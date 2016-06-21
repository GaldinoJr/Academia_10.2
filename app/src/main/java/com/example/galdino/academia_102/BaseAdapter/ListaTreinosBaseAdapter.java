package com.example.galdino.academia_102.BaseAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galdino.academia_102.AndroidItens.RoundImage;
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
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
    //private List<EntidadeDominio> listEntDomTreinos;
    private Context context;
    private boolean fgTelaMeusTreinos;
    private List<GrupoMuscular> lGrupoMuscular;
    // Construtor 1
    public ListaTreinosBaseAdapter(ListaTreinosBaseAdapterCLS cls)
    {
        ALlistaTreino = cls.getAlTreinos();
       // this.listEntDomTreinos = cls.getListEntDomTreinos();
        this.context = cls.getContext();
        l_Inflater = LayoutInflater.from(context);
        fgTelaMeusTreinos = cls.isFgTelaMeusTreinos();
        lGrupoMuscular = cls.getlGrupoMuscular();
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
        ImageView imgMarkTreino;
        ImageView imgGrupo;
        ImageView icObj;
        ImageView ic_nivel;
        TextView txtObj;
        TextView txtNivel;
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
            holder.txtObj = (TextView)convertView.findViewById(R.id.txtObj);
            holder.txtNivel = (TextView)convertView.findViewById(R.id.txtNivel);
            holder.imgMarkTreino = (ImageView)convertView.findViewById(R.id.imgMarkTreino);
            holder.imgGrupo = (ImageView)convertView.findViewById(R.id.imgGrupo);
            holder.icObj = (ImageView)convertView.findViewById(R.id.icObj);
            holder.ic_nivel = (ImageView)convertView.findViewById(R.id.ic_nivel);

            convertView.setTag(holder); // devolve os conteudos
        }
        else // se já foi criada
        {
            holder = (ViewHolder) convertView.getTag(); // Pega o conteudo que já foi enviado
        }
        if(ALlistaTreino.get(position).getIndTipoTreino() != null)
        {
            String obj = ALlistaTreino.get(position).getDescricaoObjetivo(ALlistaTreino.get(position).getIndTipoTreino());
            holder.txtObj.setText(obj.substring(0,3)+ ".");
            holder.icObj.setVisibility(View.VISIBLE);
        }
        
        if(ALlistaTreino.get(position).getIndNivel() != null) {
            String nivel = ALlistaTreino.get(position).getDescricaoNivel(ALlistaTreino.get(position).getIndNivel());
            holder.txtNivel.setText(nivel.substring(0,4)+ ".");
            holder.ic_nivel.setVisibility(View.VISIBLE);
        }
        
        if(ALlistaTreino.get(position).getFgTreinando() != null && (!fgTelaMeusTreinos))
            if(ALlistaTreino.get(position).getFgTreinando() == 1)
                holder.imgMarkTreino.setVisibility(View.VISIBLE);
        
        if(fgTelaMeusTreinos)
        {
            if(lGrupoMuscular != null)
            {
                for(GrupoMuscular grupo : lGrupoMuscular)
                {
                    if(Integer.parseInt(grupo.getID()) == ALlistaTreino.get(position).getIdGrupo())
                    {
                        holder.imgGrupo.setVisibility(View.VISIBLE);
                        String nmFotoGrupo = "ic_" + grupo.getNome().toLowerCase() + "_lista_sm";
                        int indiceFoto = context.getResources().getIdentifier(nmFotoGrupo, "drawable", context.getPackageName());
                        holder.imgGrupo.setImageResource(indiceFoto);
                        break;
                    }
                }
            }
        }
        //
        holder.txt_itemName.setText(ALlistaTreino.get(position).getNome());
        holder.itemImage.setTag(position);
        return convertView;
    }
}
