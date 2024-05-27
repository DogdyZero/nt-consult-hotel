package br.com.ntconsult.hotelaria.model;

import br.com.ntconsult.hotelaria.model.valueobjects.CPF;
import br.com.ntconsult.hotelaria.model.valueobjects.Email;
import br.com.ntconsult.hotelaria.model.valueobjects.Nome;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Cliente {
	private Nome nome;
	private CPF cpf;
	private Email email;
}
