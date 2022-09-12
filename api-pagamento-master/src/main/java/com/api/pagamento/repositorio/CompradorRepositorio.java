package com.api.pagamento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.pagamento.entidade.Comprador;

@Repository
public interface CompradorRepositorio extends JpaRepository<Comprador, Long> {

	public Comprador findByCpf(String cpf);
}
