package br.com.ntconsult.hotelaria.ports.incoming;

import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.model.valueobjects.CombinacoesFiltro;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelEQuartoConsultaAvancada;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelFiltro;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HotelServicePort {
	Flux<Hotel> buscarTodos();

	Mono<Hotel> criarHotel(Hotel hotel);

	Mono<Hotel> buscarHotelPorNome(String nome);

	Flux<Hotel> buscarHotelPorLocalizacao(String localizacao);

	Mono<Hotel> buscarHotelPeloCodigo(String codigo);

	Flux<HotelEQuartoConsultaAvancada> buscaAvancada(HotelFiltro filtro);

	Flux<Hotel> filtrarCombinacoes(CombinacoesFiltro combinacoesFiltro);

}
