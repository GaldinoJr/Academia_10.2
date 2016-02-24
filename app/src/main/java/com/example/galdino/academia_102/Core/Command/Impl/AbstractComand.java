package com.example.galdino.academia_102.Core.Command.Impl;

import com.example.galdino.academia_102.Core.Command.IComand;
import com.example.galdino.academia_102.Core.IFachada;
import com.example.galdino.academia_102.Core.Impl.Controle.Fachada;

/**
 * Created by Galdino on 19/08/2015.
 */
public abstract class AbstractComand implements IComand {
    protected IFachada fachada = new Fachada();
}
