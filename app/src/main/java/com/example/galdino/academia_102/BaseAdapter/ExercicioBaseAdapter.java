package com.example.galdino.academia_102.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;
import com.example.galdino.academia_102.Telas.TelaFichaListExercicios;

import java.util.ArrayList;
import java.util.List;

public class ExercicioBaseAdapter  extends BaseAdapter {
	private static ArrayList<Exercicio> ExercicioArrayList;
	private LayoutInflater l_Inflater;
	// Da pra ordenar os ids, pelos ids
	//private Integer[] imgId;
	private boolean[] selecionados; //Para evitar o erro do checkBox
	private Integer indTela;
	// 1 = TelaListaExercicio
	// 2 = Tela para add exercício no treino
	// 3 = TelaTreino
	private Context context;
	//private String[] vetIDExe;
	private	ArrayList<String> vetIDExe;
	// Construtor 1
	public ExercicioBaseAdapter(Context context, ArrayList<Exercicio> results, boolean[] selecionados, Integer indTela, ArrayList<String> vetIDExe)
	{
		ExercicioArrayList = results;
		this.selecionados = selecionados;
		this.indTela = indTela;
		l_Inflater = LayoutInflater.from(context);
		this.context = context;
		this.vetIDExe = vetIDExe;
	}
	// Construtor 2
	public ExercicioBaseAdapter()
	{
		
	}
//	private Integer[] orderAlfabeticamenteIdImagem(Integer[]imgid)
//	{
//		int aux,
//			nTamanho,
//			i;
//		Boolean flgHouveTroca = false;
//		nTamanho = imgid.length;
//		do
//		{
//			flgHouveTroca = false;
//			for(i = 0; i < nTamanho-1; i++)
//			{
//				if(imgid[i] > imgid[i+1]) // Atual é maior que o próximo?
//				{ // sim, ent�o troca
//					aux = imgid[i];
//					imgid[i] = imgid[i+1];
//					imgid[i+1] = aux;
//					flgHouveTroca = true; // indica que houve troca
//				}
//			}
//		}while(flgHouveTroca);
//		return imgid;
//	}

	// Conta quantos registros tem no array
	public int getCount() 
	{
		return ExercicioArrayList.size();
	}
	
	// Encontra a pocição no array
	public Object getItem(int position)
	{
		return ExercicioArrayList.get(position);
	}
	
	// Devolve a posição
	public long getItemId(int position) 
	{
		return position;
	}
	
	// Cria os elementos para receber o conteudo da tela
	static class ViewHolder {
		TextView txt_itemName;
	 	ImageView itemImage;
		CheckBox chkExercicioSelecionado;
		TextView txtSerie;
	 }
	
	// Vai converter um view para aparecer dentro de outro
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		if(convertView == null) // A lista ainda não foi criada
		{
			// cria o inflater que servira para converter para um xml ficar dentro do outro
			convertView = l_Inflater.inflate(R.layout.xml_exercicio, null);
			holder = new ViewHolder();
			// recebe o conteudo do xml que ser� um item da listView, associando os objtos da tela com os daqui
			holder.itemImage = (ImageView)convertView.findViewById(id.imgExercicioo);
			holder.txt_itemName = (TextView)convertView.findViewById(id.txtNomeExercicio);
			holder.chkExercicioSelecionado = (CheckBox)convertView.findViewById(id.chkExercicioSelecionado);
			holder.txtSerie = (TextView) convertView.findViewById(id.txtSerie);
			//holder.chkExercicioSelecionado.setChecked(false);
			convertView.setTag(holder); // devolve os conteudos
		}
		else // se já foi criada
		{
			holder = (ViewHolder) convertView.getTag(); // Pega o conteudo que já foi enviado
		}
		if(indTela == 2) // Tela para add exercício no treino
		{
			holder.chkExercicioSelecionado.setVisibility(View.VISIBLE);
			holder.chkExercicioSelecionado.setChecked(false);
			if( vetIDExe.contains(ExercicioArrayList.get(position).getID()))
				selecionados[position] = true;
//			for(int i = 0; i < vetIDExe.contains(); i++)
//				if(ExercicioArrayList.get(position).getID().equals(vetIDExe[i]))
//					selecionados[position] = true;

			if (selecionados[position])
				holder.chkExercicioSelecionado.setChecked(true);
			else
				holder.chkExercicioSelecionado.setChecked(false);
		}
		if(indTela == 3) // Tela de treino?
		{
			holder.txtSerie.setVisibility(View.VISIBLE);
		}
		// 
		holder.txt_itemName.setText(ExercicioArrayList.get(position).getNome());
		int indiceFoto = context.getResources().getIdentifier(ExercicioArrayList.get(position).getNomeLogicoFoto(), "drawable", context.getPackageName());
		holder.itemImage.setImageResource(indiceFoto);
		//holder.itemImage.setImageResource(imgId[ExercicioArrayList.get(position).getIdImage() - 1]); // ** SO FUNCIONA ENQUANTO A IMAGEM ESTIVER SENDO COLOCADA EM ORDEM ALFABETICA NA CLASSE
		holder.chkExercicioSelecionado.setTag(position);
		return convertView;
	}

}
