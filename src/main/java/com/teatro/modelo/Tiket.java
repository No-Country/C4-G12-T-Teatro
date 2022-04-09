package com.teatro.modelo;
import javax.persistence.Entity;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;

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
