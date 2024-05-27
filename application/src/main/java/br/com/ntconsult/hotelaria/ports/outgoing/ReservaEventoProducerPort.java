package br.com.ntconsult.hotelaria.ports.outgoing;

import br.com.ntconsult.hotelaria.model.valueobjects.ReservaEvento;
import reactor.core.publisher.Mono;

public interface ReservaEventoProducerPort {
	Mono<ReservaEvento> publicarEvento(ReservaEvento evento);
}
