package com.teatro.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teatro.dto.Tiket.TiketDto;
import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;
import com.teatro.modelo.objetonulo.UsuarioNulo;
import com.teatro.servicio.TiketServicio;
import com.teatro.servicio.UsuarioServicio;
import com.teatro.util.converter.TiketDtoConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tikets")
public class TiketControlador {

	private final UsuarioServicio usuarioServicio;
	private final TiketServicio tiketService;
	private final TiketDtoConverter converter;

	@GetMapping()
	@ResponseBody
	public ResponseEntity<List<TiketDto>> tiket() {

		List<Tiket> t = tiketService.buscarTodos();
		if (!t.isEmpty())
			return ResponseEntity.ok(t.stream().map(converter::convertirTiketATiketDto).collect(Collectors.toList()));

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tiket> buscarPorId(@PathVariable("id") Long id) {
		if (tiketService.existePorId(id))
			return ResponseEntity.ok(tiketService.buscarPorId(id).get());

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/comprador/{idComprador}")
	@ResponseBody
	public ResponseEntity<List<Tiket>> buscarPorComprador(@PathVariable("comparator") Long idComprador) {
		Usuario usuario = usuarioServicio.buscarPorId(idComprador).orElse(UsuarioNulo.construir());
		
		if (!usuario.esNulo()) {
			List<Tiket> tiket = tiketService.buscarPorComprador(usuario);
			
			if(tiket.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(tiketService.buscarPorComprador(usuario));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/comprador/{nombreComprador}")
	@ResponseBody
	public ResponseEntity<List<TiketDto>> buscarPorNombre(@PathVariable("comparator") String nombreComprador) {
		List<Tiket> l = tiketService.buscarPorNombre(nombreComprador);
		if (!l.isEmpty()) {
			return ResponseEntity.ok(l.stream().map(converter::convertirTiketATiketDto).collect(Collectors.toList()));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tiket> eliminar(@PathVariable("id") Long id, BindingResult bindingResult) {
		
		if(!tiketService.existePorId(id)) {
			return ResponseEntity.notFound().build();
		}
		tiketService.borrarPorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
