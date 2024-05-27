package br.com.ntconsult.hotelaria.adapters.repositories;

import br.com.ntconsult.hotelaria.mapper.QuartoMapper;
import br.com.ntconsult.hotelaria.model.Quarto;
import br.com.ntconsult.hotelaria.persistence.repositories.HotelReactiveRepository;
import br.com.ntconsult.hotelaria.persistence.repositories.QuartoReactiveRepository;
import br.com.ntconsult.hotelaria.ports.outgoing.QuartoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class QuartoRepositoryAdapter implements QuartoRepositoryPort {
	private final QuartoReactiveRepository repository;
	private final HotelReactiveRepository hotelRepository;

	@Override
	public Flux<Quarto> buscarTodos() {
		return repository.findAll()
				.map(QuartoMapper::toDomain);
	}

	@Override
	public Mono<Quarto> criarQuarto(Quarto quarto, String codigoHotel) {
		return hotelRepository.findByCodigo(codigoHotel)
				.switchIfEmpty(Mono.error(new RuntimeException("Hotel not found")))
				.flatMap(hotel -> {
					var quartoEntity = QuartoMapper.toEntity(quarto);
					quartoEntity.setHotel(hotel.getId());
					return repository.save(quartoEntity);
				})
				.map(QuartoMapper::toDomain);
	}

	@Override
	public Mono<Quarto> buscarQuartoPorHotelENumeroQuarto(String codigoHotel, String numero) {
		return repository.findByHotelAndNumero(codigoHotel, numero)
				.map(QuartoMapper::toDomain);
	}
}
