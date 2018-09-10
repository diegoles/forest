package uce.java8.curso;

public class Scope {
	
	public static double atributo1;
	public double atributo2;
	
	public double probarVariableLocal () {
		// final es opcional, pero no se puede alterar el valor de n3 en una expresion anonima
		
		final double n3 = 3;
		/*
		Operacion op = new Operacion() {

			@Override
			public double calcularPromedio(double n1, double n2) {
				return n1+n2+n3;
			}};
		*/
		Operacion op = (n1,n2)->n1+n2+n3;
		return op.calcularPromedio(1, 2);
	}
	
	
	public double probarVariableAtributosEstaticos () {
		// Se pueden utilizar variables globales 
		Operacion op = (n1,n2)->{
			atributo1 = n1 + n2;
			return atributo2 = atributo1 * 5;
			};
		return op.calcularPromedio(1, 2);
	}

	public static void main(String[] args) {
		Scope scope = new Scope();
		System.out.println(scope.probarVariableLocal());
		System.out.println(scope.probarVariableAtributosEstaticos());
	}

}
