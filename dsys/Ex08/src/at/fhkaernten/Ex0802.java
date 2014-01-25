package at.fhkaernten;

import java.util.Scanner;

public class Ex0802 {

	public static void main(String[] args) throws InterruptedException {

		Sauna sauna = new Sauna(2);

		Guest r1 = new Guest(sauna);
		Guest r2 = new Guest(sauna);
		Guest r3 = new Guest(sauna);

		Thread g1 = new Thread(r1);
		Thread g2 = new Thread(r2);
		Thread g3 = new Thread(r3);

		g1.start();
		g2.start();
		g3.start();

		Scanner sc = new Scanner(System.in);
		String exit = "n";

		do {
			exit = sc.next();

		} while (!exit.equals("y"));

		sc.close();

		r1.stopp();
		r2.stopp();
		r3.stopp();

		g1.join();
		g2.join();
		g3.join();

		System.out.println("Gast 1 war " + r1.getOut() + " mal in Sauna.");
		System.out.println("Gast 2 war " + r2.getOut() + " mal in Sauna.");
		System.out.println("Gast 3 war " + r3.getOut() + " mal in Sauna.");
	}

}
