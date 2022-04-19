package com.teatro.controlador;
import java.util.List;
import java.util.Optional;


import com.teatro.modelo.Usuario;
import com.teatro.servicio.UsuarioServicio;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
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

    private  final UsuarioServicio usuarioServicio;
    private final TiketServicio tiketService;

    @PostMapping("/tiket/new")
    public ResponseEntity<?>  crear(@Valid @RequestBody Tiket tiketNuevo, BindingResult bindingResult){
    	if (bindingResult.hasErrors())
            return new  ResponseEntity<>( bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        tiketService.guardar(tiketNuevo);
    	    return  new ResponseEntity<>("Tiket  Creado",HttpStatus.CREATED) ;


    }

    // listar todos TIKET

    @GetMapping("/tiket")
    @ResponseBody
    public List<Tiket>tiket(){
        return tiketService.buscarTodos();
    }

    //ELIMINAR TIKET
    @DeleteMapping("/tiket/eliminar/{id}")
    public ResponseEntity eliminar(@Valid @PathVariable("id") Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(  "no se encontro el tiket Eliminar",HttpStatus.BAD_REQUEST);
        tiketService.borrarPorId(id);
        return new ResponseEntity( "Tiket eliminado",HttpStatus.NO_CONTENT );
        // ex
    }

    //bUSCAR POR ID
    @GetMapping("/tiket/{id}")
    public ResponseEntity<Tiket> buscarPorId(@PathVariable("id") Long id){
       if (tiketService.existePorId(id))
           return new ResponseEntity ( tiketService.buscarPorId(id), HttpStatus.OK);
       return new ResponseEntity(  "No se encontro el tiket",HttpStatus.BAD_REQUEST);

        }

 // BUSCAR POR USUARIO

//        @GetMapping("/tiket/comprador/{comprador}")
//        @ResponseBody
//        public List<Tiket> buscarPorComprador(@PathVariable("comprador") Long comprador){
//        if (usuarioServicio.existePorId(comprador)
//           return tiketService.buscarPorComprador(comprador);
//        }



}