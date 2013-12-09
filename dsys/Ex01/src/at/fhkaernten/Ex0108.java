package at.fhkaernten;

import java.util.Scanner;

public class Ex0108 {

	// 8.8
	public static void main(String[] args) {
		ClockTime ct1 = new ClockTime(), ct2 = new ClockTime(23, 12, 1);
		if (ct1.isSame(ct2))
			System.out.println("Uhren gleich");
		System.out.println(ct1.getTime());
		System.out.println(ct2.getTime());
		System.out.println(ct1.diff(ct2));
		System.out.println(ct2.diff(ct1));

		// 8.2
		// H:mm:ss, can be stored as <int>seconds and calculated afterwards
		// Should be set to private and be accessoible via getters and setters

		// 8.9
		ClockTime cl1 = null;
		ClockTime cl2 = null;
		Scanner sc = new Scanner(System.in);
		System.out
				.println("\nEingabeformat hh,mm,ss (Minuten und Sekunden Optional(10,30,40))");
		System.out.println("Eingabe Uhrzeit 1: ");
		String c1 = sc.next();
		String[] ary1 = c1.split(":");

		if (ary1.length == 1) {
			cl1 = new ClockTime(Integer.parseInt(ary1[0]));
		} else if (ary1.length == 2) {
			cl1 = new ClockTime(Integer.parseInt(ary1[0]),
					Integer.parseInt(ary1[1]));
		} else if (ary1.length == 3) {
			cl1 = new ClockTime(Integer.parseInt(ary1[0]),
					Integer.parseInt(ary1[1]), Integer.parseInt(ary1[2]));
		}

		System.out.println("Eingabe Uhrzeit 2: ");
		String c2 = sc.next();
		String[] ary2 = c2.split(":");

		if (ary2.length == 1) {
			cl2 = new ClockTime(Integer.parseInt(ary2[0]));
		} else if (ary2.length == 2) {
			cl2 = new ClockTime(Integer.parseInt(ary2[0]),
					Integer.parseInt(ary2[1]));
		} else if (ary2.length == 3) {
			cl2 = new ClockTime(Integer.parseInt(ary2[0]),
					Integer.parseInt(ary2[1]), Integer.parseInt(ary2[2]));
		}
		System.out.println(cl1.getTime());
		System.out.println(cl2.getTime());
		System.out.println(cl1.diff(cl2));

		sc.close();
	}

}
