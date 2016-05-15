package com.example.galdino.academia_102.SlidingTab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.Telas.Testes.slideTab.Tab1;
import com.example.galdino.academia_102.Telas.Testes.slideTab.Tab2;

/**
 * Created by Galdino on 11/05/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context mContext;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb, Context mContext) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.mContext = mContext;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Tab1 tab1 = new Tab1();
            return tab1;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Tab2 tab2 = new Tab2();
            return tab2;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

   // @Override
    //public CharSequence getPageTitle(int position) {
    //    return Titles[position];
    //}

    private int[] drawablesIds = {
            R.drawable.ic_aba_video_sm,
            R.drawable.ic_aba_musculo_sm
            //R.drawable.third_tab_drawable
    };
    @Override
    public CharSequence getPageTitle(int position) {
        //return Titles[position];
//        Drawable image = mContext.getResources().getDrawable(drawablesIds[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        //image.setBounds(0, 0, 64, 64);
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        SpannableString sb = new SpannableString(" ");
//        sb.setSpan(imageSpan, 0, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
        ////Drawable image = mContext.getResources().getDrawable (R.drawable.ic_launcher);
        Drawable image = mContext.getResources().getDrawable(drawablesIds[position]);
       // image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight()); // Ultilizar quanto as fotos estiverem certas em pastas por tamanho*****
        image.setBounds(0, 0, 64, 64);
        //SpannableString sb = new SpannableString(" \n"+Titles[position]); // ***TÍTULO DA ABA*** FICA CENTRALIZADO EM BAIXO DO ICONE
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }


    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
