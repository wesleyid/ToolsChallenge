package com.api.pagamento.servico;

import com.api.pagamento.entidade.Boleto;

public interface BoletoServico {

	public Boleto salvarBoleto(Boleto boleto);

	public Boleto gerarBoleto();
}
