package at.fhkaernten;

public class Sauna {
	private int occupiedSeats;
	private int maxSeats;

	public Sauna(int maxSeats) {
		this.maxSeats = maxSeats;
		this.occupiedSeats = 0;
	}

	public synchronized void goIn() throws InterruptedException {
		while (occupiedSeats >= maxSeats)
			wait();
		occupiedSeats++;
	}

	public synchronized void goOut() {
		this.occupiedSeats--;
		notifyAll();
	}
}
