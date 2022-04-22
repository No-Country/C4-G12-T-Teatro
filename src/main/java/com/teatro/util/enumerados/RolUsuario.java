package com.teatro.util.enumerados;


public enum RolUsuario {

	ROLE_ADMIN("ADMIN"), ROLE_USER("USER"),  ROLE_SELLER("SELLER");
	
	private String rol;
	
	private RolUsuario(String rol) {
		this.rol = rol;
	}
	
	public String getRol() {
		return this.rol;
	}
}
