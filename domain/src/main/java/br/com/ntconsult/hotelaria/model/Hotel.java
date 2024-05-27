package br.com.ntconsult.hotelaria.model;

import br.com.ntconsult.hotelaria.model.valueobjects.Avaliacao;
import br.com.ntconsult.hotelaria.model.valueobjects.Codigo;
import br.com.ntconsult.hotelaria.model.valueobjects.Localizacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Hotel {
	private Codigo codigo;
	private String nome;
	private List<Quarto> quartos;
	private Localizacao localizacao;
	private List<Avaliacao> avaliacoes;
	private Double mediaAvaliacoes;
}
