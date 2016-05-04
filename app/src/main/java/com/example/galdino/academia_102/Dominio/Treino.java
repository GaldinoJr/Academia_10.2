package com.example.galdino.academia_102.Dominio;

public class Treino extends EntidadeDominio {

	private String nome;
	private Integer idGrupo,
					fgCarga;

	// Gets
	public String getNome() {
		return nome;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public Integer getFgCarga() {
		return fgCarga;
	}

	// Sets
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public void setFgCarga(Integer fgCarga) {
		this.fgCarga = fgCarga;
	}
}
