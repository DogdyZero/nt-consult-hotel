package br.com.ntconsult.hotelaria.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQService {
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;

	public void enviaMensagem(RabbitConfigEnum nomeFila, Object mensagem) {
		try {
			String mensagemJson = this.objectMapper.writeValueAsString(mensagem);
			this.rabbitTemplate.convertAndSend(nomeFila.getValue(), mensagemJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
