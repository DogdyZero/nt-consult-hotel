package br.com.ntconsult.hotelaria.adapters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class QuartoDto {
	private String numero;
	private String descricao;
	private String tipoQuarto;
	private Double preco;
}
