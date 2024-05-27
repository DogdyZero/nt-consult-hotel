package br.com.ntconsult.hotelaria.exception;

public class RegraNegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public RegraNegocioException(String mensagem) {
		super(mensagem);
	}
}
