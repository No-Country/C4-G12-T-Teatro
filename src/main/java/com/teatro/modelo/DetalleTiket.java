package com.teatro.modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DetalleTiket {
	
	 @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", nullable = false)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name="tiket_id")
	    Tiket tiket;

		public DetalleTiket() {
		}

		public DetalleTiket(Long id, Tiket tiket) {
			this.id = id;
			this.tiket = tiket;
		}

		public Long getId() {
			return id;
		}
//---------------------------------------------//

		public void setId(Long id) {
			this.id = id;
		}







		public Tiket getTiket() {
			return tiket;
		}







		public void setTiket(Tiket tiket) {
			this.tiket = tiket;
		}


	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /* @OneToMany
	    @JoinColumn(name = "sala_id")
	    private Sala salaEntity;

	    /*@OneToMany(mappedBy="detalleTiket")
	    private  Set<Butaca> butaca;*/
	
}
