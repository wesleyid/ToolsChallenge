package com.api.pagamento.servico.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.pagamento.dto.ClienteDTO;
import com.api.pagamento.entidade.Cliente;
import com.api.pagamento.repositorio.ClienteRepositorio;
import com.api.pagamento.servico.ClienteServico;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteServicoImplTest {

	@Autowired
	private ClienteServico clienteServico;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Test
	public void validarBuscarClienteComSucesso() {
		Cliente cliente = clienteRepositorio.save(new Cliente("PetShop Mundo cao", "1232144663447687"));
		Assert.assertNotNull(cliente);
		
		Cliente buscarCliente = clienteServico.buscarCliente(new ClienteDTO(cliente.getIdCliente()));
		Assert.assertNotNull(buscarCliente);
		Assert.assertEquals(cliente.getIdCliente(), buscarCliente.getIdCliente());

	}
}
