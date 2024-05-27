package br.com.ntconsult.hotelaria.adapters.web;

import br.com.ntconsult.hotelaria.config.RabbitConfigEnum;
import br.com.ntconsult.hotelaria.config.RabbitMQService;
import br.com.ntconsult.hotelaria.model.valueobjects.ReservaEvento;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusReserva;
import br.com.ntconsult.hotelaria.ports.incoming.ReservaServicePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/listener")
@RequiredArgsConstructor
public class RabbitMqController {

	private final RabbitMQService rabbitMQService;
	private final ReservaServicePort reservaServicePort;

	@GetMapping
	public Mono<Void> dispararEvento() throws JsonProcessingException {
		return reservaServicePort.buscarInformacoesReserva("S5GY42")
				.flatMap(item -> Mono.fromRunnable(() -> integrar(item)));
	}

	public void integrar(ReservaEvento reserva) {
		try {
			rabbitMQService.enviaMensagem(RabbitConfigEnum.QUEUE_REQUEST, reserva);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
