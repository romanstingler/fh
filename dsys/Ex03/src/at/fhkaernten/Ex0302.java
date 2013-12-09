package at.fhkaernten;

import java.util.Scanner;

public class Ex0302 {

	// 2.1

	private static int BSIZE = 10;

	public static void main(String[] args) {
		Measurement timeMes = new Measurement(BSIZE);
		Scanner sc = new Scanner(System.in);

		// 2.2
		System.out.println("Anzahl der Sklaven:");
		int nslaves = sc.nextInt();

		for (int i = 0; i < nslaves; i++) {
			(new Thread(new SlaveThread(timeMes))).start();
		}

		(new Thread(new MasterThread(timeMes))).start();

		sc.close();

	}

}
