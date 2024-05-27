package br.com.ntconsult.hotelaria.mapper;

import br.com.ntconsult.hotelaria.adapters.web.dto.ClienteDto;
import br.com.ntconsult.hotelaria.model.Cliente;
import br.com.ntconsult.hotelaria.model.valueobjects.CPF;
import br.com.ntconsult.hotelaria.model.valueobjects.Email;
import br.com.ntconsult.hotelaria.model.valueobjects.Nome;
import br.com.ntconsult.hotelaria.persistence.entities.ClienteEntity;

public class ClienteMapper {
	public static ClienteEntity toEntity(Cliente cliente){
		return ClienteEntity.builder()
				.email(cliente.getEmail().getEndereco())
				.nome(cliente.getNome().getValor())
				.cpf(cliente.getCpf().getNumero())
				.build();
	}

	public static Cliente toDomain(ClienteEntity clienteEntity){
		return Cliente.builder()
				.cpf(new CPF(clienteEntity.getCpf()))
				.email(new Email(clienteEntity.getEmail()))
				.nome(new Nome(clienteEntity.getNome()))
				.build();
	}

	public static ClienteDto toDto(Cliente cliente){
		return ClienteDto.builder()
				.nome(cliente.getNome().getValor())
				.cpf(cliente.getCpf().getNumero())
				.email(cliente.getEmail().getEndereco())
				.build();
	}

	public static Cliente toDomain(ClienteDto dto){
		return Cliente.builder()
				.email(new Email(dto.getEmail()))
				.nome(new Nome(dto.getNome()))
				.cpf(new CPF(dto.getCpf()))
				.build();
	}
}
