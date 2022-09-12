package com.api.pagamento.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.api.pagamento.entidade.FormaPagamento;
import com.api.pagamento.entidade.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RespostaPagamentoDTO {

	@JsonInclude(Include.NON_NULL)
	private Integer numeroCartao;
	
	private Long idPagamento;
	
	private double valor;
	
	private Date dataHora;
	
	@NotNull
	private EstabelecimentoDTO estabelecimento;
	
	@JsonInclude(Include.NON_NULL)
	private Integer nsu;
	
	@JsonInclude(Include.NON_NULL)
	private Integer codigoAutorizacao;
	
	@JsonInclude(Include.NON_NULL)
	private Integer numeroBoleto;
	
	private Status status;
	
	private FormaPagamento forma;
	
	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Integer getNsu() {
		return nsu;
	}

	public void setNsu(Integer nsu) {
		this.nsu = nsu;
	}

	public Integer getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(Integer codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	public Long getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public FormaPagamento getForma() {
		return forma;
	}

	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Integer numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public Integer getNumeroBoleto() {
		return numeroBoleto;
	}

	public void setNumeroBoleto(Integer numeroBoleto) {
		this.numeroBoleto = numeroBoleto;
	}

	@Override
	public String toString() {
		return "RespostaPagamentoDTO [idPagamento=" + idPagamento + ", valor=" + valor + ", dataHora=" + dataHora +", estabelecimento=" + estabelecimento +", forma=" + forma
				+", nsu=" + nsu +", codigoAutorizacao=" + codigoAutorizacao
				+ ", status=" + status + ", numeroCartao=" + numeroCartao + ",  numeroBoleto=" + numeroBoleto + ", getIdPagamento()=" + getIdPagamento()
				+ ", getValor()=" + getValor() + ", getForma()=" + getForma() + ", getStatus()=" + getStatus()
				+ ", getNumeroBoleto()=" + getNumeroBoleto() + ", getNumeroCartao()=" + getNumeroCartao() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
