package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Codigo {
	private final String codigo;

	public Codigo(String pattern, boolean gerarNovo) {
		if(StringUtils.isBlank(pattern)){
			throw new RegraNegocioException("Deve ser informado um codigo!");
		}
		if (gerarNovo) {
			this.codigo = gerarCodigoReserva(pattern);
		} else
			this.codigo = pattern;
	}

	private String gerarCodigoReserva(String pattern) {
		final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		final int CODE_LENGTH = 6;
		SecureRandom random = new SecureRandom();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateTimeString = sdf.format(new Date());
		String seedKey = dateTimeString + pattern.toString();
		random.setSeed(seedKey.hashCode());

		StringBuilder sb = new StringBuilder(CODE_LENGTH);
		for (int i = 0; i < CODE_LENGTH; i++) {
			int index = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}

		return sb.toString();
	}
}
