package at.fhkaernten;

public class Sauna {
	private int occupiedSeats;
	private int maxSeats;

	public Sauna(int maxSeats) {
		this.maxSeats = maxSeats;
		this.occupiedSeats = 0;
	}

	public synchronized void acquire() throws InterruptedException {
		while (occupiedSeats >= maxSeats)
			wait();
		occupiedSeats++;
		System.out.println("Sitz wurde besetzt, " + occupiedSeats
				+ " Sitze belegt.");
	}

	public synchronized void release() {
		this.occupiedSeats--;
		System.out.println("Sitz wurde frei, " + occupiedSeats
				+ " Sitze belegt.");
		notifyAll();
	}

	public synchronized int availablePermits() {
		return (maxSeats - occupiedSeats);
	}
}
