package br.com.ntconsult.hotelaria.ports.outgoing;

import br.com.ntconsult.hotelaria.model.valueobjects.HotelEQuartoConsultaAvancada;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelFiltro;
import reactor.core.publisher.Flux;

public interface HotelConsultaAvancadaRepositoryPort {
	Flux<HotelEQuartoConsultaAvancada> buscaAvancada(HotelFiltro filtro);
}
