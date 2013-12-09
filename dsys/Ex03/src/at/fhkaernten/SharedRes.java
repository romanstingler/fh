package at.fhkaernten;

public class SharedRes {
	private int value = 0;

	// 1.4

//	public void inc() {
//		synchronized (this) {
//			value++;
//			System.out.println("Inc: " + value);
//		}
//	}
//
//	public void dec() {
//		synchronized (this) {
//			value--;
//			System.out.println("Dec: " + value);
//		}
//	}

	// 1.3
	public synchronized void inc() {
		value++;
		System.out.println("Inc: " + value);
	}

	public synchronized void dec() {
		value--;
		System.out.println("Dec: " + value);
	}

}
