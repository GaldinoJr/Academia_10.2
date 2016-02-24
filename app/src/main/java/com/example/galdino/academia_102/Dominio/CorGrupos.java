package com.example.galdino.academia_102.Dominio;

import android.graphics.Color;

import com.example.galdino.academia_102.R;


public class CorGrupos {
		// cores das mascaras
		  private static final int AMARELO = -69120;    // Ombro 	
		  private static final int AZUL = -16735256;    // Coxa
		  private static final int VERDE = -14438067;   // Panturrilha
		  private static final int VERMELHO = -1238236; // Peito
		  private static final int MAGENTA = -6487985;  // Triceps
		  private static final int ROSA = -1931655;	   // Abdomen
		  private static final int CINZA = -1119286;    // Biceps
		  private static final int MARROM = -3241622;   // Costas
		  private static final int LARANJA = -32986;	   // Gluteo
	public String verificarMusculoTocado(int corTocada, int tolerancia)
	{
		String grupo;
		   // Verificar a cor que foi tocada
	       if(verificarCor(AMARELO, corTocada, tolerancia))
	    	   grupo = "Ombro";
	       else if(verificarCor(AZUL, corTocada, tolerancia))
	    	   grupo = "Coxa";
	       else if(verificarCor(VERDE, corTocada, tolerancia))
	    	   grupo = "Panturrilha";
	       else if(verificarCor(VERMELHO, corTocada, tolerancia))
	    	   grupo = "Peito";
	       else if(verificarCor(MAGENTA, corTocada, tolerancia))
	    	   grupo = "Triceps";
	       else if(verificarCor(CINZA, corTocada, tolerancia))
	    	   grupo = "Biceps";
	       else if(verificarCor(ROSA, corTocada, tolerancia))
	    	   grupo = "Abdomen";
	       else if(verificarCor(MARROM, corTocada, tolerancia))
	    	   grupo = "Costas";
	       else if(verificarCor(LARANJA, corTocada, tolerancia))
	    	   grupo = "Gluteo";
	       else 
	    	   grupo = "";
		   return grupo;
	}
	/*
	* @param color1 int
	 * @param color2 int
	 * @param tolerance int - tolerancia máxima permitida entre as cores RGB
	 * @return boolean
	 */

		private boolean verificarCor (int cor1, int cor2, int tolerancia) {
			if ((int) Math.abs (Color.red(cor1) - Color.red(cor2)) > tolerancia )
				return false;
			if ((int) Math.abs (Color.green(cor1) - Color.green(cor2)) > tolerancia )
				return false;
			if ((int) Math.abs (Color.blue(cor1) - Color.blue(cor2)) > tolerancia )
				return false;
			return true;
		} // end match
		
	
	  
//	private Integer cor_abdomen = R.drawable.cor_abdomen,
//	 cor_biceps = R.drawable.cor_biceps,
//	 cor_costa = R.drawable.cor_costa,
//	 cor_coxa = R.drawable.cor_coxa,
//	 cor_gluteo = R.drawable.cor_gluteo,
//	 cor_ombro = R.drawable.cor_ombro,
//	 cor_panturrilha = R.drawable.cor_panturrilha,
//	 cor_peito = R.drawable.cor_peito,
//	 cor_triceps = R.drawable.cor_triceps;
//

	private Integer cor_abdomen = R.drawable.icone_abdomen,
			cor_biceps = R.drawable.icone_biceps,
			cor_costa = R.drawable.icone_costas,
			cor_coxa = R.drawable.icone_coxa,
			cor_gluteo = R.drawable.icone_gluteo,
			cor_ombro = R.drawable.icone_ombro,
			cor_panturrilha = R.drawable.icone_panturilha,
			cor_peito = R.drawable.icone_peito,
			cor_triceps = R.drawable.icone_triceps;

	private Integer[] corID =
	{
			cor_abdomen, 
			cor_biceps, 
			cor_costa, 
			cor_coxa, 
			cor_gluteo,
			cor_ombro,
			cor_panturrilha, 
			cor_peito, 
			cor_triceps
	};

	public Integer verificarCorGrupo(String grupo)
	{
		if(grupo.equals("Peito"))  // Peito?
    	{
			return cor_peito;	
    	}
    	if(grupo.equals("Costas")) // Costa?
    	{
    		return cor_costa;
    	}
    	if(grupo.equals("Ombro"))  // Ombro
    	{
    		return cor_ombro;
    	}
    	if(grupo.equals("Biceps")) // Biceps?
    	{
    		return cor_biceps;	
    	}
    	if(grupo.equals("Triceps")) // Triceps?
    	{
    		return cor_triceps;
    	}
    	if(grupo.equals("Coxa")) // Perna?
    	{
    		return cor_coxa;
    	}
    	if(grupo.equals("Panturrilha"))
    	{
    		return cor_panturrilha;
    	}
    	if(grupo.equals("Abdomen")) // Abdomen?
    	{
    		return cor_abdomen;
    	}
    	if(grupo.equals("Gluteo"))
    	{
    		return cor_gluteo;
    	}
    	else
    		return null;
		
	}

	public Integer[] getCorID() {
		return corID;
	}
	
}
