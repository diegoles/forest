package uce.java8.curso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaApp {

	public void ordenar() {
		List<String> lista = new ArrayList<>();
		lista.add("MitoCode");
		lista.add("Code");
		lista.add("Mito");

		// Ordena una lista de String con Comparator
		/*
		 * Collections.sort(lista, new Comparator<String>() {
		 * 
		 * @Override public int compare(String o1, String o2) { return o1.compareTo(o2);
		 * } });
		 */

		// Ordenar una lista de String con compareTo y labda
		Collections.sort(lista, (String o1, String o2) -> o1.compareTo(o2));

		for (String l : lista) {
			System.out.println(l);
		}
	}

	public void calcular() {
		// utilizando la Interface y sobreescribiendo, no se puede instanciar una
		// interfaz
		/*
		 * Operacion operacion = new Operacion() {
		 * 
		 * @Override public double calcularPromedio(double n1, double n2) { return
		 * (n1+n2)/2; } };
		 */

		Operacion operacion = (n1, n2) -> (n1 + n2) / 2;
		System.out.println("El promedio es : " + operacion.calcularPromedio(2, 3));

		// System.out.println("La suma es : " + operacion.calcularDivision(2, 3));
	}

	public static void main(String[] args) {
		LambdaApp app = new LambdaApp();
		app.ordenar();
		app.calcular();
	}
}
