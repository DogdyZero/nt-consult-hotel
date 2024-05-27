package br.com.ntconsult.hotelaria.adapters.repositories;

import br.com.ntconsult.hotelaria.mapper.ClienteMapper;
import br.com.ntconsult.hotelaria.model.Cliente;
import br.com.ntconsult.hotelaria.persistence.repositories.ClienteReactiveRepository;
import br.com.ntconsult.hotelaria.ports.outgoing.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {
	private final ClienteReactiveRepository repository;

	@Override
	public Mono<Cliente> salvarCliente(Cliente cliente) {
		return repository.save(ClienteMapper.toEntity(cliente))
				.map(ClienteMapper::toDomain);
	}

	@Override
	public Mono<Cliente> buscarClientePorCpf(String cpf) {
		return repository.findByCpf(cpf)
				.map(ClienteMapper::toDomain);
	}

	@Override
	public Flux<Cliente> buscarTodos() {
		return repository.findAll()
				.map(ClienteMapper::toDomain);
	}
}
