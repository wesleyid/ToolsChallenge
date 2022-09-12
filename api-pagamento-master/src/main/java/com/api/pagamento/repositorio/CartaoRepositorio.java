package com.api.pagamento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.pagamento.entidade.CartaoCredito;

@Repository
public interface CartaoRepositorio extends JpaRepository<CartaoCredito, Long> {

}
