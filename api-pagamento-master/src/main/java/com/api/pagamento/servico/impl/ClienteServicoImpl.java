package com.api.pagamento.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pagamento.dto.ClienteDTO;
import com.api.pagamento.entidade.Cliente;
import com.api.pagamento.repositorio.ClienteRepositorio;
import com.api.pagamento.servico.ClienteServico;

@Service
public class ClienteServicoImpl implements ClienteServico {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Override
	public Cliente buscarCliente(ClienteDTO clienteDTO) {
		return clienteRepositorio.findById(clienteDTO.getIdCliente()).orElse(null);
	}

}