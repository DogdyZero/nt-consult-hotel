package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodigoTest {
	@Test
	void deveGerarUmCodigoValido(){
		var codigo = new Codigo("palavra", false);
		assertEquals("palavra", codigo.getCodigo());
		var codigoRandomico = new Codigo("palavra", true);

		assertNotEquals("palavra", codigoRandomico.getCodigo());
	}

	@Test
	void deveLancarExceptionQuandoForInformadoCodigoNulo(){
		var exception = assertThrows(RegraNegocioException.class, () -> {
			new Codigo(" ", true);
		});
		assertEquals("Deve ser informado um codigo!", exception.getMessage());
	}
}
