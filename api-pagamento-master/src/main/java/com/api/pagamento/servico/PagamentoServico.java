package com.api.pagamento.servico;

import com.api.pagamento.dto.RequisicaoPagamentoDTO;
import com.api.pagamento.dto.RespostaEstornoDTO;
import com.api.pagamento.dto.RespostaPagamentoDTO;
import com.api.pagamento.entidade.Pagamento;

public interface PagamentoServico {

	public RespostaPagamentoDTO realizarPagamento(RequisicaoPagamentoDTO requisicaoPagamentoDTO);

	public Pagamento buscarPagamento(Long idPagamento);
	
	public String esconderCPF(String cpf);
	
	public RespostaEstornoDTO realizarEstorno(RespostaEstornoDTO requisicaoEstornoDTO);
	
	public boolean removerPagamento(Long idPagamento);
}
