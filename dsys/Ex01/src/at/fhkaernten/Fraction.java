package at.fhkaernten;

public class Fraction {
	int n; // numerator
	int d; // denominator

	Fraction() {
		n = 0;
		d = 1;
	}

	Fraction(int n, int d) {
		this.n = n;
		this.d = d;
	}

	void add(Fraction f) {
		n = n * f.d + f.n * d;
		d = d * f.d;
	}

	void subtract(Fraction f) {
		n = n * f.d - f.n * d;
		d = d * f.d;
	}

	void multiply(Fraction f) {
		n = n * f.n;
		d = d * f.d;
	}

	void divide(Fraction f) {
		int n0 = n * f.d;
		d = d * f.n;
		n = n0;
	}
}
