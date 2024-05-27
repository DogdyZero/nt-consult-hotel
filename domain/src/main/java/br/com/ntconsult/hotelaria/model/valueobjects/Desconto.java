package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Desconto {
	private Double valor;
	public Desconto(Double valor){
		if (Objects.isNull(valor) || valor.equals(0d)){
			this.valor = 0d;
			return;
		}
		if (valor < 0)
			throw new RegraNegocioException("Não deve ser informado desconto negativo!");
		this.valor = valor;
	}
}
