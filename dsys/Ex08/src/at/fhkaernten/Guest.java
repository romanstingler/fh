package at.fhkaernten;

public class Guest implements Runnable {
	private Sauna sauna;
	private int out = 0;

	private boolean stop = false;

	public Guest(Sauna sauna) {
		this.sauna = sauna;
	}

	public int getOut() {
		return out;
	}

	@Override
	public void run() {
		while (!stop) {

			try {
				sauna.goIn();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			System.out.println(Thread.currentThread().getId()
					+ " geht in Sauna.");
			out++;

			try {
				Thread.sleep((int) (Math.random() * 1000) + 1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			sauna.goOut();

			System.out.println(Thread.currentThread().getId()
					+ " kommt aus Sauna.");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void stopp() {
		stop = true;
	}

}
