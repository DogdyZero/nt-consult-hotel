package br.com.ntconsult.hotelaria.adapters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservaDto {
	private String codigoReserva;
	private String codigoHotel;
	private String numeroQuarto;
	private LocalDate checkin;
	private LocalDate checkout;
	private Integer diarias;
	private Double totalReserva;
	private Double desconto;
	private Double precoDiaria;
	private Integer numeroHospedes;
	private String status;
}
