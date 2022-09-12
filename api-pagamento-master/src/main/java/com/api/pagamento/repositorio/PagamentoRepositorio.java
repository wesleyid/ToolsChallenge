package com.api.pagamento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.pagamento.entidade.Pagamento;

@Repository
public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long>{

}
