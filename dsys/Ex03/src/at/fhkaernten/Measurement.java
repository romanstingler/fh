package at.fhkaernten;

public class Measurement {
	private long[] values;
	private int count = 0, ipos = 0, opos = 0;

	public Measurement(int nvalues) {
		this.values = new long[nvalues];
	}

	public synchronized void put(long value) throws InterruptedException {
		while (count >= values.length)
			wait();
		values[ipos] = value;
		count++;
		ipos = (ipos + 1) % values.length;
		notifyAll();
	}

	public synchronized long get() throws InterruptedException {
		while (count <= 0)
			wait();
		long value = values[opos];
		count--;
		opos = (opos + 1) % values.length;
		notifyAll();
		return value;

	}

}
