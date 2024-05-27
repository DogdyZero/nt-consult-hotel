package br.com.ntconsult.hotelaria.config;

public enum RabbitConfigEnum {
	EXCHANGE_REQUEST("notificacao-request-exchange"), //
	QUEUE_REQUEST("notificacao-request-queue"), //
	ROUTING_KEY_REQUEST("notificacao-request-routing-key"), //
	EXCHANGE_RESPONSE("notificacao-response-exchange"), //
	QUEUE_RESPONSE("notificacao-response-queue"), //
	ROUTING_KEY_RESPONSE("notificacao-response-routing-key"); //

	private String value;

	private RabbitConfigEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
