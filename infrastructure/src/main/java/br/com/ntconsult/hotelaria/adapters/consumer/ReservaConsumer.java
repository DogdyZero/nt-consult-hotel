package br.com.ntconsult.hotelaria.adapters.consumer;

import br.com.ntconsult.hotelaria.adapters.web.dto.ReservaEventoRespostaDto;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusReserva;
import br.com.ntconsult.hotelaria.ports.outgoing.ReservaRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReservaConsumer {
	private final ReservaRepositoryPort reservaRepositoryPort;

	@RabbitListener(queues = "notificacao-response-queue")
	public Mono<Void> consume(String mensagem) throws JsonProcessingException {
		ReservaEventoRespostaDto dto = new ObjectMapper().readValue(mensagem, ReservaEventoRespostaDto.class);
		if (dto != null) {
			if (dto.getNotificacaoEnviada()) {
				return reservaRepositoryPort.atualizarStatusReserva(dto.getCodigoReserva(), StatusReserva.CONFIRMADO)
						.then(Mono.fromRunnable(() -> System.out.println("Status atualizado com sucesso!")));
			}
		}
		return Mono.empty();
	}
}
