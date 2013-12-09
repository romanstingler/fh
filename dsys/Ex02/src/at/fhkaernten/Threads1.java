package at.fhkaernten;

public class Threads1 implements Runnable {

	protected int delay;
	protected boolean stopped = false;

	Threads1() {
		this(1000);
	}

	Threads1(int delay) {
		this.delay = delay;
	}

	public static void createThreadArray(Thread[] t, Threads1[] r, int[] delay) {

		for (int i = 0; i < t.length; i++) {
			r[i] = new Threads1(delay[i]);
			t[i] = new Thread(r[i]);
			t[i].start();
		}
	}

	public static void createThreadArray(Thread[] t, Threads1[] r) {
		for (int i = 0; i < t.length; i++) {
			r[i] = new Threads1();
			t[i] = new Thread(r[i]);
			t[i].start();
		}
	}

	public static void stopThreadArray(Threads1[] r) {
		for (int i = 0; i < r.length; i++) {
			r[i].stopp();
		}
	}

	@Override
	public void run() {
		while (!stopped) {
			System.out.println("Thread: " + Thread.currentThread().getId());
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopp() {
		stopped = true;
	}

}
