package com.teatro;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.teatro.modelo.Categoria;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.PromocionPorcentual;
import com.teatro.modelo.Show;
import com.teatro.servicio.AlmacenamientoArchivoEnSistemaServicio;
import com.teatro.servicio.CategoriaServicio;
import com.teatro.servicio.PromocionServicio;
import com.teatro.servicio.ShowServicio;

@SpringBootApplication
@EnableScheduling
public class TeatroNoCountryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatroNoCountryApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(PromocionServicio promocionServicio, ShowServicio showServicio,
			AlmacenamientoArchivoEnSistemaServicio almacenamientoServicio, CategoriaServicio categoriaServicio) {
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

			Promocion promo1 = new PromocionPorcentual("PromoPrueba", null, Arrays.asList(show1, show2), 20);

			promocionServicio.guardar(promo1);

		};
	}

}
