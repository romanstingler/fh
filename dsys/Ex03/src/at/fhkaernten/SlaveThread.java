package at.fhkaernten;

public class SlaveThread implements Runnable {

	private Measurement mes;

	public SlaveThread(Measurement mes) {
		this.mes = mes;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.currentThread().getId();
				mes.put(System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
