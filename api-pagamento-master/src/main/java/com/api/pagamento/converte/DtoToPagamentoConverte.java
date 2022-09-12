package com.api.pagamento.converte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.api.pagamento.dto.PagamentoDTO;
import com.api.pagamento.entidade.FormaPagamento;
import com.api.pagamento.entidade.Pagamento;
import com.api.pagamento.exception.CartaoNuloException;

@Component
public class DtoToPagamentoConverte implements Converter<PagamentoDTO, Pagamento> {

	@Autowired
	private DtoToCartaoConverte cartaoConverte;

	@Override
	public Pagamento convert(PagamentoDTO pagamentoDTO) {
		Pagamento pagamento = new Pagamento();
		if (FormaPagamento.PARCELADO_LOJA.equals(pagamentoDTO.getForma()) ) {
			if (ObjectUtils.isEmpty(pagamentoDTO.getCartao())) {
				throw new CartaoNuloException();
			}
		if (FormaPagamento.PARCELADO_EMISSOR.equals(pagamentoDTO.getForma())) {
				if (ObjectUtils.isEmpty(pagamentoDTO.getCartao())) {
					throw new CartaoNuloException();
				}	
			pagamento.setCartao(cartaoConverte.convert(pagamentoDTO.getCartao()));
		}
		}

		pagamento.setForma(pagamentoDTO.getForma());
		pagamento.setValor(pagamentoDTO.getValor());
		return pagamento;
	}

}
