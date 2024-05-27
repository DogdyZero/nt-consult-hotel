package br.com.ntconsult.hotelaria.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CombinacoesFiltro {
	private Float precoMaximo;
	private Float precoMinimo;
	private List<String> comodidades;
	private List<Integer> avaliacoes;
	private List<String> localizacoes;
}
