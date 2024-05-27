package br.com.ntconsult.hotelaria.mapper;

import br.com.ntconsult.hotelaria.adapters.web.dto.HotelDto;
import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.model.valueobjects.Localizacao;
import br.com.ntconsult.hotelaria.persistence.entities.HotelEntity;

public class HotelMapper {
	public static HotelEntity toEntity(Hotel hotel) {
		return HotelEntity.builder()
				.nome(hotel.getNome())
				.localizacao(hotel.getLocalizacao()
						.getNome())
				.mediaAvaliacoes(hotel.getMediaAvaliacoes())
				.build();
	}

	public static Hotel toDomain(HotelEntity entity) {
		return Hotel.builder()
				.localizacao(new Localizacao(entity.getLocalizacao()))
				.nome(entity.getNome())
				.mediaAvaliacoes(entity.getMediaAvaliacoes())
				.build();
	}

	public static Hotel toDomain(HotelDto dto) {
		return Hotel.builder()
				.localizacao(new Localizacao(dto.getLocalizacao()))
				.nome(dto.getNome())
				.mediaAvaliacoes(dto.getMediaAvaliacoes())
				.quartos(QuartoMapper.toListDomain(dto.getListaQuartos()))
				.build();
	}

	public static HotelDto toDto(Hotel hotel) {
		return HotelDto.builder()
				.nome(hotel.getNome())
				.localizacao(hotel.getLocalizacao()
						.getNome())
				.mediaAvaliacoes(hotel.getMediaAvaliacoes())
				.listaQuartos(QuartoMapper.toListDto(hotel.getQuartos()))
				.build();
	}
}
