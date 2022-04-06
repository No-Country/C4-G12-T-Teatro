package com.teatro.modelo;
import javax.persistence.Entity;
import lombok.*;
import java.time.LocalDate;
import javax.persistence.*;

@Getter @Setter
@Entity
public class Tiket{
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name ="fecha_compra")
        LocalDate local = LocalDate.now();
	    
	    
	    
		public Tiket() {
		}



		public Tiket(Long id, LocalDate local) {
			this.id = id;
			this.local = local;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public LocalDate getLocal() {
			return local;
		}



		public void setLocal(LocalDate local) {
			this.local = local;
		}
        

		   //@ManyToOne
	    //@JoinColumn(name= "usuario_id");
	    //private Usuario usuario;


	    //@OneToOne
	    //@JoinColumn(name = "show_id")
	    //private Show  show;









	    


	    
	    
	    
	    
	    
	    
	    
	     
	    

}
