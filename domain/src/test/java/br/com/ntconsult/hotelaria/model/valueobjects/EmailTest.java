package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {
	@Test
	void deveAceitarUmEmailValido(){
		var email = new Email("douglas@teste.com");
		assertEquals("douglas@teste.com", email.getEndereco());

	}

	@Test
	void deveLancarExceptionQuandoForInformadoEmailInvalido(){
		var exceptionInvalido = assertThrows(RegraNegocioException.class, () -> {
			new Email("emailerrado");
		});
		var exceptionNull = assertThrows(RegraNegocioException.class, () -> {
			new Email(null);
		});
		var exceptionVazio = assertThrows(RegraNegocioException.class, () -> {
			new Email(" ");
		});
		assertEquals("Endereço de email inválido: emailerrado", exceptionInvalido.getMessage());
		assertEquals("Endereço de email inválido: null", exceptionNull.getMessage());
		assertEquals("Endereço de email inválido:  ", exceptionVazio.getMessage());
	}
}
