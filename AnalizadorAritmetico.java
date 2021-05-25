import java.lang.*;
import java.util.*;

/**
 * @class AnalizadorAritmetico
 * @author Jesús Monterrubio
 * @id A01114287
 * @version 2.0, 24/05/2021
 */

public class AnalizadorAritmetico {

	/**
	 * Método que verifica el diccionario permitido
	 * @param expresion
	 * @param digitos
	 * @param operadores
	 * @return Boolean
	 */

	public boolean verificaDiccionario(String expresion, char[] digitos, char[] operadores) {

		boolean seEncontro=false;	
		//Limpia los posibles espacios para operar adecuadamente
		while(expresion.contains(" ")) {
			expresion=expresion.replace(" ", "");	    
		}	
		for(int i=0; i<expresion.length(); i++) {     
			for(int j=0; j<digitos.length; j++) {
				if(expresion.charAt(i)==digitos[j]) {
					seEncontro=true;
					j=digitos.length; //Termina el ciclo
				} else { //Sino es un digito tiene que ser un operador permitido
					seEncontro=false; //No se encontro entre los digitos             		               		                 		                 		                 		                 		            
				}
			}              
			if(seEncontro==false) { //Se busca entre los operadores
				for(int j=0; j<operadores.length; j++) {
					if(expresion.charAt(i)==operadores[j]) {
						seEncontro=true;
						j=operadores.length; //Termina el ciclo
					} else { //Sino es un digito tiene que ser un operador permitido
						seEncontro=false; //No se encontro entre los digitos                 		                		                 		                 		                 		                 		           
					}
				}                	 
			} //Fin del if                                                                                                                           
			if(seEncontro==false) {
				i=expresion.length();             	              	
			}                	       	 
		}	
		return seEncontro;	//True-False, si es true continua y si es false es el fin del programa

	}	

	/**
	 * Método que verifica que la sintaxis sea correcta
	 * @param expresion
	 */

	public void verificaSintaxis(String expresion) {

		LinkedList pila=new LinkedList(),
				pila1=new LinkedList(),
				pila2=new LinkedList(),
				pila3=new LinkedList(),
				pila4=new LinkedList(),
				pila5=new LinkedList(),
				pp=new LinkedList(),
				ppo=new LinkedList(),
				pilaC=new LinkedList();
		int a=0,
			b=0;

		for(int i=0; i<expresion.length(); i++) {
			String elem=String.valueOf(expresion.charAt(i));
			pila.push(elem);
			pila1.push(elem);
			pila2.push(elem);
			pila3.push(elem);
			pila4.push(elem);
			pila5.push(elem);
		}

		if(pila1.size()==1) {
			String u=(String)pila1.pop();
			if(0==u.compareTo("+")||0==u.compareTo("-")||0==u.compareTo("*")||0==u.compareTo("/")||0==u.compareTo("(")||0==u.compareTo(")")) {
				System.out.println("La expresion "+expresion+" no es valida.");
				System.exit(0);
			}
		} else if(pila5.size()==2) {
			String xx=(String)pila5.pop();
			String yy=(String)pila5.pop();
			if(0==xx.compareTo("+")||0==xx.compareTo("-")||0==xx.compareTo("*")||0==xx.compareTo("/")||0==yy.compareTo("+")||0==yy.compareTo("-")||0==yy.compareTo("*")||0==yy.compareTo("/")) {
				System.out.println("La expresion "+expresion+" es  valida.");
				System.exit(0);
			}
		} else {
			while(!pila.isEmpty()) {
				String element=(String)pila.pop();
				if(0==element.compareTo(")")&&!pila.isEmpty()) {
					String sim=(String)pila.pop();
					if(0==sim.compareTo("+")||0==sim.compareTo("-")||0==sim.compareTo("*")||0==sim.compareTo("/")) {
						a++;
					}
				}
			}
			if(a!=0) {
				System.out.println("La expresion "+expresion+" no es valida.");
				System.exit(0);
			} else {
				while(!pila2.isEmpty()) {
					String simbolo=(String)pila2.pop();
					if((0==simbolo.compareTo("+")||0==simbolo.compareTo("-")||0==simbolo.compareTo("*")||0==simbolo.compareTo("/"))&&!pila2.isEmpty()) {
						String el=(String)pila2.pop();
						if(0==el.compareTo("(")||0==el.compareTo("+")||0==el.compareTo("-")||0==el.compareTo("*")||0==el.compareTo("/")) {
							b++;
						}
					}
				}
				if(b!=0) {
					System.out.println("La expresion"+expresion+" no es valida.");
				} else { 
					String w=null;
					int c = 0, 
							d = 0, 
							k = 0;
					while(!pila4.isEmpty()) {
						String v=(String)pila4.pop();
						if(0==v.compareTo("(")&&!pila4.isEmpty()) {
							w=(String)pila4.pop();
							if(0==w.compareTo("+")||0==w.compareTo("-")||0==w.compareTo("*")||0==w.compareTo("/")||0==w.compareTo("(")) {
								c++;
							} else {
								k++; 
							}
						}
					}

					if(k!=0) {
						System.out.println("La expresion "+expresion+" no es valida.");
					} else {
						while(!pila3.isEmpty()) {
							String e=(String)pila3.pop();
							if(0==e.compareTo("(")||0==e.compareTo(")")) {
								pp.push(e);
							}
						}
						while(!pp.isEmpty()) {
							ppo.push(pp.pop());
						}
						int ww=0;
						while(!ppo.isEmpty()&&ww!=1) {
							String s=(String)ppo.pop();
							if(0==s.compareTo("(")&&!pilaC.isEmpty()) {
								pilaC.pop();
							} else if (0==s.compareTo("(")&&pilaC.isEmpty()) {
								System.out.println("La expresion "+expresion+" no es valida.");	                            
								ww=1;
							} else {
								pilaC.push(s);
							}
						}
						if(ww==1)
						{
							System.out.println();
						} else if(!pilaC.isEmpty()) {
							System.out.println("La expresion "+expresion+" no es valida.");
						} else {
							System.out.println("La expresion "+expresion +" es valida.");
						}
					}
				}
			}
		}	    	
	}	

	/**
	 * Método principal.
	 * @param args
	 */

	public static void main(String[] args) {	

		boolean caracteres_permitidos=true;
		char[] digitos={'0','1','2','3','4','5','6','7','8','9'},
			   operadores={'*','-','+','(',')'};  
		String captura="";
		AnalizadorAritmetico analizer=new AnalizadorAritmetico();
		Scanner teclado = new Scanner(System.in);
		boolean repetir=false;
		System.out.println("*** ANALIZADOR SINTACTICO DE CADENAS ARITMETICAS ***");
		do {
			System.out.println("\nEscribe una expresion: ");	
			String expresion=teclado.nextLine();
			//Primero se verifica el diccionario
			if(analizer.verificaDiccionario(expresion, digitos, operadores)) {
				//Si los caracteres estan pemitidos, se verifica la sintaxis
				analizer.verificaSintaxis(expresion);
			} else { //Si se introdujo algun caracter no permitido
				System.out.println("La expresion "+ expresion+ " no es valida.");
				//Si la cadena contiene caracteres no permitidos, termina el programa
			}		
			System.out.println("\n¿Desea ingresar otra expresion? S/N");	  
			captura=teclado.nextLine();	    	    
			if(captura.contains("S")||captura.contains("s")) {
				repetir=true;
			} else {
				repetir=false;
				System.out.println("Fin del programa.");
			}	    	    
		} while(repetir);    
		System.exit(0);//termina el programa

	} //Final del main

}