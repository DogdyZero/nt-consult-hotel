package br.com.ntconsult.hotelaria.mapper;

import br.com.ntconsult.hotelaria.adapters.web.dto.QuartoDto;
import br.com.ntconsult.hotelaria.model.Quarto;
import br.com.ntconsult.hotelaria.model.valueobjects.Preco;
import br.com.ntconsult.hotelaria.model.valueobjects.TipoQuarto;
import br.com.ntconsult.hotelaria.persistence.entities.QuartoEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class QuartoMapper {
	public static Quarto toDomain(QuartoEntity entity) {
		return Quarto.builder()
				.descricao(entity.getDescricao())
				.preco(new Preco(entity.getPreco()))
				.tipoQuarto(new TipoQuarto(entity.getTipoQuarto(), null))
				.numero(entity.getNumero())
				.build();
	}

	public static QuartoEntity toEntity(Quarto quarto) {
		return QuartoEntity.builder()
				.descricao(quarto.getDescricao())
				.tipoQuarto(quarto.getTipoQuarto()
						.getNome())
				.preco(quarto.getPreco()
						.getValor())
				.numero(quarto.getNumero())
				.build();
	}

	public static List<QuartoEntity> toListEntities(List<Quarto> quartos) {
		return quartos.stream()
				.map(QuartoMapper::toEntity)
				.toList();
	}

	public static QuartoDto toDto(Quarto quarto) {
		return QuartoDto.builder()
				.descricao(quarto.getDescricao())
				.tipoQuarto(quarto.getTipoQuarto()
						.getNome())
				.preco(quarto.getPreco()
						.getValor())
				.numero(quarto.getNumero())
				.build();
	}

	public static Quarto toDomain(QuartoDto dto) {
		return Quarto.builder()
				.descricao(dto.getDescricao())
				.preco(new Preco(dto.getPreco()))
				.tipoQuarto(new TipoQuarto(dto.getTipoQuarto(), null))
				.numero(dto.getNumero())
				.build();
	}

	public static List<Quarto> toListDomain(List<QuartoDto> dtos) {
		return dtos.stream()
				.map(QuartoMapper::toDomain)
				.toList();
	}

	public static List<QuartoDto> toListDto(List<Quarto> dtos) {
		return CollectionUtils.isNotEmpty(dtos) ? dtos.stream()
				.map(QuartoMapper::toDto)
				.toList() : null;
	}
}
