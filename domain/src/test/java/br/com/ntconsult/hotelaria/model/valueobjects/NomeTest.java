package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NomeTest {

	@Test
	void deveRetornarNomeValido(){
		var nome = new Nome("Douglas");
		assertEquals("Douglas", nome.getValor());
	}

	@Test
	void deveLancarErroQuandoForInformadoValorInvalido(){
		var exceptionNumero  = assertThrows(RegraNegocioException.class, () ->{
			new Nome("123");
		});
		var exceptionNull  = assertThrows(RegraNegocioException.class, () ->{
			new Nome(null);
		});

		var exceptionVazio  = assertThrows(RegraNegocioException.class, () ->{
			new Nome(" ");
		});
		assertEquals("Nome inválido: 123",exceptionNumero.getMessage() );
		assertEquals("Nome inválido: null",exceptionNull.getMessage() );
		assertEquals("Nome inválido:  ",exceptionVazio.getMessage() );

	}
}
