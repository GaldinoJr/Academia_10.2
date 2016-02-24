package com.example.galdino.academia_102.Dominio;


import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Documento {
	private Context context;
	public Documento(Context context)
	{
		this.context = context;
	}
	
	
	public String carregarArquivoTxt(String grupoMuscular, String nomeExercicio, String assuntoArquivo)
	{
		String nomeArquivo, texto;
		nomeExercicio = montarNomeArquivo(nomeExercicio);
		nomeArquivo = "DocumentosTxt/" + assuntoArquivo + "/" + grupoMuscular + "/txt" + assuntoArquivo + nomeExercicio + ".txt";
		texto = lerArquivo(context, nomeArquivo);
		return texto;
	}
	
	
	private String montarNomeArquivo(String nomeExercicio)
	{
		String nome = "";
		int tamanho,
		i;
		tamanho = nomeExercicio.length();
		for(i = 3; i < tamanho; i++) // Roda toda  string
		{
			nome += nomeExercicio.charAt(i);
		}
		return nome;
	}
	public static String lerArquivo(Context context, String nomeArq)
	{

	    // Stringbuilder que sera utilizado no processamento
	    StringBuilder sb = new StringBuilder();
	    
	    // Adiciona o nome do Arquivo
	    sb.append(nomeArq);

	    try 
	    {

	        // Obtem o contexto definido globalmente e abre o arquivo do Assets
	        InputStream is = context.getAssets().open(nomeArq);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String s = null;

	        // Instancia do stringbuilder que sera utilizada para leitura do arquivo
	        sb = new StringBuilder();

	        while ((s = br.readLine()) != null)
	            sb.append(s + "\r\n");

	        br.close();
	        is.close();
	        return sb.toString();

	    } catch (IOException e1) {
	    	return null;
	        //throw new RuntimeException(e1); //fecha a aplica��o
	    	
	    }
	}

}
