package br.com.ntconsult.hotelaria.services;

import br.com.ntconsult.hotelaria.model.Cliente;
import br.com.ntconsult.hotelaria.ports.incoming.ClienteServicePort;
import br.com.ntconsult.hotelaria.ports.outgoing.ClienteRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ClienteServicePortImpl implements ClienteServicePort {
	private final ClienteRepositoryPort repositoryPort;

	@Autowired
	public ClienteServicePortImpl(ClienteRepositoryPort repositoryPort) {
		this.repositoryPort = repositoryPort;
	}
	@Override
	public Mono<Cliente> criarCliente(Cliente cliente) {
		return repositoryPort.salvarCliente(cliente);
	}

	@Override
	public Mono<Cliente> buscarClientePorCpf(String cpf) {
		return repositoryPort.buscarClientePorCpf(cpf);
	}

	@Override
	public Flux<Cliente> buscarTodos() {
		return repositoryPort.buscarTodos();
	}
}
