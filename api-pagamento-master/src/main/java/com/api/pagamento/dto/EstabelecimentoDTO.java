package com.api.pagamento.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class EstabelecimentoDTO {
	
	@NotNull
	private Long id;
	@NotEmpty
	private String nomeEstabelecimento;
	
	
	public EstabelecimentoDTO(int id, String nomeEstabelecimento) {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}
	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

	@Override
	public String toString() {
		return "EstabelecimentoDTO [id=" + id + ", nomeEstabelecimento=" + nomeEstabelecimento + "]";
	}
	
	
}
