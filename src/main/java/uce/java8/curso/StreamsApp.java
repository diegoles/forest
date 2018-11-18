package uce.java8.curso;

import java.util.ArrayList;
import java.util.List;

public class StreamsApp {
	private List<String> lista;
	private List<String> numeros;
	
	public StreamsApp() {
		lista = new ArrayList<>();
		lista.add("Mito");
		lista.add("Code");
		lista.add("Edgar");
		lista.add("MitoCode");
		
		numeros = new ArrayList<>();
		numeros.add("1");
		numeros.add("2");
		numeros.add("3");
		
	}

	public void filtrar() {
		lista
		.stream()
		.filter(x->x.startsWith("M"))
		.forEach(System.out::println);
	}
	public void ordenar() {
		// Ordenar
		lista.stream().sorted().forEach(System.out::println);
	}
	public void ordenarDesc() {
		// Ordenar descendente
		lista.stream().sorted((x,y)->y.compareTo(x)).forEach(System.out::println);
	}
	public void transformar() {
		lista.stream().map(String::toUpperCase).forEach(System.out::println);
		
		numeros.stream().map(x-> Integer.parseInt(x)).forEach(System.out::println);
	}
	public void limitar() {
		lista.stream().limit(2).forEach(System.out::println);
	}
	public void contar() {
		long total = lista.stream().count();
		System.out.println(total);
	}
	
	public static void main(String[] args) {
		StreamsApp app = new StreamsApp();
		System.out.println("**** Filtrar que contengan M");
		app.filtrar();
		System.out.println("**** Ordenar");
		app.ordenar();
		System.out.println("**** Ordenar descendente");
		app.ordenarDesc();
		System.out.println("**** Tansformar a mayusculas y transfoemar a enteros");
		app.transformar();
		System.out.println("**** Limitar a 2");
		app.limitar();
		System.out.println("**** Total de objetos");
		app.contar();
	}

}
