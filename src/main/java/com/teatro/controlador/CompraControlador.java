package com.teatro.controlador;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teatro.dto.butaca.MapFilaButacaDto;
import com.teatro.dto.usuario.DatosUsuarioCompraDto;
import com.teatro.error.exceptions.PagoNoExitosoException;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.Show;
import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;
import com.teatro.seguridad.JwtProveedor;
import com.teatro.servicio.CompraServicio;
import com.teatro.servicio.PromocionServicio;
import com.teatro.servicio.ShowServicio;
import com.teatro.servicio.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comprar")
public class CompraControlador {

	private final JwtProveedor jwtProveedor;
	private final UsuarioServicio usuarioServicio;
	private final PromocionServicio promocionServicio;
	private final ShowServicio showServicio;
	private final CompraServicio compraServicio;

	@PostMapping("/promocion/{idPromocion}")
	public ResponseEntity<Tiket> comprarPromocion(
			@PathVariable Long idPromocion,
			@RequestBody(required = true) MapFilaButacaDto butacas,
			@RequestBody(required = false) DatosUsuarioCompraDto datosUsuario, HttpServletRequest request) {

		String token = jwtProveedor.obtenerTokenDeLaRequest(request);

		if ((!StringUtils.hasText(token) || !jwtProveedor.esValido(token)) && datosUsuario == null) {
			return ResponseEntity.badRequest().build();
		}
		Tiket tiket;
		
		if (StringUtils.hasText(token)) {
			Long idUsuario = jwtProveedor.obtenerIdDeJWT(token);
			Usuario usuario = usuarioServicio.buscarPorId(idUsuario).get();
			Promocion promocion = promocionServicio.buscarPorId(idPromocion).orElseThrow();
			tiket = compraServicio.comprar(usuario, promocion, butacas);

		} else {
			if(datosUsuario.getEdad() < 18) {
				new ResponseEntity<Tiket>( HttpStatus.BAD_REQUEST);
			}
				
			Promocion promocion = promocionServicio.buscarPorId(idPromocion).orElseThrow();
			tiket = compraServicio.comprar(promocion, butacas);
			tiket.setNombreCompleto(datosUsuario.getNombre() + " " + datosUsuario.getApellido());	
		}
		if(tiket.esNulo()) {
			throw new PagoNoExitosoException();
		}
		
		//enviar mail con la factura de la compra
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tiket);
	}

	@PostMapping("/show/{idShow}")
	public ResponseEntity<Tiket> comprarShow(
			@PathVariable Long idShow,
			@RequestBody(required = true) MapFilaButacaDto butacas,
			@RequestBody(required = false) DatosUsuarioCompraDto datosUsuario, HttpServletRequest request) {
		
		
		
		String token = jwtProveedor.obtenerTokenDeLaRequest(request);

		if ((!StringUtils.hasText(token) || !jwtProveedor.esValido(token)) && datosUsuario == null) {
			return ResponseEntity.badRequest().build();
		}
		Tiket tiket;
		
		if (StringUtils.hasText(token)) {
			Long idUsuario = jwtProveedor.obtenerIdDeJWT(token);
			Usuario usuario = usuarioServicio.buscarPorId(idUsuario).get();
			Show show = showServicio.buscarPorId(idShow).orElseThrow();
			tiket = compraServicio.comprar(usuario, show, butacas);
		} else {
			
			if(datosUsuario.getEdad() < 18) {
				new ResponseEntity<Tiket>( HttpStatus.BAD_REQUEST);
			}	
			Show show = showServicio.buscarPorId(idShow).orElseThrow();
			tiket = compraServicio.comprar(show, butacas);
			tiket.setNombreCompleto(datosUsuario.getNombre() + " " + datosUsuario.getApellido());	
		}
		if(tiket.esNulo()) {
			throw new PagoNoExitosoException();
		}
		//enviar mail con la factura de la compra
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tiket);
	}
}
