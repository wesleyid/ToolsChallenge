package com.api.pagamento.converte;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.api.pagamento.dto.CartaoDTO;
import com.api.pagamento.entidade.CartaoCredito;

@Component
public class DtoToCartaoConverte implements Converter<CartaoDTO, CartaoCredito> {

	@Override
	public CartaoCredito convert(CartaoDTO cartaoDTO) {
		CartaoCredito cartao = new CartaoCredito();
		cartao.setDataValidade(cartaoDTO.getDataValidade());
		cartao.setName(cartaoDTO.getNome());
		cartao.setNumero(cartaoDTO.getNumero());
		cartao.setCvv(cartaoDTO.getCvv());
		return cartao;
	}

}
