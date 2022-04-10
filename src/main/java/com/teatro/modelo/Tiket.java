package com.teatro.modelo;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Tiket{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name ="fecha_compra")
        LocalDate local = LocalDate.now();

		@Column( name="activa")
		private int activa;
		@Size( max=100)
		@Column(name="descripcion")
		private  String Descripcion;

//	/*	@ManyToOne
//		@JoinColumn(name="categoria_id")
//		private Categoria  categoria;
//
//		@ManyToOne
//		@JoinColumn(name="show_id")
//		private Show show;
//*/






	    
		public Tiket() {
		}
		


		   //@ManyToOne
	    //@JoinColumn(name= "usuario_id");
	    //private Usuario usuario;


	    //@OneToOne
	    //@JoinColumn(name = "show_id")
	    //private Show  show;









	    


	    
	    
	    
	    
	    
	    
	    
	     
	    

}
