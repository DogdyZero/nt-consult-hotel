package br.com.ntconsult.hotelaria.persistence.repositories;

import br.com.ntconsult.hotelaria.persistence.entities.HotelEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface HotelReactiveRepository extends ReactiveCrudRepository<HotelEntity, Long> {
	Mono<HotelEntity> findByCodigo(String codigo);

	Mono<HotelEntity> findByNome(String nome);

	Flux<HotelEntity> findByLocalizacaoIgnoreCaseLike(String localizacao);
}
