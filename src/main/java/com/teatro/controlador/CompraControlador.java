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

import com.teatro.modelo.Promocion;
import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;
import com.teatro.seguridad.JwtProveedor;
import com.teatro.servicio.CompraServicio;
import com.teatro.servicio.PromocionServicio;
import com.teatro.servicio.ShowServicio;
import com.teatro.servicio.TiketServicio;
import com.teatro.servicio.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comprar")
public class CompraControlador {

	private final JwtProveedor jwtProveedor;
	private final TiketServicio tiketServicio;
	private final UsuarioServicio usuarioServicio;
	private final PromocionServicio promocionServicio;
	private final ShowServicio showServicio;
	private final CompraServicio compraServicio;

	@PostMapping("/promocion/{idPromocion}")
	public ResponseEntity<Tiket> comprarPromocion(
			@PathVariable Long idPromocion,
			@RequestBody(required = true) MapFilaButacaDto butacas,
			@RequestBody(required = false) DatosUsuarioCompraDto datosUsuario,
			HttpServletRequest request){
		//Logica Mercado Pago para hacer la compra y si es correcto seguir con el proceso de compra
		
		String token = obtenerTokenDeLaRequest(request);
		
		if ((!StringUtils.hasText(token) || !jwtProveedor.esValido(token)) && datosUsuario ==null ) {
			return ResponseEntity.badRequest().build();
		}
		if(StringUtils.hasText(token)) {
			Long idUsuario = jwtProveedor.obtenerIdDeJWT(token);
			Usuario usuario = usuarioServicio.buscarPorId(idUsuario).get();
			Promocion promocion = promocionServicio.buscarPorId(idPromocion).get();
			Tiket tiket = compraServicio.comprar(usuario, promocion, butacas);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(tiket);
		}else {
			
		}
	}

	@PostMapping("/show/{idShow}")
	public ResponseEntity<Tiket> comprarShow() {
		// Logica Mercado Pago para hacer la compra y si es correcto seguir con el
		// proceso de compra

	}


	private String obtenerTokenDeLaRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(JwtProveedor.TOKEN_HEADER);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProveedor.TOKEN_PREFIX)) {
			return bearerToken.substring(JwtProveedor.TOKEN_PREFIX.length(), bearerToken.length());
		}

		return null;
	}
}
