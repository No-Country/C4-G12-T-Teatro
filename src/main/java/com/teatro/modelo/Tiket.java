package com.teatro.modelo;
import javax.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Tiket implements Serializable {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name ="fecha_compra")
		@CreatedDate

        LocalDate local ;

		@Column( name= "activa")
		private boolean activa;

		@Size(max=100)
		@Column(name="descripcion")
		private  String descripcion;

		@NonNull
		 @Min (1)
		 @Max(100000)
		@Column
		private float precio;

		@NonNull
		@Min(1)
		@Column(name= "cantidad_entradas")
		private int cantidadEntradas;

		@ManyToOne
		@JoinColumn(name="show_id")
		private Show show;

	@ManyToOne
	@JoinColumn(name= "usuario_id");
	private Usuario usuario;








	    
	    
	    
	    
	    
	    
	    
	     
	    

}
