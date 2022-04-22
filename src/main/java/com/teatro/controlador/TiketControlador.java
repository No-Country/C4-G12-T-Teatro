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
import com.teatro.util.converter.TiketConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tiket")
public class TiketControlador {

	private final UsuarioServicio usuarioServicio;
	private final TiketServicio tiketService;
	private final TiketConverter converter;

	@GetMapping()
	@ResponseBody
	public ResponseEntity<List<TiketDto>> tiket() {

		List<Tiket> t = tiketService.buscarTodos();
		if (!t.isEmpty())
			return ResponseEntity.ok(t.stream().map(converter::traerTiketsDTO).collect(Collectors.toList()));

		return new ResponseEntity("No existen elementos", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tiket> buscarPorId(@PathVariable("id") Long id) {
		if (tiketService.existePorId(id))
			return new ResponseEntity(tiketService.buscarPorId(id), HttpStatus.OK);

		return new ResponseEntity<Tiket>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/comprador/{comparator}")
	@ResponseBody
	public ResponseEntity<List<Tiket>> buscarPorComprador(@PathVariable("comparator") Long comprado) {
		Usuario usuario = usuarioServicio.buscarPorId(comprado).orElse(UsuarioNulo.construir());
		
		if (!usuario.esNulo()) {
			return ResponseEntity.ok(tiketService.buscarPorComprador(usuario));
		}
		return new ResponseEntity<List<Tiket>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/comprador/{comparator}")
	@ResponseBody
	public ResponseEntity<List<TiketDto>> buscarPorNombre(@PathVariable("comparator") String comprado) {
		List<Tiket> l = tiketService.buscarPorNombre(comprado);
		if (!l.isEmpty()) {
			return ResponseEntity.ok(l.stream().map(converter::traerTiketsDTO).collect(Collectors.toList()));
		}
		return new ResponseEntity<List<TiketDto>>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tiket> eliminar(@PathVariable("id") Long id, BindingResult bindingResult) {

		tiketService.borrarPorId(id);
		return new ResponseEntity<Tiket>(HttpStatus.NO_CONTENT);
	}
}
