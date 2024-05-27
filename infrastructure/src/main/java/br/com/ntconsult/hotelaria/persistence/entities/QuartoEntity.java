package br.com.ntconsult.hotelaria.persistence.entities;

import br.com.ntconsult.hotelaria.model.valueobjects.StatusQuarto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quartos")
public class QuartoEntity {
	@Id
	private Long id;
	private String numero;
	private String descricao;
	private Double preco;
	@Column("tipo_quarto")
	private String tipoQuarto;
	@Column("hotel_id")
	private Long hotel;
	@Column("hospedes_quarto")
	private int hospedesPorQuarto;
}
