package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Preco {
	private Double valor;

	public Preco(Double valor) {
		if (Objects.isNull(valor))
			throw new RegraNegocioException("O preço deve ser informado!");
		if (valor <= 0)
			throw new RegraNegocioException("Não deve ser informado valor negativo ou zerado!");
		this.valor = valor;
	}
}
