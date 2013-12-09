package at.fhkaernten;

public class Child implements Runnable {
	private Item privateItem;
	private Cupboard cupboard;
	private int out = 0;

	private boolean stop = false;

	public Child(Cupboard cupboard, Item privateItem) {
		this.privateItem = privateItem;
		this.cupboard = cupboard;
	}

	public int getOut() {
		return out;
	}

	@Override
	public void run() {
		// System.out.println(Thread.currentThread().getId() + " hat "
		// + privateItem + ".");

		Cap c = null;
		Gloves g = null;
		Scarf s = null;

		while (!stop) {
			if (!(privateItem instanceof Cap)) {
				try {
					c = cupboard.getCcap();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(Thread.currentThread().getId()
				// + " hat Kappe.");
			}

			if (!(privateItem instanceof Gloves)) {
				try {
					g = cupboard.getCgloves();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(Thread.currentThread().getId()
				// + " hat Handschuhe.");
			}

			if (!(privateItem instanceof Scarf)) {
				try {
					s = cupboard.getCscarf();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(Thread.currentThread().getId()
				// + " hat Schal.");
			}

			System.out.println(Thread.currentThread().getId()
					+ " geht aus dem Haus.");
			out++;

			if (!(privateItem instanceof Cap)) {
				cupboard.putCcap(c);
				// System.out.println(Thread.currentThread().getId()
				// + " gibt Kappe zurück.");
			}

			if (!(privateItem instanceof Gloves)) {
				cupboard.putCgloves(g);
				// System.out.println(Thread.currentThread().getId()
				// + " gibt Handschuhe zurück.");
			}

			if (!(privateItem instanceof Scarf)) {
				cupboard.putCscarf(s);
				// System.out.println(Thread.currentThread().getId()
				// + " gibt Schal zurück.");
			}

			System.out.println(Thread.currentThread().getId()
					+ " kommt zurück.");

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
