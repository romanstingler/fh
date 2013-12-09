package at.fhkaernten;

public class Ex0301 {

	public static void main(String[] args) throws InterruptedException {
		// 1.1

		SharedRes sr = new SharedRes();

		(new Thread(new ThreadAB(sr, '+'))).start();
		(new Thread(new ThreadAB(sr, '-'))).start();
	}

}
