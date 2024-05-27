package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContatoTest {
	@Test
	void deveAceitarNumeroValido(){
		var contato = new Contato("(11)95235-3232");
		assertEquals("(11)95235-3232", contato.getNumero());
	}

	@Test
	void deveLancarExceptionQuandoNumeroInvalido(){
		final var ERRO = "O contato deve ser informado ou não está no formato (11)[9]?1111-1111!";
		var exceptionNula = assertThrows(RegraNegocioException.class, ()->{
			new Contato(null);
		});
		var exceptionVazio = assertThrows(RegraNegocioException.class, ()->{
			new Contato(" ");
		});

		var exceptionFormatoInvalido1 = assertThrows(RegraNegocioException.class, ()->{
			new Contato("xxxx");
		});

		var exceptionFormatoInvalido2 = assertThrows(RegraNegocioException.class, ()->{
			new Contato("95636-6636");
		});

		var exceptionFormatoInvalido3 = assertThrows(RegraNegocioException.class, ()->{
			new Contato("11956366636");
		});

		var exceptionFormatoInvalido4 = assertThrows(RegraNegocioException.class, ()->{
			new Contato("asdasdasd");
		});

		var exceptionFormatoInvalido5 = assertThrows(RegraNegocioException.class, ()->{
			new Contato("(11)86636-6636)");
		});
		assertEquals(ERRO, exceptionNula.getMessage());
		assertEquals(ERRO, exceptionVazio.getMessage());
		assertEquals(ERRO, exceptionFormatoInvalido1.getMessage());
		assertEquals(ERRO, exceptionFormatoInvalido2.getMessage());
		assertEquals(ERRO, exceptionFormatoInvalido3.getMessage());
		assertEquals(ERRO, exceptionFormatoInvalido4.getMessage());
		assertEquals(ERRO, exceptionFormatoInvalido5.getMessage());
	}
}
