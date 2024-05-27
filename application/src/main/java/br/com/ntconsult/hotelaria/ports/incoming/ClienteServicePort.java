package br.com.ntconsult.hotelaria.ports.incoming;

import br.com.ntconsult.hotelaria.model.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteServicePort {
	Mono<Cliente> criarCliente(Cliente cliente);
	Mono<Cliente> buscarClientePorCpf(String cpf);
	Flux<Cliente> buscarTodos();
}
