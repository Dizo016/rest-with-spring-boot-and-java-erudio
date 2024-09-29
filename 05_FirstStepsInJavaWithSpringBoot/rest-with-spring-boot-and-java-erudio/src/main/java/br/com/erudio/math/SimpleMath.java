package br.com.erudio.math;

public class SimpleMath {
	
	public Double sum(Double numberOne, Double numberTwo) {
		
		return numberOne + numberTwo;
	}

	// Subtração
	public Double subtraction(Double numberOne, Double numberTwo){

		return numberOne - numberTwo;
	}

	// Multiplicação
	public Double multiplication(Double numberOne, Double numberTwo) {
			
		return numberOne * numberTwo;
	}

	// Divisão
	public Double division(Double numberOne, Double numberTwo) {
			
		return numberOne / numberTwo;
	}

	// Média
	public Double avg(Double numberOne, Double numberTwo) {
		
		return numberOne + numberTwo / 2;
	}

	// Raíz quadrada
	public Double sqrRoot(Double numberOne){
		
		
		Double sqrRoot = Math.sqrt(numberOne);
		
		return sqrRoot;
	}

}
