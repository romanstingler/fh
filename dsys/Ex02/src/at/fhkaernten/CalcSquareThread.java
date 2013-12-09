package at.fhkaernten;

public class CalcSquareThread extends Thread {

	double from = 0;
	double to = 100000000l;
	public double sum;

	public CalcSquareThread(double i, double l) {
		this.from = i;
		this.to = l;
	}

	// 3.2
	public void run() {
		double sum = 0;
		for (double i = from; i <= to; i++) {
			sum += Math.sqrt(i);
		}
		setSum(sum);
	}

	private void setSum(double sum2) {
		this.sum = sum2;

	}

	public double getSum() {
		return sum;
	}

}
