package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Promocion;


<<<<<<< HEAD
	@Override
	public boolean esNula() {
		return true;
	}
=======
public class PromocionNula extends Promocion {
>>>>>>> db04466c3546654c7d2ec09036f00a741a1c8a69

	public static Promocion construir() {
		return new PromocionNula();
	}

	@Override
	public boolean esNula() {
		return true;
	}
}
