package at.fhkaernten;

import java.util.Scanner;

public class Ex0204 {

	public static void main(String[] args) throws InterruptedException {
		// 4.1
		int n = 0;
		double erg = 0;
		Scanner sc = new Scanner(System.in);

		System.out.println("Anzahl Threads: ");
		n = sc.nextInt();
		System.out.println("Endwert: ");
		long endw = sc.nextLong();
		if (n > endw)
			n = (int) endw;
		sc.close();
		double range = (double) endw / n;

		long start = System.currentTimeMillis();
		Threads2[] t = new Threads2[n];
		for (int i = 0; i < n; i++) {
			t[i] = new Threads2((long) (1 + (range * i)),
					(long) (range + (range * i)));
			t[i].start();
		}

		// 4.2
		for (int i = 0; i < n; i++) {
			t[i].join();
			erg += t[i].getSum();
		}
		long end = System.currentTimeMillis();

		// 4.3
		// Die optimale Anzahl von Threads hängt von der Anzahl der CPU-Kerne
		// und Technologien wie Hyperthreading ab.
		// Im Allgemeinen sollte das beste Ergebnis bei Threads = Anzahl
		// logische Kerne zu erzielen sein. Es zeigen sich aber auch
		// Unterschiede bei verschiedenen Java VMs (Oracle, Open JDK) und
		// Betriebssystemen.

		System.out.println("Ergebnis: " + erg);
		System.out.println("Zeit: " + (end - start) + "ms");
	}
}
