package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Getter
public class Email {
	private String endereco;

	public Email(String endereco) {
		if (!isValidEmail(endereco)) {
			throw new RegraNegocioException("Endereço de email inválido: " + endereco);
		}
		this.endereco = endereco;
	}

	private boolean isValidEmail(String email) {
		if(StringUtils.isBlank(email))
			return false;
		final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		return pat.matcher(email).matches();
	}
}
