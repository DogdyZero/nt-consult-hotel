package br.com.ntconsult.hotelaria.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReservaEvento {
	private String codigoReserva;
	private LocalDate checkin;
	private LocalDate checkout;
	private String nomeCliente;
	private String nomeHotel;
	private String status;
	private String contato;
}


