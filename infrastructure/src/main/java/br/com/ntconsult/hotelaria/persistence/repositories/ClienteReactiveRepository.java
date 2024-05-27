package br.com.ntconsult.hotelaria.persistence.repositories;

import br.com.ntconsult.hotelaria.persistence.entities.ClienteEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClienteReactiveRepository extends ReactiveCrudRepository<ClienteEntity, Long> {
	Mono<ClienteEntity> findByCpf(String cpf);
}
