package br.com.ntconsult.hotelaria.adapters.repositories;

import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.model.Quarto;
import br.com.ntconsult.hotelaria.model.valueobjects.CombinacoesFiltro;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelFiltro;
import br.com.ntconsult.hotelaria.model.valueobjects.Localizacao;
import br.com.ntconsult.hotelaria.model.valueobjects.Preco;
import br.com.ntconsult.hotelaria.model.valueobjects.TipoQuarto;
import br.com.ntconsult.hotelaria.ports.outgoing.HotelCombinacoesFiltroRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class HotelCombinacoesFiltroRepositoryAdapter implements HotelCombinacoesFiltroRepositoryPort {
	private final DatabaseClient databaseClient;

	@Override
	public Flux<Hotel> filtrarCombinacoes(CombinacoesFiltro combinacoesFiltro) {
		StringBuilder query = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		query.append("select h.nome as nome, h.localizacao as localizacao, ");
		query.append("h.media_avaliacao as media_avaliacao, ");
		query.append("q.tipo_quarto as tipo_quarto, q.preco as preco, ");
		query.append("q.numero as numero ");
		query.append("from hoteis h join quartos q on q.hotel_id = h.id  ");
		query.append("where 1=1 ");

		if (Objects.nonNull(combinacoesFiltro.getPrecoMaximo())) {
			var precoMinimo = 0f;
			if (Objects.nonNull(combinacoesFiltro.getPrecoMinimo())) {
				precoMinimo = combinacoesFiltro.getPrecoMinimo();
			}

			query.append("and q.preco between :precoMinimo and :precoMaximo ");
			params.put("precoMinimo", precoMinimo);
			params.put("precoMaximo", combinacoesFiltro.getPrecoMaximo());
		}

		if (CollectionUtils.isNotEmpty(combinacoesFiltro.getAvaliacoes())) {
			query.append("and h.media_avaliacao in (:avaliacoes) ");
			params.put("avaliacoes", combinacoesFiltro.getAvaliacoes());
		}

		if (CollectionUtils.isNotEmpty(combinacoesFiltro.getLocalizacoes())) {
			query.append("and lower(h.localizacao) in (:localizacao) ");
			params.put("localizacao", listLower(combinacoesFiltro.getLocalizacoes()));
		}

		if (CollectionUtils.isNotEmpty(combinacoesFiltro.getComodidades())) {
			query.append("and h.id in ( ");
			query.append("select hotel_id from comodidades c where lower(nome) in (:comodidades)) ");
			params.put("comodidades", listLower(combinacoesFiltro.getComodidades()));
		}

		DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(query.toString());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			spec = spec.bind(entry.getKey(), entry.getValue());
		}

		return spec.map((row, metadata) -> {
					return Hotel.builder()
							.nome(row.get("nome", String.class))
							.localizacao(new Localizacao(row.get("localizacao", String.class)))
							.mediaAvaliacoes(row.get("media_avaliacao", Double.class))
							.quartos(List.of(Quarto.builder()
									.numero(row.get("tipo_quarto", String.class))
									.tipoQuarto(new TipoQuarto(row.get("tipo_quarto", String.class), null))
									.preco(new Preco(row.get("preco", Double.class)))
									.build()))
							.build();
				})
				.all();
	}

	private List<String> listLower(List<String> lista) {
		return lista.stream()
				.map(String::toLowerCase)
				.toList();
	}
}
