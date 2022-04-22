package com.teatro.controlador;
import java.util.List;
import java.util.stream.Collectors;

import com.teatro.dto.Tiket.TiketDto;
import com.teatro.modelo.Usuario;
import com.teatro.modelo.objetonulo.TiketNulo;
import com.teatro.modelo.objetonulo.UsuarioNulo;
import com.teatro.servicio.UsuarioServicio;
import com.teatro.util.converter.ShowDtoConverter;
import com.teatro.util.converter.TiketConverter;
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
@RequestMapping("/tiket")
public class ControladorTiket {
    private  final UsuarioServicio usuarioServicio;
    private final TiketServicio tiketService;
    private final TiketConverter converter;


    @PostMapping("/new")
    public ResponseEntity<?>  crear(@Valid @RequestBody Tiket tiketNuevo, BindingResult bindingResult){
    	if (bindingResult.hasErrors())
            return new  ResponseEntity<>( bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        tiketService.guardar(tiketNuevo);
    	    return  new ResponseEntity<>("Tiket Creado",HttpStatus.CREATED);
    }
    // listar todos TIKET
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<TiketDto>> tiket(){

        List<Tiket> t = tiketService.listarTikets();
        if (!t.isEmpty())
         return ResponseEntity.ok(t.stream().map(converter::traerTiketsDTO).collect(Collectors.toList()));

        return  new ResponseEntity("No existen elementos",HttpStatus.NOT_FOUND);

    }
    //ELIMINAR TIKETd




    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@Valid @PathVariable("id") Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("no se encontro el tiket Eliminar",HttpStatus.BAD_REQUEST);
        tiketService.borrarPorId(id);
        return new ResponseEntity( "Tiket eliminado",HttpStatus.NO_CONTENT );
    }
    //bUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Tiket> buscarPorId(@PathVariable("id") Long id){
       if (tiketService.existePorId(id))
           return new ResponseEntity ( tiketService.buscarPorId(id), HttpStatus.OK);
       return new ResponseEntity(  "No se encontro el tiket",HttpStatus.BAD_REQUEST);
        }
    @GetMapping("/comprador/{comparator}")
    @ResponseBody
    public ResponseEntity<List<Tiket>> buscarPorComprador(@PathVariable("comparator") Long comprado ){
                  Usuario usuario = usuarioServicio.buscarPorId(comprado).orElse(UsuarioNulo.construir());
                         if (!usuario.esNulo()) {
                             return ResponseEntity.ok(tiketService.buscarPorComprador(usuario));
                         }
                     return new ResponseEntity("No Existe el usuario",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/comprador/buscarNombre/{comparator}")
    @ResponseBody
    public ResponseEntity<List<TiketDto>> buscarPorNombre(@PathVariable("comparator") String comprado ){
        List<Tiket>  l =  tiketService.buscarPorNombre(comprado);
        if (!l.isEmpty()){
            return   ResponseEntity.ok(l.stream().map(converter::traerTiketsDTO).collect(Collectors.toList()));
        }
        return new ResponseEntity("No Existe el usuario",HttpStatus.NOT_FOUND);
    }



}
