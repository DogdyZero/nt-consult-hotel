package br.com.ntconsult.hotelaria.persistence.entities;

import br.com.ntconsult.hotelaria.model.valueobjects.StatusReserva;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
public class ReservaEntity {
	@Id
	private Long id;
	@Column("codigo_reserva")
	private String codigoReserva;
	@Column("hotel_id")
	private Long hotel;
	@Column("quarto_id")
	private Long quarto;
	private LocalDate checkin;
	private LocalDate checkout;
	@Column("preco")
	private Double precoDiaria;
	private Integer diarias;
	private Double desconto;
	@Column("total_reserva")
	private Double totalReserva;
	@Column("numero_hospedes")
	private Integer numeroHospedes;
	@Column("cliente_id")
	private Long cliente;
	private String contato;
	@Column("detalhes_pagamento")
	private String detalhesPagamento;
	private String status;
}
