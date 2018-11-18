package uce.java8.curso;

import java.util.ArrayList;
import java.util.List;

public class ColleccionApp {
	
	private List<String> lista;
	
	public void llenarLista() {
		lista = new ArrayList<>();
		lista.add("MitoCode");
		lista.add("Mito");
		lista.add("Code");
	}
	
	public void usarForEach() {
		lista.forEach(System.out::println);
	}
	
	public void usarRemoveIf() {
		lista.removeIf(x->x.equalsIgnoreCase("Code"));
	}
	
	public void usarSort() {
		lista.sort((x,y)->x.compareTo(y));
	}

	public static void main(String... args) {
		ColleccionApp app = new ColleccionApp();
		app.llenarLista();
		app.usarRemoveIf();
		app.usarSort();
		app.usarForEach();
	}

}
