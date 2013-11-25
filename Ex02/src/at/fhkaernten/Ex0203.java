package at.fhkaernten;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex0203 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 3.1
		Scanner sc = new Scanner(System.in);
		int num = 0;
		try {
			System.out.println("Enter number of threads: ");
			num = sc.nextInt();
			ExecutorService threadPool = Executors.newFixedThreadPool(num);
			for (int i = 0; i < num; i++) {
				threadPool.submit(new Runnable() {
					// 3.1
					public void run() {
						long threadId = Thread.currentThread().getId();
						System.out.println("Thread # " + threadId
								+ " is doing this task");
						long sum = 0;
						for (long x = 1; x <= 1000000000; x++) {
							sum += Math.sqrt(x);
						}
						System.out.println(sum);
					}
				});
			}
			threadPool.shutdown();
			try {
				if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
					threadPool.shutdownNow();
					if (!threadPool.awaitTermination(60, TimeUnit.SECONDS))
						System.err.println("Pool did not terminate");
				}
			} catch (InterruptedException ie) {
				threadPool.shutdownNow();
				Thread.currentThread().interrupt();
			}
		} finally {
			sc.close();
		}
	}
}
