package com.teatro.servicio;

import java.util.List;

import com.teatro.modelo.Tiket;

public interface ITiketServicio {


    public List<Tiket> verTikets();
 
    public void crear(Tiket tiket);
    
}
