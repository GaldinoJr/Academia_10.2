package com.example.galdino.academia_102.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Galdino on 05/03/2016.
 */
public class ListaTreinosBaseAdapter extends BaseAdapter {
    private static ArrayList<Treino> ALlistaTreino;
    private LayoutInflater l_Inflater;
    private List<EntidadeDominio> listEntDomTreinos;
    // Da pra ordenar os ids, pelos ids
    //private Integer[] imgId;
    private Integer imgId;
    private Context context;
    // Construtor 1
    public ListaTreinosBaseAdapter(Context context, ArrayList<Treino> results, List<EntidadeDominio> listEntDomTreinos)
    {
        imgId = R.drawable.ic_close_black_24dp;
        ALlistaTreino = results;
        l_Inflater = LayoutInflater.from(context);
        this.listEntDomTreinos = listEntDomTreinos;
        this.context = context;
    }
    // Construtor 2
    public ListaTreinosBaseAdapter()
    {

    }

    // Conta quantos registros tem no array
    public int getCount()
    {
        return ALlistaTreino.size();
    }

    // Encontra a pocição no array
    public Object getItem(int position)
    {
        return ALlistaTreino.get(position);
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
        final int linha = position;
        ViewHolder holder;
        if(convertView == null) // A lista ainda não foi criada
        {
            // cria o inflater que servira para converter para um xml ficar dentro do outro
            convertView = l_Inflater.inflate(R.layout.xml_treino, null);
            holder = new ViewHolder();
            // recebe o conteudo do xml que será um item da listView, associando os objtos da tela com os daqui
            holder.itemImage = (ImageView)convertView.findViewById(R.id.imgExcluirTreino);
            holder.txt_itemName = (TextView)convertView.findViewById(R.id.txtNomeTreino);
            convertView.setTag(holder); // devolve os conteudos
        }
        else // se já foi criada
        {
            holder = (ViewHolder) convertView.getTag(); // Pega o conteudo que já foi enviado
        }
        //
        holder.txt_itemName.setText(ALlistaTreino.get(position).getNome());
      //  holder.itemImage.setImageResource(imgId[ALlistaTreino.get(position).getIdImage() - 1]);
        holder.itemImage.setImageResource(imgId);
        holder.itemImage.setTag(position);
        return convertView;
    }
}
