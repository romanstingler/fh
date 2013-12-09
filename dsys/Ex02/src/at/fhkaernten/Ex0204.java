package at.fhkaernten;

import java.util.Scanner;

public class Ex0204 {
	public static void main(String[] args) throws InterruptedException {

		// 4.1
		Scanner sc = new Scanner(System.in);
		int num = 0;
		double from = 0;
		double to = 1000000000l;
		double sum = 0;

		System.out.println("Enter number of threads: ");
		num = sc.nextInt();
		CalcSquareThread[] t = new CalcSquareThread[num];

		long x = (long) (to / num);
		long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			from = i * x + 1;
			to = (i + 1) * x;
			t[i] = new CalcSquareThread(from, to);
			t[i].start();
		}

		// 4.3
		for (int i = 0; i < num; i++) {
			t[i].join();
			sum += t[i].getSum();
		}
		long end = System.currentTimeMillis();
		System.out.println(sum);
		System.out.println("Duration: " + (end - start) + "ms");

		sc.close();
	}

}