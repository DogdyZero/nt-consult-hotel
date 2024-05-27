package br.com.ntconsult.hotelaria.persistence.entities;

import br.com.ntconsult.hotelaria.model.Quarto;
import br.com.ntconsult.hotelaria.model.valueobjects.Avaliacao;
import br.com.ntconsult.hotelaria.model.valueobjects.Localizacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hoteis")
public class HotelEntity {
	@Id
	private Long id;
	private String codigo;
	private String nome;
//	private List<QuartoEntity> quartos;
	private String localizacao;
//	private List<Double> avaliacoes;
	@Column(value = "media_avaliacao")
	private Double mediaAvaliacoes;
}
