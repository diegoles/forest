package uce.java8.curso;

public interface PersonaB {
	default public void hablar() {
		System.out.println("Saludos Persona B");
	}
}
