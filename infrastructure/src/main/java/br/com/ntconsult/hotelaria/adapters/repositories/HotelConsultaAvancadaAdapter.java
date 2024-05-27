package br.com.ntconsult.hotelaria.adapters.repositories;

import br.com.ntconsult.hotelaria.model.valueobjects.CombinacoesFiltro;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelEQuartoConsultaAvancada;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelFiltro;
import br.com.ntconsult.hotelaria.ports.outgoing.HotelConsultaAvancadaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class HotelConsultaAvancadaAdapter implements HotelConsultaAvancadaRepositoryPort {
	private final DatabaseClient databaseClient;

	@Override
	public Flux<HotelEQuartoConsultaAvancada> buscaAvancada(HotelFiltro filtro) {
		StringBuilder query = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		query.append("select h.nome as nome, h.localizacao as localizacao, ");
		query.append("q.numero as numero, q.tipo_quarto as tipo_quarto ");
		query.append("from quartos q ");
		query.append("join hoteis h on q.hotel_id = h.id ");
		query.append("where 1=1 ");

		if (StringUtils.isNotBlank(filtro.getNome())) {
			query.append("and lower(nome) like :nome ");
			params.put("nome", format(filtro.getNome()));
		}

		if (StringUtils.isNotBlank(filtro.getLocalizacao())) {
			query.append("and lower(localizacao) like :localizacao ");
			params.put("localizacao", format(filtro.getLocalizacao()));
		}

		if (Objects.nonNull(filtro.getCheckin())) {
			var hoje = LocalDate.now();
			if (Objects.nonNull(filtro.getCheckout())) {
				hoje = filtro.getCheckout();
			}
			query.append("and q.id not in ");
			query.append("(select quarto_id from reservas where checkin >= :checkin ");
			query.append("and checkout <= :checkout) ");

			params.put("checkin", filtro.getCheckin());
			params.put("checkout", hoje);
		}

		if (Objects.nonNull(filtro.getNumeroHospedes())) {
			query.append("and q.hospedes_quarto = :hospedesPorQuarto ");
			params.put("hospedesPorQuarto", filtro.getNumeroHospedes());
		}
		DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(query.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			spec = spec.bind(entry.getKey(), entry.getValue());
		}

		if (Objects.isNull(filtro.getNumeroQuartos()) || filtro.getNumeroQuartos()
				.equals(0)) {
			return getMap(spec).all();
		} else {
			return getMap(spec).all()
					.collectList()
					.flatMapMany(list -> {
						return (list.size() >= filtro.getNumeroQuartos()) ? Flux.fromIterable(list) : Flux.empty();
					});
		}
	}

	private RowsFetchSpec<HotelEQuartoConsultaAvancada> getMap(DatabaseClient.GenericExecuteSpec spec) {
		return spec.map((row, metadata) -> {
			return HotelEQuartoConsultaAvancada.builder()
					.nome(row.get("nome", String.class))
					.localizacao(row.get("localizacao", String.class))
					.numero(row.get("numero", String.class))
					.tipoQuarto(row.get("tipo_quarto", String.class))
					.build();
		});
	}

	private String format(String arg) {
		return "%" + arg.trim()
				.toLowerCase() + "%";
	}
}
