package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;

@Getter
public class Avaliacao {
	private int estrelas;

	public Avaliacao(int estrelas) {
		if (estrelas < 1 || estrelas > 5)
			throw new RegraNegocioException("A quantidade de estrelas devem ser entre 1 e 5!");
		this.estrelas = estrelas;
	}

}
