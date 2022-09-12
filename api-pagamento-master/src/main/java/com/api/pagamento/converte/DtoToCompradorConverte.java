package com.api.pagamento.converte;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.api.pagamento.dto.CompradorDTO;
import com.api.pagamento.entidade.Comprador;

@Component
public class DtoToCompradorConverte implements Converter<CompradorDTO, Comprador> {

	@Override
	public Comprador convert(CompradorDTO compradorDTO) {
		Comprador comprador = new Comprador();
		comprador.setCpf(compradorDTO.getCpf());
		comprador.setEmail(compradorDTO.getEmail());
		comprador.setNome(compradorDTO.getNome());

		return comprador;
	}

}
