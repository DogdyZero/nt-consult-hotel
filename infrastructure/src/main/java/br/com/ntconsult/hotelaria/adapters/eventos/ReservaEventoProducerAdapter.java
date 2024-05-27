package br.com.ntconsult.hotelaria.adapters.eventos;

import br.com.ntconsult.hotelaria.config.RabbitConfigEnum;
import br.com.ntconsult.hotelaria.config.RabbitMQService;
import br.com.ntconsult.hotelaria.model.valueobjects.ReservaEvento;
import br.com.ntconsult.hotelaria.ports.outgoing.ReservaEventoProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReservaEventoProducerAdapter implements ReservaEventoProducerPort {
	private final RabbitMQService rabbitMQService;

	@Override
	public Mono<ReservaEvento> publicarEvento(ReservaEvento evento) {
		rabbitMQService.enviaMensagem(RabbitConfigEnum.QUEUE_REQUEST, evento);
		return Mono.just(evento);
	}
}
