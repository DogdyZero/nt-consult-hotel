package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiariaTest {
	@Test
	void deveValidarDiaria() {
		var diaria = new Diaria(1);
		assertEquals(1, diaria.getValor());
	}

	@Test
	void deveLancarExceptionQuandoPrecoForNulo() {
		var exceptionZerada = assertThrows(RegraNegocioException.class, () -> {
			new Diaria(0);
		});
		var exceptionNegativa = assertThrows(RegraNegocioException.class, () -> {
			new Diaria(-1);
		});
		assertEquals("A diária não pode ser negativa ou zerada!", exceptionZerada.getMessage());
		assertEquals("A diária não pode ser negativa ou zerada!", exceptionNegativa.getMessage());

	}
}
