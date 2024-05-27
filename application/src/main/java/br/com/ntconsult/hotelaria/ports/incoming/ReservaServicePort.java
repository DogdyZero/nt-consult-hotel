package br.com.ntconsult.hotelaria.ports.incoming;

import br.com.ntconsult.hotelaria.model.Reserva;
import br.com.ntconsult.hotelaria.model.valueobjects.ReservaEvento;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservaServicePort {
	Mono<Reserva> criarReserva(Reserva reserva);
	Mono<Reserva> buscarReservaPorCodigo(String codigo);
	Flux<Reserva> buscarTodos();
	Mono<ReservaEvento> buscarInformacoesReserva(String codigoReserva);
}
