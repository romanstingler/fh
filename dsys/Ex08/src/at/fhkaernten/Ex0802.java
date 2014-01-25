package at.fhkaernten;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Ex0802 {

	public final static int MAX_GUESTS = 10;
	public final static int SEATS = 4;

	public static void main(String[] args) throws InterruptedException {

		Semaphore sauna = new Semaphore(SEATS);
		// Sauna sauna = new Sauna(SEATS);

		Guest[] guests = new Guest[MAX_GUESTS];
		Thread[] threads = new Thread[MAX_GUESTS];

		for (int i = 0; i < MAX_GUESTS; i++) {
			guests[i] = new Guest(sauna);
			threads[i] = new Thread(guests[i]);
			threads[i].start();
		}

		Scanner sc = new Scanner(System.in);
		String exit = "n";

		do {
			exit = sc.next();

		} while (!exit.equals("y"));

		sc.close();

		for (int i = 0; i < MAX_GUESTS; i++) {
			guests[i].stopp();
			threads[i].join();
		}

		for (int i = 0; i < MAX_GUESTS; i++)
			System.out.println("Gast " + threads[i].getId() + " war "
					+ guests[i].getOut() + " mal in Sauna.");
	}
}
