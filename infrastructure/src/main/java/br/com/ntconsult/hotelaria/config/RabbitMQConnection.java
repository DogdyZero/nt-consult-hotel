package br.com.ntconsult.hotelaria.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQConnection {
	private final AmqpAdmin amqpAdmin;

	private Queue fila(RabbitConfigEnum nomeFila) {
		return new Queue(nomeFila.getValue(), true, false, false);
	}

	private DirectExchange trocaDireta(RabbitConfigEnum nomeExchange) {
		return new DirectExchange(nomeExchange.getValue());
	}

	private Binding relacionamento(Queue fila, DirectExchange troca, RabbitConfigEnum routingKey) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), routingKey.getValue(), null);
	}

	@PostConstruct
	private void adiciona() {
		Queue filaRequest = this.fila(RabbitConfigEnum.QUEUE_REQUEST);
		Queue filaResponse = this.fila(RabbitConfigEnum.QUEUE_RESPONSE);

		DirectExchange trocaRequest = this.trocaDireta(RabbitConfigEnum.EXCHANGE_REQUEST);
		DirectExchange trocaResonse = this.trocaDireta(RabbitConfigEnum.EXCHANGE_RESPONSE);

		Binding ligacaoRequest = this.relacionamento(filaRequest, trocaRequest, RabbitConfigEnum.ROUTING_KEY_REQUEST);
		Binding ligacaoResponse = this.relacionamento(filaResponse, trocaResonse,
				RabbitConfigEnum.ROUTING_KEY_RESPONSE);

		this.amqpAdmin.declareQueue(filaRequest);
		this.amqpAdmin.declareQueue(filaResponse);

		this.amqpAdmin.declareExchange(trocaRequest);
		this.amqpAdmin.declareExchange(trocaResonse);

		this.amqpAdmin.declareBinding(ligacaoRequest);
		this.amqpAdmin.declareBinding(ligacaoResponse);

	}
}
