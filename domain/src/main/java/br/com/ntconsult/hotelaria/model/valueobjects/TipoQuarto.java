package br.com.ntconsult.hotelaria.model.valueobjects;

import br.com.ntconsult.hotelaria.exception.RegraNegocioException;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Getter
public class TipoQuarto {
	private String nome;
	private String descricao;

	public TipoQuarto(String nome, String descricao) {
		if (StringUtils.isBlank(nome)) {
			throw new RegraNegocioException("O nome do quarto deve ser informado!");
		}
		this.nome = nome;
		this.descricao = descricao;
	}

}
