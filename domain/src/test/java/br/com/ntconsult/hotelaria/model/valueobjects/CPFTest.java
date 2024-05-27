package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CPFTest {

	@Test
	void deveAceitarUmCPFValido(){
		var cpfComCaracteres = new CPF("750.055.870-80");
		assertEquals("750.055.870-80", cpfComCaracteres.getNumero());

		var cpfSemCaracteres = new CPF("75005587080");
		assertEquals("75005587080", cpfSemCaracteres.getNumero());
	}

	@Test
	void deveLancarExceptionQuandoForInformadoNumeroInvalido(){
		var exception = assertThrows(RegraNegocioException.class, () -> {
			new CPF("11233222233345");
		});
		assertEquals("CPF inválido: 11233222233345", exception.getMessage());
	}
}
