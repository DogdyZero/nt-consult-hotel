package br.com.ntconsult.hotelaria.adapters.repositories;

import br.com.ntconsult.hotelaria.config.RabbitMQService;
import br.com.ntconsult.hotelaria.mapper.ReservaMapper;
import br.com.ntconsult.hotelaria.model.Reserva;
import br.com.ntconsult.hotelaria.model.valueobjects.ReservaEvento;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusReserva;
import br.com.ntconsult.hotelaria.persistence.repositories.ClienteReactiveRepository;
import br.com.ntconsult.hotelaria.persistence.repositories.QuartoReactiveRepository;
import br.com.ntconsult.hotelaria.persistence.repositories.ReservaReactiveRepository;
import br.com.ntconsult.hotelaria.ports.outgoing.ReservaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
public class ReservaRepositoryAdapter implements ReservaRepositoryPort {
	private final ReservaReactiveRepository repository;
	private final QuartoReactiveRepository quartoRepository;
	private final ClienteReactiveRepository clienteRepository;
	private final DatabaseClient databaseClient;

	@Override
	@Transactional
	public Mono<Reserva> criarReserva(Reserva reserva) {
		return quartoRepository.findByHotelAndNumeroDisponivel(reserva.getHotel()
						.getCodigo()
						.getCodigo(), reserva.getQuarto()
						.getNumero(), reserva.getCheckin(), reserva.getCheckout())
				.switchIfEmpty(Mono.error(new RuntimeException("Hotel e quarto não estão disponíveis!")))
				.flatMap(quarto -> {
					return clienteRepository.findByCpf(reserva.getCliente()
									.getCpf()
									.getNumero())
							.switchIfEmpty(Mono.error(new RuntimeException("Cliente não encontrado!")))
							.flatMap(cliente -> {
								var reservaEntity = ReservaMapper.toEntity(reserva);
								reservaEntity.setHotel(quarto.getHotel());
								reservaEntity.setQuarto(quarto.getId());
								reservaEntity.setCliente(cliente.getId());
								return repository.save(reservaEntity);
							});
				})
				.map(ReservaMapper::toDomain);
	}

	@Override
	public Mono<Reserva> buscarReservaPorCodigo(String codigo) {
		return repository.findByCodigoReserva(codigo)
				.map(ReservaMapper::toDomain);
	}

	@Override
	public Flux<Reserva> buscarTodos() {
		return repository.findAll()
				.map(ReservaMapper::toDomain);
	}

	@Override
	public Mono<ReservaEvento> buscarInformacoesReserva(String codigoReserva) {
		StringBuilder query = new StringBuilder();
		query.append("select r.codigo_reserva as codigo_reserva, r.checkin as checkin, ");
		query.append("r.checkout as checkout, c.nome as nomeCliente, ");
		query.append("h.nome as nomeHotel, r.status as status, r.contato as contato ");
		query.append("from reservas r ");
		query.append("join hoteis h ON h.id = r.hotel_id ");
		query.append("join clientes c on c.id = r.cliente_id ");
		query.append("where r.codigo_reserva = :codigo ");

		DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(query.toString());
		spec = spec.bind("codigo", codigoReserva);

		return spec.map((row, metadata) -> {
					return ReservaEvento.builder()
							.nomeCliente(row.get("nomeCliente", String.class))
							.nomeHotel(row.get("nomeHotel", String.class))
							.codigoReserva(row.get("codigo_reserva", String.class))
							.checkin(row.get("checkin", LocalDate.class))
							.checkout(row.get("checkout", LocalDate.class))
							.contato(row.get("contato", String.class))
							.status(row.get("status", String.class))
							.build();
				})
				.first();
	}

	@Override
	public Mono<Void> atualizarStatusReserva(String codigo, StatusReserva statusReserva) {
		return repository.findByCodigoReserva(codigo)
				.switchIfEmpty(Mono.error(new RuntimeException("Código da reserva não localizado!")))
				.flatMap(reserva -> {
					reserva.setStatus(statusReserva.name());
					return repository.save(reserva);
				})
				.then();
	}
}
