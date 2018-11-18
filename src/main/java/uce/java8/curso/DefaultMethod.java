package uce.java8.curso;

public class DefaultMethod implements PersonaA, PersonaB {

	@Override
	public void caminar() {
		System.out.println("Hola mundo");
	}
	
	@Override
	public void hablar() {
		PersonaB.super.hablar();
	}
	
	// Modificando metodo Persona B
	/*@Override
	public void hablar() {
		System.out.println("Modificando metodo hablar Persona B");
	}*/

	public static void main(String[] args) {
		DefaultMethod app = new DefaultMethod();
		app.caminar();
		app.hablar();
	}

}
