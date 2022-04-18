package com.teatro.controlador;
import java.util.List;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> origin/Desarrollo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teatro.modelo.Tiket;
import com.teatro.servicio.TiketServicio;
@RequiredArgsConstructor
@RestController
public class ControladorTiket {


    private final TiketServicio tiketService;

    
<<<<<<< HEAD
    @PostMapping("/tiket/new")
    public ResponseEntity<?>  crear(@RequestBody Tiket tiketNuevo ){
    	tiketService.guardar(tiketNuevo);
=======
    /*@PostMapping("/tiket/new")
    public ResponseEntity<?>  crear(@RequestBody Tiket tiketNuevo){
    	tiketService.crear(tiketNuevo);
>>>>>>> origin/Desarrollo
    	return  new ResponseEntity<>(HttpStatus.CREATED) ;
    }
    

    @GetMapping("/tiket")
    @ResponseBody
    public List<Tiket>tiket(){
        return tiketService.buscarTodos();

    }*/

	
	
}
