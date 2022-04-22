package com.teatro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.teatro.filtro.AutenticacionFiltro;
import com.teatro.filtro.AutorizacionFiltro;
import com.teatro.seguridad.JwtProveedor;
import com.teatro.util.constantes.RutaUtilidades;
import com.teatro.util.enumerados.RolUsuario;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final AutorizacionFiltro autorizacionFiltro;
	private final JwtProveedor jwtProveedor;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AutenticacionFiltro autenticacionFiltro = new AutenticacionFiltro(jwtProveedor, authenticationManagerBean());
		http.csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().headers()
				.frameOptions().disable()
			.and()
			.authorizeHttpRequests()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers(HttpMethod.POST, RutaUtilidades.COMPRAS).permitAll()
				.antMatchers(HttpMethod.GET, RutaUtilidades.FICHERO).permitAll()
				
				.antMatchers(HttpMethod.GET, RutaUtilidades.PROMOCIONES).permitAll()
				.antMatchers(HttpMethod.POST, RutaUtilidades.PROMOCIONES)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.PUT, RutaUtilidades.PROMOCIONES)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.DELETE, RutaUtilidades.PROMOCIONES)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				
				.antMatchers(HttpMethod.GET, RutaUtilidades.SHOWS).permitAll()
				.antMatchers(HttpMethod.POST, RutaUtilidades.SHOWS)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.PUT, RutaUtilidades.SHOWS)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.DELETE, RutaUtilidades.SHOWS)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				
				.antMatchers(HttpMethod.GET, RutaUtilidades.SALAS).permitAll()
				.antMatchers(HttpMethod.POST, RutaUtilidades.SALASAGREGARELIMINARSHOW)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.POST, RutaUtilidades.SALAS)
					.hasRole(RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.PUT, RutaUtilidades.SALAS)
					.hasRole(RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.DELETE, RutaUtilidades.SALASAGREGARELIMINARSHOW)
					.hasAnyRole(RolUsuario.ROLE_SELLER.getRol(), RolUsuario.ROLE_ADMIN.getRol())
				.antMatchers(HttpMethod.DELETE, RutaUtilidades.SALAS)
					.hasRole(RolUsuario.ROLE_ADMIN.getRol())
					
				.antMatchers(HttpMethod.GET, RutaUtilidades.USUARIOS).authenticated()
			.anyRequest().hasRole(RolUsuario.ROLE_ADMIN.getRol());
		
		http.addFilter(autenticacionFiltro);		
		http.addFilterBefore(autorizacionFiltro, UsernamePasswordAuthenticationFilter.class);
	}
}
