package br.com.ntconsult.hotelaria.services;

import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.model.valueobjects.CombinacoesFiltro;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelEQuartoConsultaAvancada;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelFiltro;
import br.com.ntconsult.hotelaria.ports.incoming.HotelServicePort;
import br.com.ntconsult.hotelaria.ports.outgoing.HotelCombinacoesFiltroRepositoryPort;
import br.com.ntconsult.hotelaria.ports.outgoing.HotelConsultaAvancadaRepositoryPort;
import br.com.ntconsult.hotelaria.ports.outgoing.HotelRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HotelServicePortImpl implements HotelServicePort {
	private final HotelRepositoryPort repositoryPort;
	private final HotelConsultaAvancadaRepositoryPort hotelConsultaAvancadaRepositoryPort;
	private final HotelCombinacoesFiltroRepositoryPort hotelCombinacoesFiltroRepositoryPort;

	@Autowired
	public HotelServicePortImpl(HotelRepositoryPort repositoryPort,
			HotelConsultaAvancadaRepositoryPort hotelConsultaAvancadaRepositoryPort,
			HotelCombinacoesFiltroRepositoryPort hotelCombinacoesFiltroRepositoryPort) {
		this.repositoryPort = repositoryPort;
		this.hotelConsultaAvancadaRepositoryPort = hotelConsultaAvancadaRepositoryPort;
		this.hotelCombinacoesFiltroRepositoryPort = hotelCombinacoesFiltroRepositoryPort;
	}

	@Override
	public Flux<Hotel> buscarTodos() {
		return repositoryPort.buscarTodos();
	}

	@Override
	public Mono<Hotel> criarHotel(Hotel hotel) {

		return repositoryPort.criarHotel(hotel);
	}

	@Override
	public Mono<Hotel> buscarHotelPorNome(String nome) {
		return repositoryPort.buscarHotelPorNome(nome);
	}

	@Override
	public Flux<Hotel> buscarHotelPorLocalizacao(String localizacao) {
		return repositoryPort.buscarHotelPorLocalizacao(localizacao);
	}

	@Override
	public Mono<Hotel> buscarHotelPeloCodigo(String codigo) {
		return repositoryPort.buscarHotelPeloCodigo(codigo);
	}

	@Override
	public Flux<HotelEQuartoConsultaAvancada> buscaAvancada(HotelFiltro filtro) {
		return hotelConsultaAvancadaRepositoryPort.buscaAvancada(filtro);
	}

	@Override
	public Flux<Hotel> filtrarCombinacoes(CombinacoesFiltro combinacoesFiltro) {
		return hotelCombinacoesFiltroRepositoryPort.filtrarCombinacoes(combinacoesFiltro);
	}

}
