package at.fhkaernten;

public class Gcd {
	public static int getGcd(int a, int b) {
		while (a != 0) {
			int tmp = a;
			a = b % a;
			b = tmp;
		}
		return b;
	}
}
