package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class TotalReserva {
	private Double total;

	public TotalReserva(Preco preco, Diaria diarias, Desconto desconto) {
		if (Objects.isNull(preco) || Objects.isNull(diarias)) {
			throw new RegraNegocioException("O preço e as diarias devem ser informadas!");
		}
		if (Objects.isNull(desconto))
			desconto = new Desconto(0d);

		this.total = (preco.getValor() * diarias.getValor()) - desconto.getValor();
	}
}
