package at.fhkaernten;

public class SubFraction extends Fraction {

	SubFraction() {
		super();
	}

	SubFraction(int n, int d) {
		super(n, d);
	}

	private void reduce() {
		int gcd = Gcd.getGcd(this.d, this.n);
		this.d = this.d / gcd;
		this.n = this.n / gcd;

	}

	String getFraction() {
		this.reduce();
		return this.n + "/" + this.d;
	}

	@Override
	void add(Fraction f) {
		super.add(f);
		this.reduce();
	}

	@Override
	void subtract(Fraction f) {
		super.subtract(f);
		this.reduce();
	}

	@Override
	void multiply(Fraction f) {
		super.multiply(f);
		this.reduce();
	}

	@Override
	void divide(Fraction f) {
		super.divide(f);
		this.reduce();
	}

}
