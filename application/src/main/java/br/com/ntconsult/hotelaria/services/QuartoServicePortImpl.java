package br.com.ntconsult.hotelaria.services;

import br.com.ntconsult.hotelaria.model.Quarto;
import br.com.ntconsult.hotelaria.model.valueobjects.StatusQuarto;
import br.com.ntconsult.hotelaria.ports.incoming.QuartoServicePort;
import br.com.ntconsult.hotelaria.ports.outgoing.QuartoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuartoServicePortImpl implements QuartoServicePort {
	private final QuartoRepositoryPort repositoryPort;

	@Autowired
	public QuartoServicePortImpl(QuartoRepositoryPort repositoryPort) {
		this.repositoryPort = repositoryPort;
	}

	@Override
	public Flux<Quarto> buscarTodos() {
		return repositoryPort.buscarTodos();
	}

	@Override
	public Mono<Quarto> criarQuarto(Quarto quarto, String codigoQuarto) {
		return repositoryPort.criarQuarto(quarto, codigoQuarto);
	}

	@Override
	public Mono<Quarto> buscarQuartoPorCodigoHotelENumeroQuarto(String codigo, String numero) {
		return repositoryPort.buscarQuartoPorHotelENumeroQuarto(codigo, numero);
	}
}
