package com.teatro.controlador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teatro.modelo.Tiket;
import com.teatro.servicio.TiketServicio;

@RestController
public class ControladorTiket {

	
	
    @Autowired
    private  TiketServicio tiketService;


    @GetMapping("/tiket")
    @ResponseBody
    public List<Tiket>tiket(){
        return tiketService.verTikets();

    }



    @PostMapping("/tiket/new")
    public void crear(@RequestBody Tiket tiketNuevo){
      tiketService.crear(tiketNuevo);
    }
	
	
	
}
