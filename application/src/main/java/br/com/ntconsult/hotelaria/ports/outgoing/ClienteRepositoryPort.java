package br.com.ntconsult.hotelaria.ports.outgoing;

import br.com.ntconsult.hotelaria.model.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteRepositoryPort {
	Mono<Cliente> salvarCliente(Cliente cliente);
	Mono<Cliente> buscarClientePorCpf(String cpf);
	Flux<Cliente> buscarTodos();
}
