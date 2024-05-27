package br.com.ntconsult.hotelaria.adapters.web;

import br.com.ntconsult.hotelaria.adapters.web.dto.CadastroReservaDto;
import br.com.ntconsult.hotelaria.adapters.web.dto.ReservaDto;
import br.com.ntconsult.hotelaria.mapper.ReservaMapper;
import br.com.ntconsult.hotelaria.ports.incoming.ReservaServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {
	private final ReservaServicePort service;

	@PostMapping
	public Mono<ReservaDto> criarReserva(@RequestBody CadastroReservaDto dto){
		return service.criarReserva(ReservaMapper.toDomain(dto))
				.map(ReservaMapper::toDto);
	}

	@GetMapping("{codigo}")
	public Mono<ReservaDto> buscarPorCodigoReserva(@PathVariable(name = "codigo") String codigo){
		return service.buscarReservaPorCodigo(codigo)
				.map(ReservaMapper::toDto);
	}

	@GetMapping
	public Flux<ReservaDto> buscarTodos(){
		return service.buscarTodos()
				.map(ReservaMapper::toDto);
	}
}
