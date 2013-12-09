package at.fhkaernten;

public class Ex0201 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 1.1
		// Provide a Runnable object. The Runnable interface defines a single
		// method, run, meant to contain the code executed in the thread. The
		// Runnable object is passed to the Thread constructor.
		//		class ImplementsRunnable implements Runnable {
		//			 private int counter = 0;
		//
		//			 public void run() {
		//			     counter++;
		//			     System.out.println("ImplementsRunnable : Counter : " + counter);
		//			  }
		//			 }
		//		}
		//		public static void main(String args[]) throws Exception {
		//		    ImplementsRunnable rc = new ImplementsRunnable();
		//		    Thread t1 = new Thread(rc);
		//		    t1.start();
		//		}

		// Subclass Thread. The Thread class itself implements Runnable, though
		// its run method does nothing. An application can subclass Thread,
		// providing its own implementation of run.
		//		class ExtendsThread extends Thread {
		//			private int counter = 0;
		//
		//			public void run() {
		//			    counter++;
		//			    System.out.println("ExtendsThread : Counter : " + counter);
		//			 }
		//		}
		//		public static void main(String args[]) throws Exception {
		//		    ExtendsThread tc1 = new ExtendsThread();
		//		    tc1.start();
		//		}

		// It is recommend using something like Runnable rather than Thread
		// because it allows to keep work only loosely coupled with concurrency.
		// For example, if you use a Runnable and
		// decide later on that this doesn't in fact require it's own Thread,
		// you can just call threadA.run().

		// 1.2
		// Access to variable should be protected by a synchronized block
		
		// public class A {
		// 	public final Object variable;
		// 	public void update() {
		// 		synchronized(variable) {
		// 			variable.complexAlgorithm();
		// 		}
		// 	}
		// }
		//
		// public class B {
		// 	public A a;
		// 	public void update() {
		// 		sychronized(a.variable) {
		// 			consume(a.variable);
		// 		}
		// 	}
		// }
		// Also the 'volatile' modifier can be used, such as semaphores or blocking queues. 

		// 1.3
		// A spinlock is a lock which causes a thread trying to acquire it to
		// simply wait in a loop ("spin") while repeatedly checking if the lock
		// is available. Since the thread remains active but is not performing a
		// useful task, the use of such a lock is a kind of busy waiting.
		//		while(!hasPerformedAction()){
		//		    //wait for the user to perform the action
		//		}
		//		setHasPerformedAction(false);
		//		return getActionPerfomed();

		// 1.4
		// The best thing to do would be to synchronize/wait/notify on a shared
		// variable.
		// Thread1:
		// Object shared = new Object();
		// startThread2(shared);
		// synchronized (shared) {
		// 	while (taskNotDone())
		// 	shared.wait();
		// }
		// Thread2:
		// shared was saved at the start of the thread
		// processing stuff
		// markTaskAsDone();
		// synchronized (shared) {
		// 	shared.notify();
		// }
	}

}
