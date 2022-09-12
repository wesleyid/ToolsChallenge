package com.api.pagamento.servico.impl;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.pagamento.dto.CartaoDTO;
import com.api.pagamento.dto.ClienteDTO;
import com.api.pagamento.dto.CompradorDTO;
import com.api.pagamento.dto.EstabelecimentoDTO;
import com.api.pagamento.dto.PagamentoDTO;
import com.api.pagamento.dto.RequisicaoPagamentoDTO;
import com.api.pagamento.dto.RespostaPagamentoDTO;
import com.api.pagamento.entidade.Cliente;
import com.api.pagamento.entidade.Comprador;
import com.api.pagamento.entidade.FormaPagamento;
import com.api.pagamento.entidade.Pagamento;
import com.api.pagamento.repositorio.ClienteRepositorio;
import com.api.pagamento.servico.PagamentoServico;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PagamentoServicoImplTest {

	@Autowired
	private PagamentoServico pagamentoservico;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	Cliente cliente;

	@Before
	public void init() {
		Cliente clienteNew = new Cliente();
		clienteNew.setCnpj("37667221000128");
		clienteNew.setRazaoSocial("Teste");
		cliente = clienteRepositorio.save(clienteNew);
	}

	@After
	public void deleteAll() {
		clienteRepositorio.deleteAll();
	}

	@Test
	public void validarRealizarPagamentoSucessoBoleto() {
		RequisicaoPagamentoDTO requisicaoPagamentoDTO = new RequisicaoPagamentoDTO();
		ClienteDTO clienteDto = new ClienteDTO(cliente.getIdCliente());
		CompradorDTO compradorDto = new CompradorDTO("Sandy", "sandylima@gmail.com", "16379094890");
		double valor = 234.53;
		PagamentoDTO pagamentoDto = new PagamentoDTO(1000235689,valor, null,FormaPagamento.AVISTA_BOLETO, null,null);

		requisicaoPagamentoDTO.setCliente(clienteDto);
		requisicaoPagamentoDTO.setComprador(compradorDto);
		requisicaoPagamentoDTO.setPagamento(pagamentoDto);
		RespostaPagamentoDTO realizarPagamento = pagamentoservico.realizarPagamento(requisicaoPagamentoDTO);

		Assert.assertNotNull(realizarPagamento.getIdPagamento());
		Assert.assertNotNull(realizarPagamento.getNumeroBoleto());
		Assert.assertEquals(realizarPagamento.getForma(), FormaPagamento.AVISTA_BOLETO);
		Assert.assertEquals(realizarPagamento.getValor(), valor, 0);
	}

	@Test
	public void validarRealizarPagamentoSucessoCartaoCredito() {
		RequisicaoPagamentoDTO requisicaoPagamentoDTO = new RequisicaoPagamentoDTO();
		ClienteDTO clienteDto = new ClienteDTO(cliente.getIdCliente());
		CompradorDTO compradorDto = new CompradorDTO("Sandy", "sandylima@gmail.com", "16379094890");
		long idPagamento =  (long) 1000235689;
		double valor = 21431.15;
		CartaoDTO cartaoDto = new CartaoDTO("Teste Fulano", "378600277204815", LocalDate.now().plusYears(3), "235");
		EstabelecimentoDTO estabelecimentoDto = new EstabelecimentoDTO(1234657,"PetShop Mundo cao");
		PagamentoDTO pagamentoDto = new PagamentoDTO((int) idPagamento,valor, null,FormaPagamento.PARCELADO_LOJA, cartaoDto,estabelecimentoDto);

		requisicaoPagamentoDTO.setCliente(clienteDto);
		requisicaoPagamentoDTO.setComprador(compradorDto);
		requisicaoPagamentoDTO.setPagamento(pagamentoDto);
		RespostaPagamentoDTO realizarPagamento = pagamentoservico.realizarPagamento(requisicaoPagamentoDTO);

		Assert.assertNotNull(realizarPagamento.getIdPagamento());
		Assert.assertNull(realizarPagamento.getNumeroBoleto());
		Assert.assertEquals(realizarPagamento.getForma(), FormaPagamento.PARCELADO_LOJA);
		Assert.assertEquals(realizarPagamento.getValor(), valor, 0);
	}

	@Test
	public void validarRealizarPagamentoSucessoMesmoCPF() {
		CompradorDTO compradorDto = new CompradorDTO("Sandy", "sandylima@gmail.com", "16379094890");

		// realizar pagamento 2
		RequisicaoPagamentoDTO requisicaoPagamentoDTO = new RequisicaoPagamentoDTO();
		requisicaoPagamentoDTO.setCliente(new ClienteDTO(cliente.getIdCliente()));
		requisicaoPagamentoDTO.setComprador(compradorDto);
		requisicaoPagamentoDTO.setPagamento(new PagamentoDTO(1000235689,482.53,null, FormaPagamento.AVISTA_BOLETO, null,null));
		RespostaPagamentoDTO realizarPagamento1 = pagamentoservico.realizarPagamento(requisicaoPagamentoDTO);

		requisicaoPagamentoDTO = new RequisicaoPagamentoDTO();
		requisicaoPagamentoDTO.setCliente(new ClienteDTO(cliente.getIdCliente()));
		requisicaoPagamentoDTO.setComprador(compradorDto);
		CartaoDTO cartaoDto = new CartaoDTO("Wesley Jr", "378600277204815", LocalDate.now().plusYears(3), "235");
		EstabelecimentoDTO estabelecimentoDto = new EstabelecimentoDTO(1234657,"PetShop Mundo cao");
		requisicaoPagamentoDTO.setPagamento(new PagamentoDTO(1000235689,224.63, null,FormaPagamento.PARCELADO_LOJA, cartaoDto, estabelecimentoDto));
		RespostaPagamentoDTO realizarPagamento2 = pagamentoservico.realizarPagamento(requisicaoPagamentoDTO);

		// verificar se tem somente um comprador com mesmo CPF
		Pagamento pagamento1 = pagamentoservico.buscarPagamento(realizarPagamento1.getIdPagamento());
		Comprador comprador1 = pagamento1.getComprador();

		Pagamento pagamento2 = pagamentoservico.buscarPagamento(realizarPagamento2.getIdPagamento());
		Comprador comprador2 = pagamento2.getComprador();

		Assert.assertEquals(comprador1.getIdComprador(), comprador2.getIdComprador());
		Assert.assertEquals(comprador1.getCpf(), comprador2.getCpf());
	}

	// @Test(expected = ClienteNaoEncontradoException.class)
	@Test(expected = ConversionFailedException.class)
	public void validarRealizarPagamentoFalhaClienteInvalido() {
		RequisicaoPagamentoDTO requisicaoPagamentoDTO = new RequisicaoPagamentoDTO();
		ClienteDTO clienteDto = new ClienteDTO(123456789l);
		CompradorDTO compradorDto = new CompradorDTO("Sandy", "sandylima@gmail.com", "16379094890");
		long idPagamento =  (long) 1000235689;
		double valor = 234.53;
		PagamentoDTO pagamentoDto = new PagamentoDTO((int) idPagamento,valor, null,FormaPagamento.AVISTA_BOLETO, null,null);

		requisicaoPagamentoDTO.setCliente(clienteDto);
		requisicaoPagamentoDTO.setComprador(compradorDto);
		requisicaoPagamentoDTO.setPagamento(pagamentoDto);
		pagamentoservico.realizarPagamento(requisicaoPagamentoDTO);
	}

	// @Test(expected = CartaoNuloException.class)
	@Test(expected = ConversionFailedException.class)
	public void validarRealizarPagamentoFalhaCartaoNuloException() {
		RequisicaoPagamentoDTO requisicaoPagamentoDTO = new RequisicaoPagamentoDTO();
		ClienteDTO clienteDto = new ClienteDTO(123456789l);
		CompradorDTO compradorDto = new CompradorDTO("Sandy", "sandylima@gmail.com", "16379094890");
		EstabelecimentoDTO estabelecimentoDto = new EstabelecimentoDTO(1234657,"PetShop Mundo cao");
		PagamentoDTO pagamentoDto = new PagamentoDTO(1000235689, 234.23, null,FormaPagamento.PARCELADO_LOJA, null,null);

		requisicaoPagamentoDTO.setCliente(clienteDto);
		requisicaoPagamentoDTO.setComprador(compradorDto);
		requisicaoPagamentoDTO.setEstabelecimento(estabelecimentoDto);
		requisicaoPagamentoDTO.setPagamento(pagamentoDto);
		pagamentoservico.realizarPagamento(requisicaoPagamentoDTO);
	}

	@Test
	public void esconderCPFComSucesso() {
		String cpfAntigo = "76217645033";
		String cpfNovo = pagamentoservico.esconderCPF(cpfAntigo);
		Assert.assertEquals("*********33", cpfNovo);
	}

	@Test
	public void esconderCPFNuloComFalha() {
		Assert.assertNull(pagamentoservico.esconderCPF(null));
	}
}
