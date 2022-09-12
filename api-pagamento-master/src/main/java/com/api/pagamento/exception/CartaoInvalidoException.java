package com.api.pagamento.exception;

import com.api.pagamento.entidade.CartaoCredito;

public class CartaoInvalidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartaoCredito cartaoCredito;

	public CartaoInvalidoException(CartaoCredito cartaoCredito) {
		super();
		this.cartaoCredito = cartaoCredito;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

}
