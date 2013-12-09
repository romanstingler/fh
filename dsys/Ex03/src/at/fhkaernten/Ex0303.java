package at.fhkaernten;

import java.util.Scanner;

public class Ex0303 {

	public static void main(String[] args) throws InterruptedException {

		// 3.1
		// Die Kinder werden als Thread implementiert, die auf eine gemeinsame
		// Ressource zugreifen.

		// 3.2
		// Die gemeinsame Ressource ist das Cupboard mit den Items Schal,
		// Handschuhe und Kappe.
		// Ja, ein Deadlock ist möglich, die Kinder benötigen gemeinsame
		// Ressourcen. Daher Verwendung von synchronisierten Methoden für das
		// entfernen, zurücklegen der einzelnen Items und Verwendung von wait
		// und notify.

		// 3.3
		// Starvation ist möglich, das Kind das die erste Item vom Cupboard
		// benötigt ist im Vorteil und blockiert die anderen.
		// Daher "schläft" jedes Kind nachdem es wieder in das Haus gekommen
		// ist, dadurch erhalten die anderen Kinder die Möglichkeit sich die
		// Items zu holen.

		Cupboard cupboard = new Cupboard(new Cap(), new Gloves(), new Scarf());

		Child r1 = new Child(cupboard, new Cap());
		Child r2 = new Child(cupboard, new Gloves());
		Child r3 = new Child(cupboard, new Scarf());

		Thread c1 = new Thread(r1);
		Thread c2 = new Thread(r2);
		Thread c3 = new Thread(r3);

		c1.start();
		c2.start();
		c3.start();

		Scanner sc = new Scanner(System.in);
		String exit = "n";

		do {
			exit = sc.next();

		} while (!exit.equals("y"));

		sc.close();

		r1.stopp();
		r2.stopp();
		r3.stopp();

		c1.join();
		c2.join();
		c3.join();

		System.out.println("Kind 1 war " + r1.getOut() + " mal aus dem Haus.");
		System.out.println("Kind 2 war " + r2.getOut() + " mal aus dem Haus.");
		System.out.println("Kind 3 war " + r3.getOut() + " mal aus dem Haus.");
	}

}
