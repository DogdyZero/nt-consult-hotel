package br.com.ntconsult.hotelaria.ports.outgoing;

import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.model.valueobjects.CombinacoesFiltro;
import reactor.core.publisher.Flux;

public interface HotelCombinacoesFiltroRepositoryPort {
	Flux<Hotel> filtrarCombinacoes(CombinacoesFiltro combinacoesFiltro);
}
