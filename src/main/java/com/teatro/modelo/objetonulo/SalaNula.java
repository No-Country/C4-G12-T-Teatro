package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Sala;

public class SalaNula extends Sala{
	
	private static final long serialVersionUID = -180848147934287735L;
	
	public static Sala construir() {
		return new SalaNula();
	}
	
	@Override
	public boolean esNula() {
		return true;
	}
}
