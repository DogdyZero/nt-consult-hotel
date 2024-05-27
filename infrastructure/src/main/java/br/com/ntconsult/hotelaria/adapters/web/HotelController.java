package br.com.ntconsult.hotelaria.adapters.web;

import br.com.ntconsult.hotelaria.adapters.web.dto.HotelDto;
import br.com.ntconsult.hotelaria.adapters.web.dto.QuartoDto;
import br.com.ntconsult.hotelaria.mapper.HotelMapper;
import br.com.ntconsult.hotelaria.mapper.QuartoMapper;
import br.com.ntconsult.hotelaria.model.Hotel;
import br.com.ntconsult.hotelaria.model.valueobjects.CombinacoesFiltro;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelEQuartoConsultaAvancada;
import br.com.ntconsult.hotelaria.model.valueobjects.HotelFiltro;
import br.com.ntconsult.hotelaria.ports.incoming.HotelServicePort;
import br.com.ntconsult.hotelaria.ports.incoming.QuartoServicePort;
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
@RequiredArgsConstructor
@RequestMapping("/hoteis")
public class HotelController {
	private final HotelServicePort servicePort;
	private final QuartoServicePort quartoServicePort;

	@PostMapping
	public Mono<ResponseEntity<Void>> salvarHotel(@RequestBody HotelDto dto) {
		return servicePort.criarHotel(HotelMapper.toDomain(dto))
				.then(Mono.just(new ResponseEntity<>(HttpStatus.CREATED)));
	}

	@PostMapping("{codigo}/quartos")
	public Mono<QuartoDto> salvarQuarto(@PathVariable(name = "codigo") String codigo, @RequestBody QuartoDto dto) {
		return quartoServicePort.criarQuarto(QuartoMapper.toDomain(dto), codigo)
				.map(QuartoMapper::toDto);
	}

	@GetMapping("{codigo}")
	public Mono<HotelDto> buscarHotelPeloCodigo(@PathVariable(name = "codigo") String codigo) {
		return servicePort.buscarHotelPeloCodigo(codigo)
				.map(HotelMapper::toDto);
	}

	@GetMapping("{codigo}/quarto/{numero}")
	public Mono<QuartoDto> buscarQuartoNoHotel(@PathVariable(name = "codigo") String codigo,
			@PathVariable(name = "numero") String numero) {
		return quartoServicePort.buscarQuartoPorCodigoHotelENumeroQuarto(codigo, numero)
				.map(QuartoMapper::toDto);
	}

	@GetMapping
	public Flux<HotelEQuartoConsultaAvancada> busca(HotelFiltro filtro) {
		return servicePort.buscaAvancada(filtro);
	}

	@PostMapping("/filtro")
	public Flux<HotelDto> filtrarCombinacoes(@RequestBody CombinacoesFiltro combinacoes) {
		return servicePort.filtrarCombinacoes(combinacoes)
				.map(HotelMapper::toDto);
	}

}