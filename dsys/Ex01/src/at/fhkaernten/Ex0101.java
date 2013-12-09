package at.fhkaernten;

import java.util.Scanner;

public class Ex0101 {

	public static void main(String[] args) {
		// 1.1
		System.out.println("Primitive Datentypen in Java");
		System.out.println("Typ \t Von \t Bis \t Größe");
		System.out.println(String.format("%s %s \t %s \t %d", "boolean",
				Boolean.FALSE, Boolean.TRUE, 0));
		System.out.println(String.format("%s \t %d \t %d \t %d", "byte",
				Byte.MIN_VALUE, Byte.MAX_VALUE, Byte.SIZE / 8));
		System.out.println(String.format("%s \t %c \t %c \t %d", "char",
				Character.MIN_VALUE, Character.MAX_VALUE, Character.SIZE / 8));
		System.out.println(String.format("%s \t %d \t %d \t %d", "short",
				Short.MIN_VALUE, Short.MAX_VALUE, Short.SIZE / 8));
		System.out.println(String.format("%s \t %d \t %d \t %d", "int",
				Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.SIZE / 8));
		System.out.println(String.format("%s \t %d \t %d \t %d", "long",
				Long.MIN_VALUE, Long.MAX_VALUE, Long.SIZE / 8));
		System.out.println(String.format("%s \t %f \t %f \t %d", "float",
				Float.MIN_VALUE, Float.MAX_VALUE, Float.SIZE / 8));
		System.out.println(String.format("%s \t %f \t %f \t %d", "double",
				Double.MIN_VALUE, Double.MAX_VALUE, Double.SIZE / 8));

		// 1.2
		System.out.println("2 / 3 = " + 2 / 3); // integer division
		System.out.println("2 / 3.0 = " + 2 / 3.0); // double division
		System.out.println("2 / 3.0 = " + 2 / 3f); // float division
		System.out.println("2 / 3.0 = " + (double) 2 / 3);

		/*
		 * 2 / 3.0 ist anders, weil Java 3.0 als double nimmt und darum die
		 * double division ausführt.
		 */

		// 1.3
		Scanner sc = new Scanner(System.in);
		System.out.println("Wie heißt du?");
		String s = sc.next();
		System.out.println("Hallo " + s);
		sc.close();
	}

}
