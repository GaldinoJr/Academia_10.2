package com.example.galdino.academia_102.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galdino.academia_102.Dominio.CorGrupos;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

import java.util.ArrayList;

public class GrupoMuscularBaseAdapter extends BaseAdapter {
	private static ArrayList<GrupoMuscular> grupoMuscularArrayList;
	private LayoutInflater l_Inflater;
	
	// Da pra ordenar os ids, pelos ids
	private Integer[] imgid;
	// Construtor 1
	public GrupoMuscularBaseAdapter(Context context, ArrayList<GrupoMuscular> results)
	{
		CorGrupos corGrup = new CorGrupos();
		imgid = corGrup.getCorID();
		orderAlfabeticamenteIdImagem();
		grupoMuscularArrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}
	// Construtor 2
	public GrupoMuscularBaseAdapter()
	{
		
	}
	private void orderAlfabeticamenteIdImagem()
	{
		int aux, 
			nTamanho,
			i;
		Boolean flgHouveTroca = false;
		nTamanho = imgid.length;
		do
		{
			flgHouveTroca = false;
			for(i = 0; i < nTamanho-1; i++)
			{
				if(imgid[i] > imgid[i+1]) // Atual é maior que o próximo?
				{ // sim, então troca
					aux = imgid[i];
					imgid[i] = imgid[i+1];
					imgid[i+1] = aux;
					flgHouveTroca = true; // indica que houve troca
				}
			}
		}while(flgHouveTroca);	
	}

	// Conta quantos registros tem no array
	public int getCount() 
	{
		return grupoMuscularArrayList.size();
	}
	
	// Encontra a pocisão no array
	public Object getItem(int position)
	{
		return grupoMuscularArrayList.get(position);
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
	
	 }
	
	// Vai converter um view para aparecer dentro de outro
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		if(convertView == null) // A lista ainda não foi criada
		{
			// cria o inflater que servira para converter para um xml ficar dentro do outro
			convertView = l_Inflater.inflate(R.layout.xmlgrupo, null);
			holder = new ViewHolder();
			// recebe o conteudo do xml que sera um item da listView, associando os objtos da tela com os daqui
			holder.itemImage = (ImageView)convertView.findViewById(id.imgExercicio);
			holder.txt_itemName = (TextView)convertView.findViewById(id.txtGrupoName);
			convertView.setTag(holder); // devolve os conteudos
		}
		else // se já foi criada
		{
			holder = (ViewHolder) convertView.getTag(); // Pega o conteudo que já foi enviado
		}
		// 
		holder.txt_itemName.setText(grupoMuscularArrayList.get(position).getNome());
		holder.itemImage.setImageResource(imgid[grupoMuscularArrayList.get(position).getIdImage() - 1]);
		return convertView;
	}
}
