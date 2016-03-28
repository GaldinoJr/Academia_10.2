package com.example.galdino.academia_102.BaseAdapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Galdino on 27/03/2016.
 */
public class BaseAdapterRepeticoes extends BaseAdapter{

    private static ArrayList<String> ALlistaTreino;
    private LayoutInflater l_Inflater;
    private Context context;
    // Construtor 1
    public BaseAdapterRepeticoes(Context context, ArrayList<String> results)
    {
        ALlistaTreino = results;
        l_Inflater = LayoutInflater.from(context);
        //this.listEntDomTreinos = listEntDomTreinos;
        this.context = context;
    }
    // Construtor 2
    public BaseAdapterRepeticoes()
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
        EditText edtRepeticoes;
        Button btnMenosRepeticaoList,
                btnMaisRepeticaoList;
        int id;
    }

    // Vai converter um view para aparecer dentro de outro
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final int linha = position;
        final ViewHolder holder;
        if(convertView == null) // A lista ainda não foi criada
        {
            // cria o inflater que servira para converter para um xml ficar dentro do outro
            convertView = l_Inflater.inflate(R.layout.xml_repeticoes, null);
            holder = new ViewHolder();
            // recebe o conteudo do xml que será um item da listView, associando os objtos da tela com os daqui
            holder.edtRepeticoes = (EditText)convertView.findViewById(R.id.edtRepeticoes1);
            holder.btnMenosRepeticaoList = (Button)convertView.findViewById(R.id.btnMenosRepeticaoList);
            holder.btnMaisRepeticaoList = (Button)convertView.findViewById(R.id.btnMaisRepeticaoList);
            convertView.setTag(holder); // devolve os conteudos
        }
        else // se já foi criada
        {
            holder = (ViewHolder) convertView.getTag(); // Pega o conteudo que já foi enviado
        }
        //
        //holder.txt_itemName.setText(ALlistaTreino.get(position).getNome());
        holder.edtRepeticoes.setTag(position);
       // holder.edtRepeticoes.setId(position);
        holder.edtRepeticoes.setText(ALlistaTreino.get(position));
        holder.btnMenosRepeticaoList.setTag(position);
        holder.btnMaisRepeticaoList.setTag(position);

        //
        //holder.edtRepeticoes.setId(position);
        holder.id = position;
        String repeticao = holder.edtRepeticoes.getText().toString();
        String body = (String) getItem(position);
        if(!TextUtils.isEmpty(repeticao) && repeticao != null && !repeticao.equals(""))
        {
            ALlistaTreino.set(position, repeticao);
        }
        else
        {
            if (ALlistaTreino != null && ALlistaTreino.get(position) != null) {
                holder.edtRepeticoes.setText(ALlistaTreino.get(position));
            } else {
                holder.edtRepeticoes.setText(null);
            }
        }
        holder.edtRepeticoes.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                ALlistaTreino.set(holder.id,arg0.toString());
            }
        });
        holder.btnMaisRepeticaoList.setId(Integer.parseInt(ALlistaTreino.get(position)));
        holder.btnMenosRepeticaoList.setId(Integer.parseInt(ALlistaTreino.get(position)));
        // Add listener for edit text
//        holder.edtRepeticoes
//                .setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                    /*
//                     * When focus is lost save the entered value for
//                     * later use
//                     */
//                        if (!hasFocus) {
//                            //int itemIndex = v.getId();
//                            int itemIndex = (Integer) v.getTag();
//                            String repeticao = ((EditText) v).getText()
//                                    .toString();
//                            ALlistaTreino.set(itemIndex, repeticao);
//                        }
//                        }
//                });

        //
        return convertView;
    }
}
