package br.com.ntconsult.hotelaria.ports.outgoing;

import br.com.ntconsult.hotelaria.model.Reserva;
import br.com.ntconsult.hotelaria.model.valueobjects.ReservaEvento;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusReserva;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservaRepositoryPort {
	Mono<Reserva> criarReserva(Reserva reserva);
	Mono<Reserva> buscarReservaPorCodigo(String codigo);
	Flux<Reserva> buscarTodos();
	Mono<ReservaEvento> buscarInformacoesReserva(String codigoReserva);
	Mono<Void> atualizarStatusReserva(String codigo, StatusReserva statusReserva);
}
