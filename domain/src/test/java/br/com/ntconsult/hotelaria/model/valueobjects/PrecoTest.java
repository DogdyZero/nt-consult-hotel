package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrecoTest {
	@Test
	void deveValidarPreco() {
		var preco = new Preco(24d);
		assertEquals(24d, preco.getValor());
	}

	@Test
	void deveLancarExceptionQuandoPrecoForNulo() {
		var exception = assertThrows(RegraNegocioException.class, () ->{
			new Preco(null);
		});
		var exceptionInvalido = assertThrows(RegraNegocioException.class, () ->{
			new Preco(0d);
		});
		assertEquals("O preço deve ser informado!", exception.getMessage());
		assertEquals("Não deve ser informado valor negativo ou zerado!", exceptionInvalido.getMessage());

	}
}
