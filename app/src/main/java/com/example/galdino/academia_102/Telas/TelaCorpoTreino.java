package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.galdino.academia_102.AndroidItens.RoundAdapter;
import com.example.galdino.academia_102.Dominio.CorGrupos;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.R;

import java.util.ArrayList;

public class TelaCorpoTreino extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private Button btnVirarFotoPrinc,
            btnVirarFotoPrinc2;
    private Intent intent;
    private  String[] vetSgrupos;
    private String[] vetor;
    private ImageView iVprincipal;
    private static final int LIMITE_MINIMO = 50;
    private static final int TOLERANCIA = 0;
    private String grupo,
           // telaAnterior,
            nmTreino,
            idTreino;
    //private ArrayList<String> vetIDExe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_corpo_treino);
        ArrayList<GrupoMuscular> image_details = GetSearchResults();
        //
        btnVirarFotoPrinc = (Button)findViewById(R.id.btnVirarFotoPrinc);
        btnVirarFotoPrinc2 = (Button)findViewById(R.id.btnVirarFotoPrinc2);
        btnVirarFotoPrinc.setOnClickListener(this);
        btnVirarFotoPrinc2.setOnClickListener(this);

        iVprincipal = (ImageView)findViewById(R.id.image);
        if (iVprincipal != null) {
            iVprincipal.setOnTouchListener (this);
        }
        //
        Intent dados = getIntent();
        //telaAnterior = dados.getStringExtra("nmTelaCorrespondente");
        idTreino = dados.getStringExtra("idTreino");
        nmTreino = dados.getStringExtra("nmTreino");
        //Bundle b=this.getIntent().getExtras();
        //vetIDExe = b.getStringArray("exe");
        //vetIDExe = dados.getStringArrayListExtra("exe");
    }

    public ArrayList<GrupoMuscular> GetSearchResults()
    {
        int i, qtdRegistro;
        Integer[] vetIndice;

        GrupoMuscular grupoMuscular;
        grupoMuscular = new GrupoMuscular();
        // Ordena em ordem alfabetica os vetores
        vetSgrupos = grupoMuscular.getVetorGrupos();
        ArrayList<GrupoMuscular> results = new ArrayList<GrupoMuscular>();
        qtdRegistro = vetSgrupos.length;

        vetIndice = new Integer[qtdRegistro];
        for(i = 0; i < qtdRegistro; i++)
        {
            vetIndice[i] = i;
            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNome(vetSgrupos[i]);
            grupoMuscular.setIdImage(i+1);
            results.add(grupoMuscular);
        }// for
        return results;
    }
    public boolean onTouch (View v, MotionEvent ev)
    {
        CorGrupos corGrupos = new CorGrupos();
        boolean handledHere = false;
        final int action = ev.getAction();
        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        int nextImage = -1;			// resource id of the next image to display
        ImageView imageView = (ImageView) findViewById (R.id.image);
        if (imageView == null)
            return false;
        Integer tagNum = (Integer) imageView.getTag ();

        int currentResource = (tagNum == null) ? R.drawable.principal_frente : tagNum.intValue ();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN : // clicou na tela
                if (currentResource == R.drawable.principal_frente) {
                    nextImage = R.drawable.principal_frente_precionar2;
                    handledHere = true;
                }
                else
                {
                    nextImage = R.drawable.principal_tras_precionar;
                    handledHere = true;
                }
                break;

            case MotionEvent.ACTION_UP : // soltou o clique
                int touchColor;

                if(currentResource == R.drawable.principal_frente || currentResource == R.drawable.principal_frente_precionar2)
                    touchColor = getHotspotColor (R.id.image_areas, evX, evY);
                else
                    touchColor = getHotspotColor (R.id.image_areas_tras, evX, evY);

                // VERIFICAR COR TOCADA
                grupo = corGrupos.verificarMusculoTocado(touchColor, TOLERANCIA);
                if(grupo.equals(""))
                {
                    if (currentResource == R.drawable.principal_frente || currentResource == R.drawable.principal_frente_precionar2) // troca a imagem
                        nextImage = R.drawable.principal_frente;
                    else
                        nextImage = R.drawable.principal_tras;

                    handledHere = true;
                    break;
                }
                Exercicio exercicio = new Exercicio();
                exercicio.ordenarVetores(grupo);
                vetor = exercicio.getVetCorrespondente();
                if(vetor == null)
                {
                    Toast.makeText(TelaCorpoTreino.this, "Não à exercicios para este grupo", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    finish();
                }
                //chamarTelaCorrespondeteAoGrupo(vetor, grupo);
                chamarTelaCorrespondeteAoGrupo(grupo);

                handledHere = true;
                break;
            default:
                handledHere = false;
        } // end switch

        if (handledHere) {

            if (nextImage > 0) {
                imageView.setImageResource (nextImage);
                imageView.setTag (nextImage);
            }
        }
        return handledHere;
    }

    public int getHotspotColor (int hotspotId, int x, int y) {
        int teste;
        ImageView img = (ImageView) findViewById (hotspotId);
        if (img == null) {
            Log.d("ImageAreasActivity", "Imagem não encontrada");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d ("ImageAreasActivity", "imagem não foi criada");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                // Testa se vai conseguir pegar a cor da tela a partir do toque
                try
                {
                    teste = hotspots.getPixel(x, y);
                }
                catch(Exception e) // não conseguiu
                {
                    teste = 0; // retorna zero
                }

                return teste;

            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnVirarFotoPrinc || v == btnVirarFotoPrinc2)
        {
            int proximaImagem = -1;
            ImageView imagemAtual = (ImageView) findViewById (R.id.image);
            if(imagemAtual == null) // não tem imagem na tela?
                return; // volta para onde estava
            Integer tagNum = (Integer) imagemAtual.getTag ();
            int idImagemAtual = (tagNum == null) ? R.drawable.principal_frente : tagNum.intValue ();
            int teste = R.drawable.principal_tras;
            if(idImagemAtual ==  R.drawable.principal_frente) // troca a imagem
                proximaImagem = R.drawable.principal_tras;
            else
                proximaImagem = R.drawable.principal_frente;

            if (proximaImagem > 0) {
                imagemAtual.setImageResource(proximaImagem);
                imagemAtual.setTag (proximaImagem);
            }
        }
    }
    private void imageClick(String sGrupo)
    {
        grupo = sGrupo;
        chamarTelaCorrespondeteAoGrupo(sGrupo);
    }

    public void chamarTelaCorrespondeteAoGrupo(String grupo)
    {
        intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaCorpoTreino.this, TelaTreinoGrupo.class);
        intent.putExtra("grupo", grupo);
        //intent.putExtra("nmTelaCorrespondente",telaAnterior);
//        if(TelaTreinoGrupo.class.toString().equals(telaAnterior))
//        {
//            intent.putExtra("idTreino", idTreino);
//            intent.putExtra("nmTreino", nmTreino);
//            //intent.putStringArrayListExtra("exe", (ArrayList<String>) vetIDExe);
//        }
        startActivity(intent); // chama a próxima tela
        finish(); // Encerra a tela atual
    }
//    public void chamarTelaCorrespondeteAoGrupo(String grupo)
//    {
//        intent = new Intent();
//        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
//        intent.setClass(TelaCorpoTreino.this, TelaListaExercicios.class);
//        intent.putExtra("grupo", grupo);
//        intent.putExtra("nmTelaCorrespondente",telaAnterior);
//        if(TelaTreinoExercicio.class.toString().equals(telaAnterior)) {
//            intent.putExtra("idTreino", idTreino);
//            intent.putExtra("nmTreino", nmTreino);
//            intent.putStringArrayListExtra("exe", (ArrayList<String>) vetIDExe);
////            Bundle b=new Bundle();
////            b.putStringArray("exe", vetIDExe);
////            intent.putExtras(b);
//        }
//        startActivity(intent); // chama a próxima tela
//        finish(); // Encerra a tela atual
//    }

    public void onBackPressed() // voltar?
    {

        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
//        if(TelaTreinoExercicio.class.toString().equals(telaAnterior)) {
//            intent.setClass(TelaCorpoTreino.this, TelaTreinoExercicio.class);
//            intent.putExtra("nomeTreino", nmTreino);
//            intent.putExtra("idTreino", idTreino);
//        }
//        else
        intent.setClass(TelaCorpoTreino.this, TelaPrincipalApp.class);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();

    }
}// end class
