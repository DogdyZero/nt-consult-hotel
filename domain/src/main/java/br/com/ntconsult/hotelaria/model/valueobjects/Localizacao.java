package br.com.ntconsult.hotelaria.model.valueobjects;

import lombok.Getter;

@Getter
public class Localizacao {
	private String nome;

	public Localizacao(String nome) {
		this.nome = nome;
	}
}
