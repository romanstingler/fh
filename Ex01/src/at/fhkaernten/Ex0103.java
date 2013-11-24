package at.fhkaernten;

import java.util.Scanner;

public class Ex0103 {

	public static void main(String[] args) {
		// 3.1
		Scanner sc = new Scanner(System.in);
		String s;

		do {
			System.out.println("Do you really want to continue? [yn]");
			s = sc.next();

			// 3.2
			if (s.equals("y")) {
				System.out.println("Erste Zahl: ");
				double z1 = sc.nextDouble();
				System.out.println("Zweite Zahl:");
				double z2 = sc.nextDouble();
				System.out
						.println("+ für addieren, * für multiplizieren, / für dividieren, - für subtrahieren");
				char op = sc.next().charAt(0);
				switch (op) {
				case '+':
					System.out.println(z1 + z2);
					break;
				case '*':
					System.out.println(z1 * z2);
					break;
				case '/':
					System.out.println(z1 / z2);
					break;
				case '-':
					System.out.println(z1 - z2);
					break;
				default:
					System.out.println("Ungültiger Operator.");
					break;
				}
			}
		} while (!s.equals("n"));
		sc.close();

		// 3.3
		// Bei switch wird immer nur ein case ausgeführt, was effizienter ist
		// als im schlimmsten fall bei if da hierbei alle Bedingungen geprüft
		// werden müssten.

		// 3.4
		// Break unterbricht eine Schleife dabei kann angegeben werden ob nur
		// die innere oder auch die äußeren schleifen abgebrochen werden.
		// Continue führt die Schleife weiter aus.
	}
}
