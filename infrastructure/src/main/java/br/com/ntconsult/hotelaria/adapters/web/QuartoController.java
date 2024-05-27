package br.com.ntconsult.hotelaria.adapters.web;

import br.com.ntconsult.hotelaria.adapters.web.dto.QuartoDto;
import br.com.ntconsult.hotelaria.mapper.QuartoMapper;
import br.com.ntconsult.hotelaria.ports.incoming.QuartoServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/quartos")
@RequiredArgsConstructor
public class QuartoController {

	private final QuartoServicePort servicePort;

	@GetMapping
	public Flux<QuartoDto> buscarTodos() {
		return servicePort.buscarTodos()
				.map(QuartoMapper::toDto);
	}
}
