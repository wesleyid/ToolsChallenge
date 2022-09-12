package com.api.pagamento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.pagamento.entidade.Boleto;

@Repository
public interface BoletoRepositorio extends JpaRepository<Boleto, Long> {

}
