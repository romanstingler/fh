package at.fhkaernten;

public class ThreadAB implements Runnable {
	private SharedRes sharedVar;
	private char op;

	public ThreadAB(SharedRes sharedVar, char op) {
		this.sharedVar = sharedVar;
		this.op = op;
	}

	@Override
	public void run() {
		while (true) {
			switch (op) {
			case '+':
				sharedVar.inc();
				break;
			case '-':
				sharedVar.dec();
				break;
			default:
				sharedVar.inc();
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
