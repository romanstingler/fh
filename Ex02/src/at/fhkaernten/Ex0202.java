package at.fhkaernten;

public class Ex0202 extends Object implements Runnable {
	private ThreadID var;

	public Ex0202(ThreadID v) {
		this.var = v;
	}

	public void run() {
		try {
			for (int i = 1; i < 10; i++) {
				print("var getThreadID = " + var.getThreadID());
				Thread.sleep(1000);
				print("var getThreadID = " + var.getThreadID());
			}
		} catch (InterruptedException x) {
		}
	}

	private static void print(String msg) {
		String name = Thread.currentThread().getName();
		System.out.println(name + ": " + msg);
	}

	public static void main(String[] args) {
		ThreadID tid = new ThreadID();
		Ex0202 shared = new Ex0202(tid);

		try {
			Thread threadA = new Thread(shared, "1. Thread");
			threadA.start();
			Thread.sleep((long) (Math.random() * 1000));

			Thread threadB = new Thread(shared, "2. Thread");
			threadB.start();
			Thread.sleep(1000);

			Thread threadC = new Thread(shared, "3. Thread");
			threadC.start();
			// Thread.sleep(1000);
		} catch (InterruptedException x) {
		}
	}
}

class ThreadID extends ThreadLocal<Object> {
	private int nextID;

	public ThreadID() {
		nextID = 111;
	}

	private synchronized Integer getNewID() {
		Integer id = new Integer(nextID);
		nextID += 111;
		return id;
	}

	protected Object initialValue() {
		print("in initialValue()");
		return getNewID();
	}

	public int getThreadID() {
		Integer id = (Integer) get();
		return id.intValue();
	}

	private static void print(String msg) {
		String name = Thread.currentThread().getName();
		System.out.println(name + ": " + msg);
	}
}