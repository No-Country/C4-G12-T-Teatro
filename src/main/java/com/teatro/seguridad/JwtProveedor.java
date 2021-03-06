package com.teatro.seguridad;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teatro.error.exceptions.UsuarioNoEncontradoException;
import com.teatro.modelo.Usuario;
import com.teatro.servicio.UsuarioServicio;
import com.teatro.util.enumerados.RolUsuario;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProveedor {

	@Value("${jwt.secreto}")
	private String jwtSecreto;

	@Value("${jwt.expiracion}")
	private int jwtDuracionEnSeg;
	
	private final UsuarioServicio usuarioServicio;
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";

	

	public String generarToken(String username) {
		Usuario usuario = usuarioServicio.buscarPorEmail(username)
							.orElseThrow(() -> new UsuarioNoEncontradoException(username));
		
		Date fechaExpiracionToken = new Date(System.currentTimeMillis() + (jwtDuracionEnSeg * 100));
		List<String> rolesConFormato = usuario.getRoles().stream()
													.map(RolUsuario::name)
													.collect(Collectors.toList());
		
		return JWT.create().withHeader(Map.of("type", TOKEN_TYPE))
							.withSubject(Long.toString(usuario.getId()))
							.withIssuedAt(new Date())
							.withExpiresAt(fechaExpiracionToken)
							.withClaim("roles", rolesConFormato)
							.withClaim("username", usuario.getEmail())
							.sign(construirAlgotirmo());
	}
	
	public Long obtenerIdDeJWT(String token) {
		DecodedJWT jwtDecodificado = decodificar(token);
		
		return Long.parseLong(jwtDecodificado.getSubject());
	}
	
	public List<String> obtenerRolesDeJWT(String token){
		DecodedJWT jwtDecodificado = decodificar(token);
		
		return jwtDecodificado.getClaim("roles").asList(String.class);
	}
	
	public String obtenerUsernameDeJWT(String token) {
		DecodedJWT jwtDecodificado = decodificar(token);
		
		return jwtDecodificado.getClaim("username").asString();
	}
	
	public boolean esValido(String token) {
		try {
			decodificar(token);
			return true;
		}catch (JWTDecodeException e) {
			log.info("Token malformado: " + e.getMessage());
		} catch (TokenExpiredException e) {
			log.info("El token ha expirado: " + e.getMessage());
		} catch (SignatureVerificationException e) {
			log.info("Error en la firma del token JWT: " + e.getMessage());
		} catch (InvalidClaimException e) {
			log.info("JWT claims vac??o");
		} catch (AlgorithmMismatchException e) {
			log.info("Token JWT no soportado: " + e.getMessage());
		}
		return false;
	}
	
	public String obtenerTokenDeLaRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(JwtProveedor.TOKEN_HEADER);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProveedor.TOKEN_PREFIX)) {
			return bearerToken.substring(JwtProveedor.TOKEN_PREFIX.length(), bearerToken.length());
		}

		return null;
	}
	
	private Algorithm construirAlgotirmo() {
		return Algorithm.HMAC256(jwtSecreto.getBytes());
	}
	
	private DecodedJWT decodificar(String token) {
		return JWT.require(construirAlgotirmo()).build().verify(token);
	}
}
