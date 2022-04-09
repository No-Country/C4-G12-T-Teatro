package com.teatro.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"Show\"")
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@Getter
	@Setter
	private long id;
	@Column(name = "FECHA_SHOW")
	@Getter
	@Setter
	private Date fecha;
	@Column(name = "DESCRIPCION")
	@Getter
	@Setter
	private String descripcion;
	@Column(name = "LENGUAJE")
	@Getter
	@Setter
	private String lenguaje;
	@Column(name = "ESTADO")
	@Getter
	@Setter
	private boolean estado;
	@Column(name = "IMAGEN")
	@Getter
	@Setter
	private byte[] imagen;

	@ManyToOne
	@JoinColumn(name = "categoria")
	private Categoria categoria;

	@Override
	public String toString() {
		return "Show [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", lenguaje=" + lenguaje
				+ ", estado=" + estado + ", imagen=" + Arrays.toString(imagen) + ", categoria=" + categoria + ", sala="
				+ sala + ", ticket=" + ticket + "]";
	}

	@OneToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@OneToOne(mappedBy = "show")
	private Ticket ticket;

}
