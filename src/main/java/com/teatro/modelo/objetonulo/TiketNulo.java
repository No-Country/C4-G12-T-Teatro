package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Tiket;

public class TiketNulo  extends Tiket {
    public static Tiket construir(){
        return new TiketNulo();

    }
    @Override
    public boolean esNulo()
    {
        return true ;
    }
}
