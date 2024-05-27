package br.com.ntconsult.hotelaria.model;

import br.com.ntconsult.hotelaria.model.valueobjects.Preco;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusQuarto;
import br.com.ntconsult.hotelaria.model.valueobjects.TipoQuarto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Quarto {
	private String numero;
	private String descricao;
	private TipoQuarto tipoQuarto;
	private Preco preco;
	private StatusQuarto status;
	private int hospedesPorQuarto;
}
