package com.api.pagamento.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.api.pagamento.entidade.FormaPagamento;

public class PagamentoDTO {
	

	@NotNull
	private Long idPagamento;
	@NotNull
	private Double valor;
	@NotNull
	private Date dataHora;
	@NotNull
	private FormaPagamento forma;
	@NotNull
	private CartaoDTO cartao;
	@NotNull
	private EstabelecimentoDTO estabelecimento;

	public PagamentoDTO(int idPagamento, Double valor, Date dataHora, FormaPagamento forma, CartaoDTO cartao, EstabelecimentoDTO estabelecimento) {
		super();
		this.idPagamento = (long) idPagamento;
		this.valor = valor;
		this.dataHora = dataHora;
		this.forma = forma;
		this.cartao = cartao;
		this.estabelecimento = estabelecimento;

	}

	public PagamentoDTO() {

	}
	
	public Long getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public FormaPagamento getForma() {
		return forma;
	}

	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}

	public CartaoDTO getCartao() {
		return cartao;
	}

	public void setCartao(CartaoDTO cartao) {
		this.cartao = cartao;
	}
	
	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	@Override
	public String toString() {
		return "PagamentoDTO [idPagamento=" + idPagamento +", valor=" + valor + ",dataHora=" + dataHora +", forma=" + forma + ", cartao=" + cartao + ", estabelecimento=" + estabelecimento +"]";
	}
}
