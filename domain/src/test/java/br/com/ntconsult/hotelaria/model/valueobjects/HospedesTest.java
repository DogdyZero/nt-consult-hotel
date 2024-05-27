package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HospedesTest {
	@Test
	void deveAceitarQuantidadeDeHospedes() {
		var hospedes = new Hospedes(1);
		assertEquals(1, hospedes.getNumero());
	}

	@Test
	void deveLancarExceptionQuandoNumeroHospedesForInvalido() {
		var exceptionZerada = assertThrows(RegraNegocioException.class, () -> {
			new Hospedes(0);
		});
		var exceptionNegativa = assertThrows(RegraNegocioException.class, () -> {
			new Hospedes(-1);
		});
		assertEquals("O número de hospodes não pode ser negativa ou zerada!", exceptionZerada.getMessage());
		assertEquals("O número de hospodes não pode ser negativa ou zerada!", exceptionNegativa.getMessage());

	}
}
