package uce.java8.curso;

import java.util.Arrays;

public class MeRefApp {
	
	public static void referenciaMetodoStatic() {
		System.out.println("Metodo referido estatico");
	}
	
	public void referenciaMetodoInstanciaObjetoArbitrario() {
		String[] nombres = {"Mito","Code","Edgar"};
//		Arrays.sort(nombres, (s1,s2)-> s1.compareToIgnoreCase(s2));
//		System.out.println(Arrays.toString(nombres));
		
		Arrays.sort(nombres, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(nombres));		
	}
	
	public void referenciaMetodoInstanciaObjetoParticular() {
		System.out.println("Metodo referido instancia de clase");	
	}
	
	public void referenciaConstructor() {
		IPersona iper = (x,y)->new Persona(x,y);
		Persona per = iper.crear(1, "Edgar");
		System.out.println(per.getId()+"-"+per.getNombre());
		
		IPersona iper2 = Persona::new;
		Persona per2 = iper2.crear(2, "Efra");
		System.out.println(per2.getId()+"-"+per2.getNombre());
	}	
	
	public void operar() {
		Operacion1 op = ()-> MeRefApp.referenciaMetodoStatic();
		op.saludar();
		
//		Operacion1 op2 = ()-> MeRefApp::referenciaMetodoStatic;
//		op2.saludar();
	}

	public static void main(String[] args) {
		MeRefApp app = new MeRefApp();
		app.operar();
		app.referenciaMetodoInstanciaObjetoArbitrario();
		
		Operacion1 op = app::referenciaMetodoInstanciaObjetoParticular;
		op.saludar();
		
		app.referenciaConstructor();
	}

}
