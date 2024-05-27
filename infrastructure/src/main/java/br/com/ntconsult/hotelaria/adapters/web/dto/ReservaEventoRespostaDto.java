package br.com.ntconsult.hotelaria.adapters.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaEventoRespostaDto {
	private Boolean notificacaoEnviada;
	private String codigoReserva;
	private String status;
}
