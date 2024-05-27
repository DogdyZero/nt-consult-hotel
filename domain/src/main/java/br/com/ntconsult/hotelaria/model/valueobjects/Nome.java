package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Getter
public class Nome {
	private String valor;
	public Nome(String valor) {
		if (!nomeEhValido(valor)) {
			throw new RegraNegocioException("Nome inválido: " + valor);
		}
		this.valor = valor;
	}

	private boolean nomeEhValido(String email) {
		if(StringUtils.isBlank(email))
			return false;
		String nomeRegex = "^[\\p{L} ]+$";
		Pattern pat = Pattern.compile(nomeRegex);
		return pat.matcher(email).matches();
	}
}
