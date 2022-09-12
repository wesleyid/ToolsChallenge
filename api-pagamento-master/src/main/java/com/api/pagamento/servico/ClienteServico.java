package com.api.pagamento.servico;

import com.api.pagamento.dto.ClienteDTO;
import com.api.pagamento.entidade.Cliente;

public interface ClienteServico {

	public Cliente buscarCliente(ClienteDTO clienteDTO);
	
	
}
