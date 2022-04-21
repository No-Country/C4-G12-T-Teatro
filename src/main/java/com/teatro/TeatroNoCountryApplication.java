package com.teatro;
import java.time.LocalDateTime;
import java.util.Arrays;
import com.teatro.modelo.*;
import com.teatro.servicio.*;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.teatro.util.enumerados.RolUsuario;

@SpringBootApplication
@EnableScheduling
public class TeatroNoCountryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatroNoCountryApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(PromocionServicio promocionServicio, ShowServicio showServicio,
								  AlmacenamientoArchivoEnSistemaServicio almacenamientoServicio, CategoriaServicio categoriaServicio,
								  UsuarioServicio usuarioServicio, BCryptPasswordEncoder encoder , TiketServicio tiketServicio,ButacaServicio butacaServicio) {
		return args -> {
			almacenamientoServicio.deleteAll();
			almacenamientoServicio.init();
			
			Categoria categoria = new Categoria();
			categoria.setNombre("Musical");
			Categoria categoria2 = new Categoria();
			categoria2.setNombre("Drama");
			
			categoriaServicio.guardar(categoria);
			categoriaServicio.guardar(categoria2);

			Show show1 = new Show();
			show1.setTitulo("Prueba");
			show1.setPrecio(100);
			show1.setFechaShow(LocalDateTime.now());
			show1.setDuracionMinShow(200);
			show1.setCategoria(categoria);

			Show show2 = new Show();
			show2.setTitulo("Prueba2");
			show2.setPrecio(50);
			show2.setFechaShow(LocalDateTime.now());
			show2.setDuracionMinShow(230);
			show2.setCategoria(categoria2);
			showServicio.guardar(show1);
			showServicio.guardar(show2);
			Promocion promo1 = new PromocionPorcentual("PromoPrueba", null, show1, 20);
			promocionServicio.guardar(promo1);

			Usuario us = new Usuario(null, "Pepe Jose", "Hernandes", "hernandes@gmail.com", "123456", null, 22, LocalDateTime.now(), categoria2, Arrays.asList(RolUsuario.ROLE_USER), null);
			Usuario us1 = new Usuario(null, "Pepe Jose", "Hernandes", "hernans@gmail.co", "123456", null, 22, LocalDateTime.now(), categoria2, Arrays.asList(RolUsuario.ROLE_USER), null);
			us.setContrasena(encoder.encode(us.getContrasena()));
			us1.setContrasena(encoder.encode(us.getContrasena()));

			usuarioServicio.guardar(us);
			usuarioServicio.guardar(us1);
			Tiket tiket1=new Tiket((Long) null,null,31231,32322,show1,us1 ,null);
			tiketServicio.guardar(tiket1);

		};
	}

}
