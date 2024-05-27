package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;

@Getter
public class Hospedes {
	private int numero;

	public Hospedes(int numero) {
		if (numero <= 0) {
			throw new RegraNegocioException("O número de hospodes não pode ser negativa ou zerada!");
		}
		this.numero = numero;
	}
}
