package com.example.galdino.academia_102.Dominio;

public class Treino extends EntidadeDominio {

	private String nome;
	private Integer idGrupo;

	// Gets
	public String getNome() {
		return nome;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	// Sets
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
}
