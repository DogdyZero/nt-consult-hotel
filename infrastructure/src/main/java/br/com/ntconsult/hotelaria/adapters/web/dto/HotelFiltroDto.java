package br.com.ntconsult.hotelaria.adapters.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HotelFiltroDto {
	private String nome;
	private String localizacao;
	private LocalDate checkin;
	private LocalDate checkout;
	private Integer numeroHospedes;
	private Integer numeroQuartos;
}
