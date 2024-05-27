package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Getter
public class Contato {
	private String numero;

	public Contato(String contato) {
		if (!isContatoValido(contato)) {
			throw new RegraNegocioException("O contato deve ser informado ou não está no formato (11)[9]?1111-1111!");
		}
		this.numero = contato;
	}

	private boolean isContatoValido(String contato) {
		if (StringUtils.isBlank(contato))
			return false;
		final String contatoRegex = "^\\([\\d]{2}\\)[9]?[\\d]{4}-[\\d]{4}$";
		Pattern pat = Pattern.compile(contatoRegex);
		return pat.matcher(contato)
				.matches();
	}
}
