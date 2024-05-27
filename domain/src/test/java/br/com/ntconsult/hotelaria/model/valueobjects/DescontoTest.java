package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DescontoTest {
	@Test
	void deveValidarDesconto() {
		var desconto = new Desconto(24d);
		assertEquals(24d, desconto.getValor());
		var descontoNaoInformado = new Desconto(null);
		assertEquals(0, descontoNaoInformado.getValor());
		var descontoZerado = new Desconto(0d);
		assertEquals(0, descontoZerado.getValor());
	}

	@Test
	void deveLancarExceptionQuandoPrecoForNegativo() {
		var exception = assertThrows(RegraNegocioException.class, () ->{
			new Desconto(-1d);
		});
		assertEquals("Não deve ser informado desconto negativo!", exception.getMessage());

	}
}
