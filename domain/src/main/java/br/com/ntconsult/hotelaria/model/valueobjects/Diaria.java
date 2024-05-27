package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;

@Getter
public class Diaria {
	private int valor;
	public Diaria(int valor){
		if(valor <= 0){
			throw new RegraNegocioException("A diária não pode ser negativa ou zerada!");
		}
		this.valor = valor;
	}
}
