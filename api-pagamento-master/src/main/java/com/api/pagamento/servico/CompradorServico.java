package com.api.pagamento.servico;

import com.api.pagamento.entidade.Comprador;

public interface CompradorServico {

	public Comprador buscarComprador(Comprador comprador);

	public Comprador buscarCompradorCPF(String cpf);
	
	public Comprador salvarComprador(Comprador comprador);
}
