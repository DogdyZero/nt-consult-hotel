package br.com.ntconsult.hotelaria.ports.incoming;

import br.com.ntconsult.hotelaria.model.Quarto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuartoServicePort {
	Flux<Quarto> buscarTodos();
	Mono<Quarto> criarQuarto(Quarto quarto, String codigoQuarto);
	Mono<Quarto> buscarQuartoPorCodigoHotelENumeroQuarto(String codigo, String numero);
}