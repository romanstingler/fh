package at.fhkaernten;

public class XThread implements Runnable {

	boolean halted = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (!halted) {
			System.out
					.print("Thread: " + Thread.currentThread().getId() + "\n");
		}
	}

	public void haltRun() {
		halted = true;
	}
}