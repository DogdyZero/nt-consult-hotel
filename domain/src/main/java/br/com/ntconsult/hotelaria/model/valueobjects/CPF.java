package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;

@Getter
public class CPF {
	private String numero;

	public CPF(String numero) {
		if (!isValidCPF(numero)) {
			throw new RegraNegocioException("CPF inválido: " + numero);
		}
		this.numero = numero;
	}

	private static boolean isValidCPF(String cpf) {
		cpf = cpf.replaceAll("\\D", ""); // Remove non-digits
		if (cpf.length() != 11 || new HashSet<>(Arrays.asList(cpf.split(""))).size() <= 1) {
			return false;
		}

		int[] weightsFirstDigit = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] weightsSecondDigit = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

		int firstDigit = calculateDigit(cpf.substring(0, 9), weightsFirstDigit);
		int secondDigit = calculateDigit(cpf.substring(0, 9) + firstDigit, weightsSecondDigit);

		return cpf.equals(cpf.substring(0, 9) + firstDigit + secondDigit);
	}

	private static int calculateDigit(String str, int[] weights) {

		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			sum += Character.getNumericValue(str.charAt(i)) * weights[i];
		}
		int remainder = sum % 11;
		return remainder < 2 ? 0 : 11 - remainder;
	}
}
