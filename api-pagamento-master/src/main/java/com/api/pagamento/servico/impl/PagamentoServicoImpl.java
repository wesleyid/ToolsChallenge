package com.api.pagamento.servico.impl;

import java.time.LocalDate;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.api.pagamento.dto.RequisicaoPagamentoDTO;
import com.api.pagamento.dto.RespostaEstornoDTO;
import com.api.pagamento.dto.RespostaPagamentoDTO;
import com.api.pagamento.entidade.Boleto;
import com.api.pagamento.entidade.CartaoCredito;
import com.api.pagamento.entidade.Cliente;
import com.api.pagamento.entidade.Comprador;
import com.api.pagamento.entidade.FormaPagamento;
import com.api.pagamento.entidade.Pagamento;
import com.api.pagamento.entidade.Status;
import com.api.pagamento.repositorio.PagamentoRepositorio;
import com.api.pagamento.servico.BoletoServico;
import com.api.pagamento.servico.CartaoServico;
import com.api.pagamento.servico.CompradorServico;
import com.api.pagamento.servico.PagamentoServico;

@Service
public class PagamentoServicoImpl implements PagamentoServico {

	@Autowired
	private PagamentoRepositorio pagamentoRepositorio;

	@Autowired
	private CartaoServico cartaoServico;

	@Autowired
	private CompradorServico compradorServico;

	@Autowired
	private BoletoServico boletoServico;

	@Autowired
	private ConversionService conversionService;

	@Override
	public RespostaPagamentoDTO realizarPagamento(RequisicaoPagamentoDTO requisicaoPagamentoDTO) {

		Pagamento pag = conversionService.convert(requisicaoPagamentoDTO.getPagamento(), Pagamento.class);

		// Converte cliente e atribui no pagamento
		Cliente cliente = conversionService.convert(requisicaoPagamentoDTO.getCliente(), Cliente.class);
		pag.setCliente(cliente);

		// Verifica se existe o comprador
		Comprador comprador = compradorServico.buscarCompradorCPF(requisicaoPagamentoDTO.getComprador().getCpf());
		if (!ObjectUtils.isEmpty(comprador)) {
			pag.setComprador(comprador);
		} else {
			Comprador compradorNovo = conversionService.convert(requisicaoPagamentoDTO.getComprador(), Comprador.class);
			pag.setComprador(compradorServico.salvarComprador(compradorNovo));
		}

		// Verifica a forma de pagamento
		if (FormaPagamento.PARCELADO_LOJA.equals(pag.getForma())) {
			CartaoCredito cc = (CartaoCredito) pag.getCartao();
			cartaoServico.validarCartao(cc);
			cc.setTipoBandeira(cartaoServico.identificarBandeira(cc.getNumero()));
			pag.setCartao(cartaoServico.salvarCartao(cc));
			pag.setStatus(Status.AUTORIZADO);
			
		} else if (FormaPagamento.PARCELADO_EMISSOR.equals(pag.getForma())) {
				CartaoCredito cc = (CartaoCredito) pag.getCartao();
				cartaoServico.validarCartao(cc);
				cc.setTipoBandeira(cartaoServico.identificarBandeira(cc.getNumero()));
				pag.setCartao(cartaoServico.salvarCartao(cc));
				pag.setStatus(Status.AUTORIZADO);	

		} else if (FormaPagamento.AVISTA_BOLETO.equals(pag.getForma())) {
			Boleto boleto = boletoServico.gerarBoleto();
			pag.setBoleto(boletoServico.salvarBoleto(boleto));
			pag.setStatus(Status.AUTORIZADO);
		}
		pag.setDataCadastro(LocalDate.now());

		Pagamento pagamento = pagamentoRepositorio.save(pag);

		RespostaPagamentoDTO respostaPagamentoDTO = new RespostaPagamentoDTO();
		respostaPagamentoDTO.setIdPagamento(pagamento.getIdPagamento());
		respostaPagamentoDTO.setValor(pagamento.getValor());
		respostaPagamentoDTO.setForma(pagamento.getForma());
		respostaPagamentoDTO.setStatus(pagamento.getStatus());
		if (FormaPagamento.AVISTA_BOLETO.equals(pagamento.getForma())) {
			respostaPagamentoDTO.setNumeroBoleto(pagamento.getBoleto().getNumeroBoleto());
		}
		return respostaPagamentoDTO;
	}

	@Override
	public Pagamento buscarPagamento(Long idPagamento) {
		Pagamento pag = pagamentoRepositorio.findById(idPagamento).orElse(null);
		if (ObjectUtils.isEmpty(pag)) {
			return null;
		}
		String cpfNovo = this.esconderCPF(pag.getComprador().getCpf());
		pag.getComprador().setCpf(cpfNovo);
		return pag;
	}

	@Override
	public String esconderCPF(String cpf) {
		if (ObjectUtils.isEmpty(cpf)) {
			return null;
		}
		String cpfNovo = "";
		for (int x = 0; x < cpf.length() - 2; x++) {
			cpfNovo += "*";
		}
		return cpfNovo + cpf.substring(cpf.length() - 2);
	}

	@Override
	public RespostaEstornoDTO realizarEstorno(RespostaEstornoDTO requisicaoEstornoDTO) {
		
		Pagamento pag = (Pagamento) pagamentoRepositorio.findAll();
		if (ObjectUtils.isEmpty(pag)) {
			return requisicaoEstornoDTO;
		}
		pagamentoRepositorio.delete(pag);
		return requisicaoEstornoDTO;
	}
	
	@Override
	public boolean removerPagamento(Long idPagamento) {
		Pagamento pag = pagamentoRepositorio.findById(idPagamento).orElse(null);
		if (ObjectUtils.isEmpty(pag)) {
			return false;
		}
		pagamentoRepositorio.delete(pag);
		return true;
	}

}
