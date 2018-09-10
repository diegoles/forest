package uce.java8.curso;

public interface PersonaA {
	public void caminar();
	
	default public void hablar() {
		System.out.println("Saludos Persona A");
	}
}
