package at.fhkaernten;

import java.util.Scanner;

public class Ex0203 {

	public static void main(String[] args) throws InterruptedException {
		// 3.1
		Scanner sc = new Scanner(System.in);
		int num = 0;

		System.out.println("Enter number of threads: ");
		num = sc.nextInt();
		CalcSquareThread[] t = new CalcSquareThread[num];

		for (int i = 0; i < num; i++) {
			t[i] = new CalcSquareThread(0, 1000000000l);
			t[i].start();
		}

		// 3.3
		for (int i = 0; i < num; i++) {
			t[i].join();
			System.out.println(t[i].getSum());
		}
		sc.close();
	}
}