package br.com.ntconsult.hotelaria.ports.outgoing;

import br.com.ntconsult.hotelaria.model.Quarto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuartoRepositoryPort {
	Flux<Quarto> buscarTodos();
	Mono<Quarto> criarQuarto(Quarto quarto, String codigoHotel);
	Mono<Quarto> buscarQuartoPorHotelENumeroQuarto(String codigoHotel, String codigo);
}
