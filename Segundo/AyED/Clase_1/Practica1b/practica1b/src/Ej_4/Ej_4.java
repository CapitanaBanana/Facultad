/*4. ¿Qué imprime el siguiente programa al ejecutar main?
a. Intente averiguarlo sin ejecutar el programa en su computadora.
b. Ejecute el ejercicio en su computadora, y compare su resultado con lo esperado en el
inciso anterior.*/
package Ej_4;

import java.io.Console;

public class Ej_4 {
	public static void main(String[] args){
		int a = 1, b = 2;
	Integer c = 3, d = 4;
swap1(a,b);
swap2(c,d);
System.out.println("a=" + a + " b=" + b) ;
System.out.println("c=" + c + " d=" + d) ;

	}

	public static void swap1 (int x, int y) {
	if (x < y) {
		int tmp = x ;
		x = y ;
		y = tmp;
	}
	System.out.println("x=" + x+ " y=" + y) ;
	}
	public static void swap2 (Integer x, Integer y) {
	if (x < y) {
	int tmp = x ;
	x = y ;
	y = tmp;
	}
	System.out.println("x=" + x+ " y=" + y) ;
	}
}
