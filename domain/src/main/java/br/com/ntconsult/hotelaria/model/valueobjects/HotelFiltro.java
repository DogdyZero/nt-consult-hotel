package br.com.ntconsult.hotelaria.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HotelFiltro {
	private String nome;
	private String localizacao;
	private LocalDate checkin;
	private LocalDate checkout;
	private Integer numeroHospedes;
	private Integer numeroQuartos;
}
