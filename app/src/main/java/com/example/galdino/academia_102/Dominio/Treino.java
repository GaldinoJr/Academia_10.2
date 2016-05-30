package com.example.galdino.academia_102.Dominio;

import java.util.List;

public class Treino extends EntidadeDominio
{
	// DEFINES
	// Definição do código dos objetivos do treino
	public static final String DF_CD_OBJ_DEFINICAO 		= "0";
	public static final String DF_CD_OBJ_FORCA 			= "1";
	public static final String DF_CD_OBJ_HIPERTROFIA 	= "2";
	public static final String DF_CD_OBJ_RESISTENCIA	= "3";
	// Definição do codigo dos níveis do treino
	public static final String DF_CD_NIVEL_INICIANTE 	= "0";
	public static final String DF_CD_NIVEL_INTERMEDIARIO= "1";
	public static final String DF_CD_NIVEL_AVANCADO 	= "2";
	public static final String getDescricaoNivel(int indNivel)
	{
		String dsNivel = null;
		switch (String.valueOf(indNivel))
		{
			case DF_CD_NIVEL_INICIANTE:
				dsNivel = "Iniciante";
				break;
			case DF_CD_NIVEL_INTERMEDIARIO:
				dsNivel = "Intermediário";
				break;
			case DF_CD_NIVEL_AVANCADO:
				dsNivel = "Avançado";
				break;
			default:
				dsNivel = "N/A";
				break;
		}
		return dsNivel;
	}
	public  static final String getDescricaoObjetivo(int indObj)
	{
		String dsNivel = null;
		switch (String.valueOf(indObj))
		{
			case DF_CD_OBJ_DEFINICAO:
				dsNivel = "Definição";
				break;
			case DF_CD_OBJ_FORCA:
				dsNivel = "Força";
				break;
			case DF_CD_OBJ_HIPERTROFIA:
				dsNivel = "Hipertrofia";
				break;
			case DF_CD_OBJ_RESISTENCIA:
				dsNivel = "Resistência";
				break;
			default:
				dsNivel = "N/A";
				break;
		}
		return dsNivel;
	}

	// VARIÁVEIS
	private String nome,
					Descricao,
					dsNomeFoto;
	private Integer idGrupo,
					fgCarga,
					indTipoTreino,
					indNivel;
	// Parametros utilizados apenas para consultas
	private List<String> listaCodigosObjParaBusca,
						listaCodigosNivelParaBusca;
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
	public List<String> getListaCodigosObjParaBusca() {
		return listaCodigosObjParaBusca;
	}
	public List<String> getListaCodigosNivelParaBusca() {
		return listaCodigosNivelParaBusca;
	}
	public String getDsNomeFoto() {
		return dsNomeFoto;
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
	public void setListaCodigosObjParaBusca(List<String> listaCodigosObjParaBusca) {
		this.listaCodigosObjParaBusca = listaCodigosObjParaBusca;
	}
	public void setListaCodigosNivelParaBusca(List<String> listaCodigosNivelParaBusca) {
		this.listaCodigosNivelParaBusca = listaCodigosNivelParaBusca;
	}
	public void setDsNomeFoto(String dsCaminhoFoto) {
		this.dsNomeFoto = dsCaminhoFoto;
	}
}
