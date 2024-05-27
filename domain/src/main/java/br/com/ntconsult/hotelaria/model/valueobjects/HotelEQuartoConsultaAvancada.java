package br.com.ntconsult.hotelaria.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class HotelEQuartoConsultaAvancada {
	private String localizacao;
	private String nome;
	private String numero;
	private String tipoQuarto;
}
