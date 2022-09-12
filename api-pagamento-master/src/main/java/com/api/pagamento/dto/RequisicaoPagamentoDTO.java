package com.api.pagamento.dto;

import javax.validation.constraints.NotNull;

public class RequisicaoPagamentoDTO {

	@NotNull
	private PagamentoDTO pagamento;
	@NotNull
	private ClienteDTO cliente;
	@NotNull
	private CompradorDTO comprador;
	@NotNull
	private EstabelecimentoDTO estabelecimento;

	public PagamentoDTO getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoDTO pagamento) {
		this.pagamento = pagamento;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public CompradorDTO getComprador() {
		return comprador;
	}

	public void setComprador(CompradorDTO comprador) {
		this.comprador = comprador;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
}
