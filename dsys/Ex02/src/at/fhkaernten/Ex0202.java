package at.fhkaernten;

public class Ex0202 {
	public static void main(String[] args) throws InterruptedException {

		XThread xt1 = new XThread();
		XThread xt2 = new XThread();
		XThread xt3 = new XThread();

		Thread t1 = new Thread(xt1);
		Thread t2 = new Thread(xt2);
		Thread t3 = new Thread(xt3);

		t1.start();
		t2.start();
		t3.start();

		Thread.sleep(1000);
		xt1.haltRun();
		t1.join();
		Thread.sleep(2000);
		xt2.haltRun();
		t2.join();
		Thread.sleep(2000);
		xt3.haltRun();
		t3.join();

	}

}
