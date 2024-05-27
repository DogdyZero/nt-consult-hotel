package br.com.ntconsult.hotelaria.ports.outgoing;

import br.com.ntconsult.hotelaria.model.Hotel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HotelRepositoryPort {
	Flux<Hotel> buscarTodos();
	Mono<Hotel> criarHotel(Hotel hotel);
	Mono<Hotel> buscarHotelPorNome(String nome);
	Mono<Hotel> buscarHotelPeloCodigo(String codigo);
	Flux<Hotel> buscarHotelPorLocalizacao(String localizacao);
}
