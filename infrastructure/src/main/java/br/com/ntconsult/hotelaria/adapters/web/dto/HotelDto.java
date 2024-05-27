package br.com.ntconsult.hotelaria.adapters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {
	private String nome;
	private String localizacao;
	private Double mediaAvaliacoes;
	private List<QuartoDto> listaQuartos;
}
