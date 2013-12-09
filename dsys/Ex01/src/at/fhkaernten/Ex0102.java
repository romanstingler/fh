package at.fhkaernten;

import java.util.Scanner;

public class Ex0102 {

	private static int fiboRec(int k) {
		if (k <= 1)
			return k;
		else
			return fiboRec(k - 1) + fiboRec(k - 2);
	}

	private static int fiboIt(int k) {
		if (k <= 1)
			return k;
		int prev = 1;
		int pprev = 0;
		int current = 0;
		for (int i = 1; i < k; i++) {
			current = prev + pprev;
			pprev = prev;
			prev = current;

		}
		return current;
	}

	public static void main(String[] args) {
		// 2.1
		Scanner sc = new Scanner(System.in);
		float zahl = 0;
		try {
			System.out.println("Bitte eine Zahl: ");
			zahl = sc.nextFloat();
		} catch (Exception e) {
			System.err.println("Das ist keine Zahl");
			System.exit(1);
		}
		if (zahl >= 0) {
			System.out.println(String.format("Die Zahl %f ist positiv.", zahl));
		} else
			System.out.println(String.format("Die Zahl %f ist negativ", zahl));

		System.out.println();

		// 2.2
		System.out.println("Anzahl Sterne: ");
		int m = sc.nextInt();
		while (m < 1) {
			System.out.println("Bitte positive Zahl eingeben: ");
			m = sc.nextInt();
		}
		do {
			System.out.print("*");
		} while (--m > 0);
		System.out.println();

		// 2.3
		System.out.println("Anzahl Sterne: ");
		int n = sc.nextInt();
		while (--n >= 0) {
			System.out.print("*");
		}
		System.out.println();

		// 2.4
		System.out.println("Anzahl Sterne: ");
		int o = sc.nextInt();
		for (int i = 0; i < o; i++) {
			System.out.print("*");
		}
		System.out.println();

		// 2.5
		System.out.println("Bitte Breite: ");
		int a = sc.nextInt();
		System.out.println("Bitte Höhe: ");
		int b = sc.nextInt();
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < a; j++)
				System.out.print("*");
			System.out.println();
		}
		System.out.println();

		/**
		 * //2.6 Bei einer For-Schleife zählt der Computer von einer Anfangszahl
		 * bis zu einer Endzahl und wiederholt dabei jedes mal den Codeblock.
		 * Bei einer While-Schleife erfolgt die Abfrage der Bedingung, bevor der
		 * Schleifenrumpf ausgeführt wird, also am Kopf des Konstruktes. Solange
		 * eine Bedingung wahr ist, läuft die Schleife. Bei einer
		 * Do-While-Schleife erfolgt die Abfrage der Bedingung, nachdem der
		 * Schleifenrumpf ausgeführt wurde, also am Fuß des Konstruktes.
		 * 
		 * // 2.7 Mithilfe von foreach wird das Durchlaufen von z.B Arrays
		 * wesentlich vereinfacht"); for( int k: array ) { System.out.println('k
		 * = '+k); } Vorteil: Man muss sich nicht um die Laufvariable kümmern
		 **/

		// 2.8
		System.out.println("Fibonacci Folge - k eingeben: ");
		int k = sc.nextInt();
		System.out.println("Rekursiv " + fiboRec(k));
		System.out.println("Iterativ " + fiboIt(k));

		sc.close();

	}

}