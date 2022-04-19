package com.teatro.controlador;
import java.util.List;

import jdk.javadoc.internal.doclets.toolkit.builders.BuilderFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.teatro.modelo.Tiket;
import com.teatro.servicio.TiketServicio;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ControladorTiket {


    private final TiketServicio tiketService;

    

    @PostMapping("/tiket/new")
    public ResponseEntity<?>  crear(@RequestBody Tiket tiketNuevo, BindingResult bindingResult){
    	if (bindingResult.hasErrors())
            return new  ResponseEntity("campos mal ingresados",HttpStatus.BAD_REQUEST);
        tiketService.guardar(tiketNuevo);
    	    return  new ResponseEntity<>(HttpStatus.CREATED) ;
    }
    @GetMapping("/tiket")
    @ResponseBody
    public List<Tiket>tiket(){
        return tiketService.buscarTodos();

    }

    @DeleteMapping("/tiket/eliminar/{id}")
    public ResponseEntity eliminar(@Valid @RequestParam Long id, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return new ResponseEntity(  "no se encontro el tiket a eliminar",HttpStatus.BAD_REQUEST);
        tiketService.borrarPorId(id);
        return null;
    }


	
	
}
