package br.com.ntconsult.hotelaria.mapper;

import br.com.ntconsult.hotelaria.adapters.web.dto.CadastroReservaDto;
import br.com.ntconsult.hotelaria.adapters.web.dto.ReservaDto;
import br.com.ntconsult.hotelaria.model.Cliente;
import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.model.Quarto;
import br.com.ntconsult.hotelaria.model.Reserva;
import br.com.ntconsult.hotelaria.model.valueobjects.CPF;
import br.com.ntconsult.hotelaria.model.valueobjects.Codigo;
import br.com.ntconsult.hotelaria.model.valueobjects.Contato;
import br.com.ntconsult.hotelaria.model.valueobjects.Desconto;
import br.com.ntconsult.hotelaria.model.valueobjects.DetalhesPagamento;
import br.com.ntconsult.hotelaria.model.valueobjects.Diaria;
import br.com.ntconsult.hotelaria.model.valueobjects.Hospedes;
import br.com.ntconsult.hotelaria.model.valueobjects.Preco;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusReserva;
import br.com.ntconsult.hotelaria.model.valueobjects.TotalReserva;
import br.com.ntconsult.hotelaria.persistence.entities.ReservaEntity;

public class ReservaMapper {
	public static Reserva toDomain(ReservaEntity entity) {
		var diaria = new Diaria(entity.getDiarias());
		var desconto = new Desconto(entity.getDesconto());
		var preco = new Preco(entity.getPrecoDiaria());
		return Reserva.builder()
				.checkin(entity.getCheckin())
				.checkout(entity.getCheckout())
				.desconto(new Desconto(entity.getDesconto()))
				.diaria(new Diaria(entity.getDiarias()))
				.precoDiaria(preco)
				.numeroHospedes(new Hospedes(entity.getNumeroHospedes()))
				.totalReserva(new TotalReserva(preco, diaria, desconto))
				.codigoReserva(new Codigo(entity.getCodigoReserva(), false))
				.status(StatusReserva.valueOf(entity.getStatus()))
				.build();
	}

	public static ReservaEntity toEntity(Reserva reserva) {
		return ReservaEntity.builder()
				.codigoReserva(reserva.getCodigoReserva()
						.getCodigo())
				.checkin(reserva.getCheckin())
				.checkout(reserva.getCheckout())
				.numeroHospedes(reserva.getNumeroHospedes()
						.getNumero())
				.diarias(reserva.getDiaria()
						.getValor())
				.desconto(reserva.getDesconto() != null ? reserva.getDesconto()
						.getValor() : null)
				.totalReserva(reserva.getTotalReserva()
						.getTotal())
				.precoDiaria(reserva.getPrecoDiaria()
						.getValor())
				.contato(reserva.getContato()
						.getNumero())
				.detalhesPagamento(reserva.getDetalhesPagamento()
						.name())
				.status(reserva.getStatus()
						.name())
				.build();
	}

	public static Reserva toDomain(ReservaDto dto) {
		var diaria = new Diaria(dto.getDiarias());
		var desconto = new Desconto(dto.getDesconto());
		var preco = new Preco(dto.getPrecoDiaria());
		return Reserva.builder()
				.checkin(dto.getCheckin())
				.checkout(dto.getCheckout())
				.desconto(new Desconto(dto.getDesconto()))
				.diaria(new Diaria(dto.getDiarias()))
				.numeroHospedes(new Hospedes(dto.getNumeroHospedes()))
				.totalReserva(new TotalReserva(preco, diaria, desconto))
				.codigoReserva(new Codigo(dto.getCodigoReserva(), false))
				.precoDiaria(new Preco(dto.getPrecoDiaria()))
				.hotel(Hotel.builder()
						.codigo(new Codigo(dto.getCodigoHotel(), false))
						.build())
				.quarto(Quarto.builder()
						.numero(dto.getNumeroQuarto())
						.build())
				.build();
	}

	public static Reserva toDomain(CadastroReservaDto dto) {
		return Reserva.builder()
				.checkin(dto.getCheckin())
				.checkout(dto.getCheckout())
				.diaria(new Diaria(dto.getDiarias()))
				.numeroHospedes(new Hospedes(dto.getNumeroHospedes()))
				.codigoReserva(new Codigo("novaReserva", true))
				.hotel(Hotel.builder()
						.codigo(new Codigo(dto.getCodigoHotel(), false))
						.build())
				.quarto(Quarto.builder()
						.numero(dto.getNumeroQuarto())
						.build())
				.contato(new Contato(dto.getContato()))
				.detalhesPagamento(dto.getDetalhesPagamento())
				.cliente(Cliente.builder()
						.cpf(new CPF(dto.getCpfCliente()))
						.build())
				.build();
	}

	public static ReservaDto toDto(Reserva reserva) {
		return ReservaDto.builder()
				.codigoReserva(reserva.getCodigoReserva()
						.getCodigo())
				.checkin(reserva.getCheckin())
				.checkout(reserva.getCheckout())
				.numeroHospedes(reserva.getNumeroHospedes()
						.getNumero())
				.diarias(reserva.getDiaria()
						.getValor())
				.desconto(reserva.getDesconto()
						.getValor())
				.totalReserva(reserva.getTotalReserva()
						.getTotal())
				.precoDiaria(reserva.getPrecoDiaria()
						.getValor())
				.status(reserva.getStatus()
						.name())
				.build();
	}
}
