package br.com.ntconsult.hotelaria.persistence.repositories;

import br.com.ntconsult.hotelaria.persistence.entities.ReservaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReservaReactiveRepository extends ReactiveCrudRepository<ReservaEntity, Long> {
	Mono<ReservaEntity> findByCodigoReserva(String codigo);
}
