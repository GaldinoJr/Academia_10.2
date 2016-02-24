package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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

public class TelaPrincipal extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    //private String grupo;
    private ImageView imgIconeAbdomen,
            imgIconeBiceps,
            imgIconeCostas,
            imgIconeCoxa,
            imgIconeGluteo,
            imgIconeOmbro,
            imgIconePanturrilha,
            imgIconePeito,
            imgIconeTriceps;
    private Button btnVirarFotoPrinc,
            btnVirarFotoPrinc2;
    private Intent intent;
    private  String[] vetSgrupos;
    private String[] vetor;
    private float X1, X2, deltaX;
    private ImageView iVprincipal;
    private static final int LIMITE_MINIMO = 50;
    private static final int TOLERANCIA = 0;
    private String grupo;
    float Y1, Y2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        ArrayList<GrupoMuscular> image_details = GetSearchResults();
        //
        btnVirarFotoPrinc = (Button)findViewById(R.id.btnVirarFotoPrinc);
        btnVirarFotoPrinc2 = (Button)findViewById(R.id.btnVirarFotoPrinc2);
        btnVirarFotoPrinc.setOnClickListener(this);
        btnVirarFotoPrinc2.setOnClickListener(this);
        imgIconeAbdomen = (ImageView)findViewById(R.id.imgIconeAbdomen);
        imgIconeBiceps = (ImageView)findViewById(R.id.imgIconeBiceps);
        imgIconeCostas = (ImageView)findViewById(R.id.imgIconeCostas);
        imgIconeCoxa = (ImageView)findViewById(R.id.imgIconeCoxa);
        imgIconeGluteo = (ImageView)findViewById(R.id.imgIconeGluteo);
        imgIconeOmbro = (ImageView)findViewById(R.id.imgIconeOmbro);
        imgIconePanturrilha = (ImageView)findViewById(R.id.imgIconePanturrilha);
        imgIconePeito = (ImageView)findViewById(R.id.imgIconePeito);
        imgIconeTriceps = (ImageView)findViewById(R.id.imgIconeTriceps);
        //
        imgIconeAbdomen.setOnClickListener(this);
        imgIconeBiceps.setOnClickListener(this);
        imgIconeCostas.setOnClickListener(this);
        imgIconeCoxa.setOnClickListener(this);
        imgIconeGluteo.setOnClickListener(this);
        imgIconeOmbro.setOnClickListener(this);
        imgIconePanturrilha.setOnClickListener(this);
        imgIconePeito.setOnClickListener(this);
        imgIconeTriceps.setOnClickListener(this);
        //
        RoundAdapter ra = new RoundAdapter();
        imgIconeAbdomen.setImageDrawable(ra.RoundImageGrupo("Abdomen",this));
        imgIconeBiceps.setImageDrawable(ra.RoundImageGrupo("Biceps",this));
        imgIconeCostas.setImageDrawable(ra.RoundImageGrupo("Costas",this));
        imgIconeCoxa.setImageDrawable(ra.RoundImageGrupo("Coxa",this));
        imgIconeGluteo.setImageDrawable(ra.RoundImageGrupo("Gluteo",this));
        imgIconeOmbro.setImageDrawable(ra.RoundImageGrupo("Ombro",this));
        imgIconePanturrilha.setImageDrawable(ra.RoundImageGrupo("Panturrilha",this));
        imgIconePeito.setImageDrawable(ra.RoundImageGrupo("Peito",this));
        imgIconeTriceps.setImageDrawable(ra.RoundImageGrupo("Triceps",this));
//		Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.icone_abdomen);
//		RoundImage roundedImage  = new RoundImage(bm);
//		imgIconeAbdomen.setImageDrawable(roundedImage);

        //
        iVprincipal = (ImageView)findViewById(R.id.image);
        if (iVprincipal != null) {
            iVprincipal.setOnTouchListener (this);
        }
        //

        //final ListView lv1 = (ListView) findViewById(R.id.list);

        //lv1.setAdapter(new GrupoMuscularBaseAdapter(this, image_details));

        //lv1.setOnItemClickListener(new OnItemClickListener()
//		{
//			@Override
//			public void onItemClick(AdapterView<?> a, View v, int position, long id)
//			{
        //		Object o = lv1.getItemAtPosition(position);
//
//				GrupoMuscular obj_itemDetails = (GrupoMuscular)o;
//				grupo = obj_itemDetails.getNome();
//				Exercicio exercicio = new Exercicio();
//				exercicio.ordenarVetores(grupo);
//				vetor = exercicio.getVetCorrespondente();
//				if(vetor == null)
//				{
//					Toast.makeText(TelaPrincipal.this, "Não à exercicios para este grupo", Toast.LENGTH_SHORT);
//					 Intent intent = new Intent(Intent.ACTION_MAIN);
//					 finish();
//				}
//            	chamarTelaCorrespondeteAoGrupo(vetor, grupo);
//			}
//		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }


    public void chamarTelaCorrespondeteAoGrupo(String[] vetExe, String grupo)
    {
        Bundle b;

        b=new Bundle();
        b.putStringArray("exe", vetExe);
        intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaPrincipal.this, TelaListaExercicios.class);
        intent.putExtras(b);
        intent.putExtra("grupo", grupo);
        startActivity(intent); // chama a próxima tela
        finish(); // Encerra a tela atual
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
                    Toast.makeText(TelaPrincipal.this, "Não à exercicios para este grupo", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    finish();
                }
                chamarTelaCorrespondeteAoGrupo(vetor, grupo);


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
        else if(v == imgIconeAbdomen)
            imageClick("Abdomen");
        else if(v == imgIconeBiceps)
            imageClick("Biceps");
        else if(v == imgIconeCostas)
            imageClick("Costas");
        else if(v == imgIconeCoxa)
            imageClick("Coxa");
        else if(v == imgIconeGluteo)
            imageClick("Gluteo");
        else if(v == imgIconeOmbro)
            imageClick("Ombro");
        else if(v == imgIconePanturrilha)
            imageClick("Panturrilha");
        else if(v == imgIconePeito)
            imageClick("Peito");
        else if(v == imgIconeTriceps)
            imageClick("Triceps");
    }
    private void imageClick(String sGrupo)
    {
        grupo = sGrupo;
        Exercicio exercicio = new Exercicio();
        exercicio.ordenarVetores(grupo);
        vetor = exercicio.getVetCorrespondente();
        if(vetor == null)
        {
            Toast.makeText(TelaPrincipal.this, "Não à exercicios para este grupo", Toast.LENGTH_SHORT);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            finish();
        }
        chamarTelaCorrespondeteAoGrupo(vetor, grupo);
    }

    public void onBackPressed() // voltar?
    {

        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaPrincipal.this, TelaPrincipalApp.class);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();

    }
}// end class