package br.com.ntconsult.hotelaria.adapters.repositories;

import br.com.ntconsult.hotelaria.mapper.HotelMapper;
import br.com.ntconsult.hotelaria.mapper.QuartoMapper;
import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.persistence.repositories.HotelReactiveRepository;
import br.com.ntconsult.hotelaria.persistence.repositories.QuartoReactiveRepository;
import br.com.ntconsult.hotelaria.ports.outgoing.HotelRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class HotelRepositoryAdapter implements HotelRepositoryPort {
	private final HotelReactiveRepository repository;
	private final QuartoReactiveRepository quartoRepository;

	@Override
	public Flux<Hotel> buscarTodos() {
		return null;
	}

	@Override
	@Transactional
	public Mono<Hotel> criarHotel(Hotel hotel) {
		return repository.save(HotelMapper.toEntity(hotel))
				.flatMap(saved -> {
					var quartosEntity = QuartoMapper.toListEntities(hotel.getQuartos());
					quartosEntity.forEach(quarto -> quarto.setHotel(saved.getId()));

					return quartoRepository.saveAll(quartosEntity)
							.collectList()
							.then(Mono.just(HotelMapper.toDomain(saved)));
				});
	}

	@Override
	public Mono<Hotel> buscarHotelPorNome(String nome) {
		return repository.findByNome(nome)
				.map(HotelMapper::toDomain);
	}

	@Override
	public Mono<Hotel> buscarHotelPeloCodigo(String codigo) {
		return repository.findByCodigo(codigo).map(HotelMapper::toDomain);
	}

	@Override
	public Flux<Hotel> buscarHotelPorLocalizacao(String localizacao) {
		return repository.findByLocalizacaoIgnoreCaseLike(localizacao)
				.map(HotelMapper::toDomain);
	}
}
