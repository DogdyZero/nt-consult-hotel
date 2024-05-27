package br.com.ntconsult.hotelaria.persistence.repositories;

import br.com.ntconsult.hotelaria.persistence.entities.QuartoEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface QuartoReactiveRepository extends ReactiveCrudRepository<QuartoEntity, Long> {
	@Query( "select * from Quartos q "
			+ "join Hoteis h on q.id = q.hotel_id "
			+ "where h.codigo = :codigo and q.numero = :numero")
	Mono<QuartoEntity> findByHotelAndNumero(String codigo, String numero);

	@Query( "select * from Quartos q "
			+ "join Hoteis h on q.id = q.hotel_id "
			+ "where h.codigo = :codigo and q.numero = :numero and "
			+ "h.id not in (select quarto_id from Reservas r where "
			+ "r.checkin >= :checkin and r.checkout <= :chechout )")
	Mono<QuartoEntity> findByHotelAndNumeroDisponivel(String codigo, String numero, LocalDate checkin, LocalDate checkout);
}
