package com.example.galdino.academia_102.BaseAdapter;

import android.content.Context;
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

import java.util.ArrayList;

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
	private Integer fgCarga; // Indica se o treino é de carga ou não(para habilitar o excluir/editar
	private Context context;
	//private String[] vetIDExe;
	private	ArrayList<String> vetIDExe;
	private ArrayList<String> vetRepeticaoExercicio;
	private Integer[] vetOrdemExercicio;

	// Construtor 2
	public ExercicioBaseAdapter()
	{

	}
	// Construtor 1
	public ExercicioBaseAdapter(Context context, ArrayList<Exercicio> results, boolean[] selecionados, Integer indTela, ArrayList<String> vetIDExe, ArrayList<String> vetRepeticaoExercicio, Integer fgCarga, Integer[] vetOrdemExercicio)
	{
		ExercicioArrayList = results;
		this.selecionados = selecionados;
		this.indTela = indTela;
		l_Inflater = LayoutInflater.from(context);
		this.context = context;
		this.vetIDExe = vetIDExe;
		this.vetRepeticaoExercicio = vetRepeticaoExercicio;
		this.fgCarga = fgCarga;
		this.vetOrdemExercicio = vetOrdemExercicio;
	}

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
		TextView txtNrOrdem;
		ImageView imgExcluirExercicioTreino;
		ImageView imgAddRepeticaoExercicio;
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
			// recebe o conteudo do xml que será um item da listView, associando os objtos da tela com os daqui
			holder.itemImage = (ImageView)convertView.findViewById(id.imgExercicioo);
			holder.imgExcluirExercicioTreino = (ImageView)convertView.findViewById(id.imgExcluirExercicioTreino);
			holder.imgAddRepeticaoExercicio = (ImageView)convertView.findViewById(id.imgAddRepeticaoExercicio);
			holder.txt_itemName = (TextView)convertView.findViewById(id.txtNomeExercicio);
			holder.txtSerie = (TextView) convertView.findViewById(id.txtSerie);
			holder.chkExercicioSelecionado = (CheckBox)convertView.findViewById(id.chkExercicioSelecionado);
			holder.txtNrOrdem = (TextView)convertView.findViewById(id.txtNrOrdem);
			//holder.chkExercicioSelecionado.setChecked(false);
			convertView.setTag(holder); // devolve os conteudos
		}
		else // se já foi criada
		{
			holder = (ViewHolder) convertView.getTag(); // Pega o conteudo que já foi enviado
		}
		if(indTela == 2) // Tela para add exercício no treino
		{
			holder.txtNrOrdem.setVisibility(View.VISIBLE);
			holder.chkExercicioSelecionado.setVisibility(View.VISIBLE);
			holder.chkExercicioSelecionado.setChecked(false);
			// Carrega os exercícios que estavam gravados
			if(vetIDExe != null)
				if (vetIDExe.contains(ExercicioArrayList.get(position).getID()))
					selecionados[position] = true;

			holder.txtNrOrdem.setText("");
			if (selecionados[position]) {
				holder.chkExercicioSelecionado.setChecked(true);
				if (vetOrdemExercicio[position] != null)
					holder.txtNrOrdem.setText(String.valueOf(vetOrdemExercicio[position]));
			}
			else
				holder.chkExercicioSelecionado.setChecked(false);
		}
		if(indTela == 3) // Tela de treino?
		{
			holder.txtSerie.setVisibility(View.VISIBLE);
			holder.txtSerie.setText(vetRepeticaoExercicio.get(position));
			if(fgCarga == 0) // Só habilita edição se não for carga
			{
				holder.imgExcluirExercicioTreino.setVisibility(View.VISIBLE);
				holder.imgExcluirExercicioTreino.setTag(position); // para pegar a linha quando for executado o click do botão
				holder.imgAddRepeticaoExercicio.setVisibility(View.VISIBLE);
				holder.imgAddRepeticaoExercicio.setTag(position); // para pegar a linha quando for executado o click do botão
			}
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
