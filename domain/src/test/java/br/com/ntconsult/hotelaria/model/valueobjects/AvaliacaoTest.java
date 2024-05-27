package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvaliacaoTest {
	@Test
	void deveAceitarAAvaliacao() {
		var avaliacao = new Avaliacao(2);
		assertEquals(2, avaliacao.getEstrelas());
	}

	@Test
	void deveLancarExceptionQuandoValorAvaliacaoForInvalido() {
		var exceptionMenorQueUm = assertThrows(RegraNegocioException.class, () -> {
			new Avaliacao(0);
		});
		var exceptionMaiorQueCinco = assertThrows(RegraNegocioException.class, () -> {
			new Avaliacao(6);
		});
		assertEquals("A quantidade de estrelas devem ser entre 1 e 5!", exceptionMenorQueUm.getMessage());
		assertEquals("A quantidade de estrelas devem ser entre 1 e 5!", exceptionMaiorQueCinco.getMessage());

	}
}
