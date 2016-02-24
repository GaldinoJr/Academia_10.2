package com.example.galdino.academia_102.AndroidItens;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 18/02/2016.
 */
public class RoundAdapter {
    private Bitmap bm;
    private  RoundImage roundedImage;
    public RoundImage RoundImageGrupo(String grupo, Context context)
    {
        if(grupo.equals("Abdomen")) // Abdomen?
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_abdomen);
        }
        else if(grupo.equals("Biceps")) // Biceps?
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_biceps);
        }
        else if(grupo.equals("Costas")) // Costa?
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_costas);
        }
        else if(grupo.equals("Coxa")) // Perna?
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_coxa);
        }
        else if(grupo.equals("Gluteo"))
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_gluteo);
        }
        else if(grupo.equals("Ombro"))  // Ombro
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_ombro);
        }
        else if(grupo.equals("Panturrilha"))
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_panturilha);
        }
        else if(grupo.equals("Peito"))  // Peito?
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_peito);
        }
        else if(grupo.equals("Triceps")) // Triceps?
        {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_triceps);
        }
        else
        {
            return null;
        }
        roundedImage = new RoundImage(bm);
        return roundedImage;
    }
}
