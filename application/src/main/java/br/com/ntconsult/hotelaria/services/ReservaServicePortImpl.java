package br.com.ntconsult.hotelaria.services;

import br.com.ntconsult.hotelaria.model.Reserva;
import br.com.ntconsult.hotelaria.model.valueobjects.Desconto;
import br.com.ntconsult.hotelaria.model.valueobjects.ReservaEvento;
import br.com.ntconsult.hotelaria.ports.incoming.ReservaServicePort;
import br.com.ntconsult.hotelaria.ports.outgoing.QuartoRepositoryPort;
import br.com.ntconsult.hotelaria.ports.outgoing.ReservaEventoProducerPort;
import br.com.ntconsult.hotelaria.ports.outgoing.ReservaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservaServicePortImpl implements ReservaServicePort {
	private final ReservaRepositoryPort repositoryPort;
	private final QuartoRepositoryPort quartoRepositoryPort;
	private final ReservaEventoProducerPort reservaEventoProducerPort;

	@Autowired
	public ReservaServicePortImpl(ReservaRepositoryPort repositoryPort, QuartoRepositoryPort quartoRepositoryPort,
			ReservaEventoProducerPort reservaEventoProducerPort) {
		this.repositoryPort = repositoryPort;
		this.quartoRepositoryPort = quartoRepositoryPort;
		this.reservaEventoProducerPort = reservaEventoProducerPort;
	}

	@Override
	public Mono<Reserva> criarReserva(Reserva reserva) {
		return quartoRepositoryPort.buscarQuartoPorHotelENumeroQuarto(reserva.getHotel()
						.getCodigo()
						.getCodigo(), reserva.getQuarto()
						.getNumero())
				.switchIfEmpty(Mono.error(new RuntimeException("Hotel e quarto não encontrado!")))
				.flatMap(quarto -> {
					reserva.inicializarReserva(quarto, new Desconto(0d));
					return repositoryPort.criarReserva(reserva);
				})
				.flatMap(reservaSalva -> {
					return repositoryPort.buscarInformacoesReserva(reservaSalva.getCodigoReserva()
							.getCodigo());
				})
				.flatMap(evento ->{
					return reservaEventoProducerPort.publicarEvento(evento);
				}).flatMap(evento ->{
					return repositoryPort.buscarReservaPorCodigo(evento.getCodigoReserva());
				});
	}

	@Override
	public Mono<Reserva> buscarReservaPorCodigo(String codigo) {
		return repositoryPort.buscarReservaPorCodigo(codigo);
	}

	@Override
	public Flux<Reserva> buscarTodos() {
		return repositoryPort.buscarTodos();
	}

	@Override
	public Mono<ReservaEvento> buscarInformacoesReserva(String codigoReserva) {
		return repositoryPort.buscarInformacoesReserva(codigoReserva);
	}
}
