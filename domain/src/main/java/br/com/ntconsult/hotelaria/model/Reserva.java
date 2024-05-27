package br.com.ntconsult.hotelaria.model;

import br.com.ntconsult.hotelaria.model.valueobjects.Codigo;
import br.com.ntconsult.hotelaria.model.valueobjects.Contato;
import br.com.ntconsult.hotelaria.model.valueobjects.Desconto;
import br.com.ntconsult.hotelaria.model.valueobjects.DetalhesPagamento;
import br.com.ntconsult.hotelaria.model.valueobjects.Diaria;
import br.com.ntconsult.hotelaria.model.valueobjects.Hospedes;
import br.com.ntconsult.hotelaria.model.valueobjects.Preco;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusReserva;
import br.com.ntconsult.hotelaria.model.valueobjects.TotalReserva;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class Reserva {
	private Codigo codigoReserva;
	private Hotel hotel;
	private Quarto quarto;
	private LocalDate checkin;
	private LocalDate checkout;
	private Diaria diaria;
	private Desconto desconto;
	private TotalReserva totalReserva;
	private Preco precoDiaria;
	private Hospedes numeroHospedes;
	private Cliente cliente;
	private Contato contato;
	private DetalhesPagamento detalhesPagamento;
	private StatusReserva status;

	public void inicializarReserva(Quarto quarto, Desconto desconto) {
		this.desconto = desconto;
		this.precoDiaria = new Preco(quarto.getPreco()
				.getValor());
		this.totalReserva = new TotalReserva(quarto.getPreco(), diaria, desconto);
		this.status = StatusReserva.PENDENTE;
	}
}
