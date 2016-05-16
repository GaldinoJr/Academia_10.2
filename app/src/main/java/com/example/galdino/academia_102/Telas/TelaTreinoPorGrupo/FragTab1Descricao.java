package com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 15/05/2016.
 */
public class FragTab1Descricao extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);
        return v;
    }
}