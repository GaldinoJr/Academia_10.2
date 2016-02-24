package com.example.galdino.academia_102.Dominio;

public class GrupoMuscular extends EntidadeDominio {
	private String nome;
	private int idImage;
	
	
	/*
	 * peito   = 1
	 * costa   = 2
	 * ombro   = 3
	 * biceps  = 4
	 * tricpes = 5
	 * perna   = 6
	 */
	private  String[] vetSgrupos = {"Peito",
		"Costas", "Ombro", "Biceps", "Triceps", "Coxa", "Abdomen", "Panturrilha", "Gluteo" };
	
	private  void orderAlfabeticamenteVetor()
	{
		int nTamanho,
			i,
			comp;
		String sAux;
		Boolean flgHouveTroca = false;
		nTamanho = (vetSgrupos.length)-1;
		do
		{
			flgHouveTroca = false;
			for(i = 0; i < nTamanho; i++)
			{
				comp = vetSgrupos[i].compareTo(vetSgrupos[i+1]); // compara o indice atual com o próximo indice
				if(comp > 0) // Indice atual é maior que o próximo indice?
				{ // Sim, então troca
					sAux = vetSgrupos[i];
					vetSgrupos[i] = vetSgrupos[i+1];
					vetSgrupos[i+1] = sAux;
					i = -1; // recomeça do zero
					flgHouveTroca = true; // indica que houve troca
				}
			}
		}while(flgHouveTroca);	
	}
	
	public String[] getVetorGrupos()
	{
		orderAlfabeticamenteVetor();
		return vetSgrupos;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

}
