package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TotalReservaTest {
	@Test
	void deveCadastrarOTotalDaReserva() {
		var totalReserva = new TotalReserva(new Preco(24d), new Diaria(2), null);
		assertEquals(48d, totalReserva.getTotal());

		var totalReservaComDesconto = new TotalReserva(new Preco(24d), new Diaria(2), new Desconto(20d));
		assertEquals(28d, totalReservaComDesconto.getTotal());
	}

	@Test
	void deveLancarExceptionQuandoPrecoForNulo() {
		var exceptionPrecoEDiariasNull = assertThrows(RegraNegocioException.class, () -> {
			new TotalReserva(null, null, null);
		});
		var exceptionPrecoNull = assertThrows(RegraNegocioException.class, () -> {
			new TotalReserva(null, new Diaria(2), null);
		});

		var exceptionDiariaNull = assertThrows(RegraNegocioException.class, () -> {
			new TotalReserva(new Preco(24d), null, null);
		});
		assertEquals("O preço e as diarias devem ser informadas!", exceptionPrecoNull.getMessage());
		assertEquals("O preço e as diarias devem ser informadas!", exceptionPrecoNull.getMessage());
		assertEquals("O preço e as diarias devem ser informadas!", exceptionDiariaNull.getMessage());
	}
}
