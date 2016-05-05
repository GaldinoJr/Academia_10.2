package com.example.galdino.academia_102.Telas.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.AndroidItens.RoundAdapter;
import com.example.galdino.academia_102.BaseAdapter.ExercicioBaseAdapter;
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.R;

import com.example.galdino.academia_102.SideMenu.ScreenShotable;
import com.example.galdino.academia_102.Telas.TelaExercicio;
import com.example.galdino.academia_102.Telas.TelaTreinoExercicio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Galdino on 23/04/2016.
 */
public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String ABDOMEN = "Abdomen";
    public static final String BICEPS = "Biceps";
    public static final String COSTAS = "Costas";
    public static final String COXA = "Coxa";
    public static final String GLUTEO = "Gluteo";
    public static final String OMBRO = "Ombro";
    public static final String PANTURRILHA = "Panturrilha";
    public static final String PEITO = "Peito";
    public static final String TRICEPS = "Triceps";

    private View containerView;
    protected ImageView mImageView;
    protected String res;
    private Bitmap bitmap;
    //
    private List<EntidadeDominio> listEntDomExercicio;
    private static boolean[] itemChecked;
    private GrupoMuscular grupoMuscular;
    private Exercicio exercicio;
    private String grupo;
    private Intent dados;
    private List<EntidadeDominio> listEntDom;
    private ArrayList<String> vetIDExe;
    //
    public static ContentFragment newInstance(String resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getString(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
       // mImageView = (ImageView) rootView.findViewById(R.id.image_content);
        //mImageView.setClickable(true);
        //mImageView.setFocusable(true);
        //mImageView.setImageResource(res);
        //
        //Intent dados;
        // recebe os dados da tela 1
        //dados = getIntent();
        if(vetIDExe == null)
            vetIDExe = new ArrayList<>();
        int indTela = 1;
        // Recebe os dados da tela anterior
        //grupo = dados.getStringExtra("grupo");

        grupo = res;

        //telaAnterior = dados.getStringExtra("nmTelaCorrespondente");
//        if(TelaTreinoExercicio.class.toString().equals(telaAnterior)) {
//            indTela = 2;
//            idTreino = dados.getStringExtra("idTreino");
//            nmTreino = dados.getStringExtra("nmTreino");
//            vetIDExe = dados.getStringArrayListExtra("exe");
//            if(vetIDExe == null)
//                vetIDExe = new ArrayList<>();
//            fBtnConfirmarExercicio.setVisibility(View.VISIBLE);
//        }
        // Carregar o id do grupo no banco
        grupoMuscular = new GrupoMuscular();
        grupoMuscular.setNome(grupo);
        listEntDom = grupoMuscular.operar(getActivity(), true, Controler.DF_CONSULTAR, grupoMuscular);
        int idGrupo = 0;
        if(listEntDom != null) {
            grupoMuscular = (GrupoMuscular)listEntDom.get(0);
            idGrupo = Integer.parseInt(grupoMuscular.getID());
        }
        else
        {
            Toast.makeText(getActivity(),"Grupo muscular " + grupo + " não existe na base de dados.",Toast.LENGTH_LONG).show();
            //return;
        }
        // Carrega os exercícios do grupo correspondente
        exercicio = new Exercicio();
        exercicio.setIdGrupo(idGrupo);
        listEntDomExercicio = exercicio.operar(getActivity(),true,Controler.DF_CONSULTAR,exercicio);
        if(listEntDomExercicio == null) {
            Toast.makeText(getActivity(),"Não existe exercícios do grupo muscular " + grupo + " na base de dados.",Toast.LENGTH_LONG).show();
            //return;
        }

        ArrayList<Exercicio> image_details2 = GetSearchResults();

        final ListView lvExercicio2 = (ListView)rootView.findViewById(R.id.lvExercicios2);

        lvExercicio2.setAdapter(new ExercicioBaseAdapter(getActivity(), image_details2, itemChecked, indTela, vetIDExe, null, null));

        lvExercicio2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                String nome = "",
                        exe = "";

                Object o = lvExercicio2.getItemAtPosition(position);

                Exercicio obj_itemDetails = (Exercicio)o;
                exe = obj_itemDetails.getNome();
                nome = encontrarNome(exe);
                Intent intent = new Intent(getActivity(), TelaExercicio.class);

                intent.putExtra("grupo", grupo);
                intent.putExtra("nome",nome);
                intent.putExtra("exe", exe);
                startActivity(intent); // chama a próxima tela
                //finish(); // Não faz para não perder as info nem precisar carregar de novo.
            }
        });
        //
        return rootView;
    }

    @Override
    public void takeScreenShot()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    synchronized (this)
                    {
                        wait(5000);
                        if(getActivity() != null)
                        {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                                            containerView.getHeight(), Bitmap.Config.ARGB_8888);
                                    Canvas canvas = new Canvas(bitmap);
                                    containerView.draw(canvas);
                                    ContentFragment.this.bitmap = bitmap;
                                }
                            });
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            };
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
    //
    private ArrayList<Exercicio> GetSearchResults()
    {
        int i, qtdRegistro;
        ArrayList<Exercicio> results = new ArrayList<Exercicio>();
        qtdRegistro = listEntDomExercicio.size();
        itemChecked = new boolean[qtdRegistro];

        for(i = 0; i < qtdRegistro; i++)
        {
            Exercicio e = (Exercicio)listEntDomExercicio.get(i);
            e.setIdImage(i+1);
            results.add(e);
        }// for
        return results;
    }
    public String encontrarNome(String nome)
    {
        int tamanho,
                i;
        String gifNome = "";
        tamanho = nome.length(); // recebe a quantidade de caracteres da palavra
        for(i = 0; i< tamanho; i++) // Roda toda  string
        {
            if(nome.charAt(i) == ' ') // Achou espaço?
            {
                gifNome += String.valueOf(nome.charAt(i +1)).toUpperCase(); // sim pula ele e muda o próximo caracter para maiusculo concatenando na nova string
                i++; // incrementa o indice
            }
            else
                gifNome += nome.charAt(i); // apenas concatena na string
        }
        gifNome = "gif" + gifNome; // Concatena o nome obtido com gif na frente, a fim de terminar o nome
        return gifNome;
    }
    //
}
