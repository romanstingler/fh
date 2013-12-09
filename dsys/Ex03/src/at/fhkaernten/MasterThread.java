package at.fhkaernten;

public class MasterThread implements Runnable {

	private Measurement mes;

	public MasterThread(Measurement mes) {
		this.mes = mes;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Messung: " + mes.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
