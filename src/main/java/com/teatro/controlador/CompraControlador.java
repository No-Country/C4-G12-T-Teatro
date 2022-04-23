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

import com.teatro.dto.compra.CompraDto;
import com.teatro.dto.tiket.TiketDto;
import com.teatro.error.exceptions.PagoNoExitosoException;
import com.teatro.error.exceptions.PromocionNoEncontradaException;
import com.teatro.error.exceptions.ShowNoEncontradoException;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.Show;
import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;
import com.teatro.seguridad.JwtProveedor;
import com.teatro.servicio.CompraServicio;
import com.teatro.servicio.PromocionServicio;
import com.teatro.servicio.ShowServicio;
import com.teatro.servicio.UsuarioServicio;
import com.teatro.util.converter.TiketDtoConverter;

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
	private final TiketDtoConverter converter;

	@PostMapping("/promocion/{idPromocion}")
	public ResponseEntity<Tiket> comprarPromocion(
			@PathVariable Long idPromocion,
			@RequestBody(required = true) CompraDto datosCompra,
			HttpServletRequest request) {

		String token = jwtProveedor.obtenerTokenDeLaRequest(request);

		Tiket tiket;
		
		if (StringUtils.hasText(token)) {
			if(!jwtProveedor.esValido(token)) {
				return ResponseEntity.badRequest().build();
			}
			Long idUsuario = jwtProveedor.obtenerIdDeJWT(token);
			Usuario usuario = usuarioServicio.buscarPorId(idUsuario).get();
			Promocion promocion = promocionServicio.buscarPorId(idPromocion).orElseThrow(() -> new PromocionNoEncontradaException(idPromocion));
			tiket = compraServicio.comprar(usuario, promocion, datosCompra);

		} else {
			if(datosCompra.getEdad() < 18) {
				new ResponseEntity<Tiket>( HttpStatus.BAD_REQUEST);
			}
				
			Promocion promocion = promocionServicio.buscarPorId(idPromocion).orElseThrow(() -> new PromocionNoEncontradaException(idPromocion)	);
			tiket = compraServicio.comprar(promocion, datosCompra);
			tiket.setNombreApellido(datosCompra.getNombre() + " " + datosCompra.getApellido());	
		}
		if(tiket.esNulo()) {
			throw new PagoNoExitosoException();
		}
		
		//enviar mail con la factura de la compra
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tiket);
	}

	@PostMapping("/show/{idShow}")
	public ResponseEntity<TiketDto> comprarShow(
			@PathVariable Long idShow,
			@RequestBody(required = true) CompraDto datosCompra,
			HttpServletRequest request) {
		
		String token = jwtProveedor.obtenerTokenDeLaRequest(request);

		Tiket tiket;
		
		if (StringUtils.hasText(token)) {
			if(!jwtProveedor.esValido(token)) {
				return ResponseEntity.badRequest().build();
			}
			Long idUsuario = jwtProveedor.obtenerIdDeJWT(token);
			Usuario usuario = usuarioServicio.buscarPorId(idUsuario).get();
			Show show = showServicio.buscarPorId(idShow).orElseThrow(() -> new ShowNoEncontradoException(idShow));
			tiket = compraServicio.comprar(usuario, show, datosCompra);
		} else {
			
			if(datosCompra.getEdad() < 18) {
				new ResponseEntity<Tiket>( HttpStatus.BAD_REQUEST);
			}	
			Show show = showServicio.buscarPorId(idShow).orElseThrow(() -> new ShowNoEncontradoException(idShow));
			tiket = compraServicio.comprar(show, datosCompra);
			tiket.setNombreApellido(datosCompra.getNombre() + " " + datosCompra.getApellido());	
		}
		if(tiket.esNulo()) {
			throw new PagoNoExitosoException();
		}
		//enviar mail con la factura de la compra
		
		return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertirTiketATiketDto(tiket));
	}
}
