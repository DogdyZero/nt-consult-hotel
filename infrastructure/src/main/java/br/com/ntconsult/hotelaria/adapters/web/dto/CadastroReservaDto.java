package br.com.ntconsult.hotelaria.adapters.web.dto;

import br.com.ntconsult.hotelaria.model.valueobjects.DetalhesPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CadastroReservaDto {
	private String codigoHotel;
	private String numeroQuarto;
	private LocalDate checkin;
	private LocalDate checkout;
	private Integer diarias;
	private Integer numeroHospedes;
	private String cpfCliente;
	private DetalhesPagamento detalhesPagamento;
	private String contato;
}
