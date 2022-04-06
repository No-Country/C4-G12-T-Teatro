package com.teatro.modelo;

import javax.persistence.Entity;

import lombok.*;

import java.time.LocalDate;
 
 
import javax.persistence.*;

@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tiket")
public class Tiket{
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", nullable = false)
	    private Long id;
	   
	    @Column(name="fecha_compra", nullable= false)
	    LocalDate localDate = LocalDate.now();

	    
	 







	    


	    
	    
	    
	    
	    
	    
	    
	     
	    

	    //@ManyToOne
	    //@JoinColumn(name= "usuario_id");
	    //private Usuario usuario;


	    //@OneToOne
	    //@JoinColumn(name = "show_id ")
	    //private Show  show;

}
