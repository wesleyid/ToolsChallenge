package com.api.pagamento.converte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.api.pagamento.dto.ClienteDTO;
import com.api.pagamento.entidade.Cliente;
import com.api.pagamento.exception.ClienteNaoEncontradoException;
import com.api.pagamento.servico.ClienteServico;

@Component
public class DtoToClienteConverte implements Converter<ClienteDTO, Cliente> {

	@Autowired
	private ClienteServico clienteServico;

	@Override
	public Cliente convert(ClienteDTO clienteDTO) {
		Cliente cliente = clienteServico.buscarCliente(clienteDTO);
		if (!ObjectUtils.isEmpty(cliente)) {
			return cliente;
		} else {
			throw new ClienteNaoEncontradoException(clienteDTO.getIdCliente());
		}
	}
}
