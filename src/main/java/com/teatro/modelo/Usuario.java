package com.teatro.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.teatro.util.enumerados.RolUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable{

	private static final long serialVersionUID = -3415330562513402939L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellido;
	
	@Email
	@Column(unique = true)
	@NotNull
	private String email;
	
	@NotBlank
	@Length(min = 5, max = 15)
	private String contrasena;
	
	private String urlAvatar;
	
	@Min(18)
	private int edad;
	
	@CreatedDate
	private LocalDateTime fechaDeAlta;
	
	private boolean activo = true;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria preferencia;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private List<RolUsuario> roles;
	
	@OneToMany(mappedBy = "comprador")
	private List<Tiket> compras;
	
	public boolean esNulo() {
		return false;
	}
}
