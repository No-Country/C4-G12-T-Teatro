package com.teatro.controlador;

import org.springframework.web.bind.annotation.RestController;

import com.teatro.seguridad.JwtProveedor;
import com.teatro.servicio.PromocionServicio;
import com.teatro.servicio.ShowServicio;
import com.teatro.servicio.TiketServicio;
import com.teatro.servicio.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompraControlador {
	
	private final JwtProveedor jwtProveedor;
	private final TiketServicio tiketServicio;
	private final UsuarioServicio usuarioServicio;
	private final PromocionServicio promocionServicio;
	private final ShowServicio showServicio;	
}
