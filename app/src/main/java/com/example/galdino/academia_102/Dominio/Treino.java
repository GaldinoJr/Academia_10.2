package com.example.galdino.academia_102.Dominio;

public class Treino extends EntidadeDominio {

	private String nome,
					Descricao;
	private Integer idGrupo,
					fgCarga,
					indTipoTreino,
					indNivel;

	// Gets
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return Descricao;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public Integer getFgCarga() {
		return fgCarga;
	}
	public Integer getIndTipoTreino() {
		return indTipoTreino;
	}
	public Integer getIndNivel() {
		return indNivel;
	}

	// Sets
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public void setFgCarga(Integer fgCarga) {
		this.fgCarga = fgCarga;
	}
	public void setIndTipoTreino(Integer indTipoTreino) {
		this.indTipoTreino = indTipoTreino;
	}
	public void setIndNivel(Integer indNivel) {
		this.indNivel = indNivel;
	}
}
