package br.com.ntconsult.hotelaria.adapters.web;

import br.com.ntconsult.hotelaria.adapters.web.dto.ClienteDto;
import br.com.ntconsult.hotelaria.mapper.ClienteMapper;
import br.com.ntconsult.hotelaria.ports.incoming.ClienteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
	private final ClienteServicePort service;

	@PostMapping
	public Mono<ResponseEntity<Void>> salvarCliente(@RequestBody ClienteDto dto) {
		return service.criarCliente(ClienteMapper.toDomain(dto))
				.then(Mono.just(new ResponseEntity<>(HttpStatus.CREATED)));
	}

	@GetMapping("{cpf}")
	public Mono<ResponseEntity<ClienteDto>> buscarPorCpf(@PathVariable(name = "cpf") String cpf) {
		return service.buscarClientePorCpf(cpf)
				.map(cliente -> new ResponseEntity<>(ClienteMapper.toDto(cliente), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public Flux<ClienteDto> buscarTodos() {
		return service.buscarTodos()
				.map(ClienteMapper::toDto);
	}
}
