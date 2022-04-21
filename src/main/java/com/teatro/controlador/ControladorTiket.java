package com.teatro.controlador;
import java.awt.event.TextEvent;
import java.util.List;
import java.util.Optional;
import com.teatro.modelo.Usuario;
import com.teatro.modelo.objetonulo.TiketNulo;
import com.teatro.modelo.objetonulo.UsuarioNulo;
import com.teatro.servicio.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.teatro.modelo.Tiket;
import com.teatro.servicio.TiketServicio;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

@RequiredArgsConstructor

@RestController
@RequestMapping("/tiket")
public class ControladorTiket {

    private  final UsuarioServicio usuarioServicio;
    private final TiketServicio tiketService;

    @PostMapping("/new")
    public ResponseEntity<?>  crear(@Valid @RequestBody Tiket tiketNuevo, BindingResult bindingResult){
    	if (bindingResult.hasErrors())
            return new  ResponseEntity<>( bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        tiketService.guardar(tiketNuevo);
    	    return  new ResponseEntity<>("Tiket Creado",HttpStatus.CREATED) ;
    }

    // listar todos TIKET
    @GetMapping("")
    @ResponseBody
    public List<Tiket>tiket(){
        return tiketService.buscarTodos();
    }
    //ELIMINAR TIKET
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@Valid @PathVariable("id") Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(  "no se encontro el tiket Eliminar",HttpStatus.BAD_REQUEST);
        tiketService.borrarPorId(id);
        return new ResponseEntity( "Tiket eliminado",HttpStatus.NO_CONTENT );
        // ex
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
    public ResponseEntity<List<Tiket>> buscarPorComprador(@PathVariable("comparator")  Long comprado ){
                  Optional<Usuario> usuario = Optional.of(usuarioServicio.buscarPorId(comprado)).orElse(null);
                     if (usuarioServicio.existePorId(comprado)){
                         if (!usuario.isEmpty()) {
                             return ResponseEntity.ok(tiketService.buscarPorComprador(usuario));
                         }
                     }
                     return new ResponseEntity("No Existe el usuario",HttpStatus.NO_CONTENT);




    }



}
