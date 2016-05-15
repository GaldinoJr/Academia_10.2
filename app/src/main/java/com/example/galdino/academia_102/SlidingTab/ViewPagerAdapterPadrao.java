package com.example.galdino.academia_102.SlidingTab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
/**
 * Created by Galdino on 14/05/2016.
 */
public class ViewPagerAdapterPadrao extends FragmentStatePagerAdapter {

    private CharSequence titulos[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    private int nrAbas; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    private Context mContext;
    private int[] icones;
    Fragment[] fragments;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapterPadrao(Context mContext, FragmentManager fm, CharSequence titulos[], int nrAbas, int[] icones, Fragment[] fragments)
    {
        super(fm);
        this.mContext = mContext;
        this.nrAbas = nrAbas;
        this.titulos = titulos;
        this.icones = icones;
        this.fragments = fragments;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position)
    {
        // É possível tentar fazer melhor, visto que no vetor, é preciso instanciar todas as classes, independente de usar ou não
        return fragments[position];
    }

    // Este método cria cada aba(com foto e escrita)
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = mContext.getResources().getDrawable(icones[position]);
        // image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight()); // Ultilizar quanto as fotos estiverem certas em pastas por tamanho*****
        image.setBounds(0, 0, 64, 64);
        //SpannableString sb = new SpannableString(" \n"+Titles[position]); // ***TÍTULO DA ABA*** FICA CENTRALIZADO EM BAIXO DO ICONE
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
        //return Titles[position];
    }


    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return nrAbas;
    }
}
