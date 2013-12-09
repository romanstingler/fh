package at.fhkaernten;

public class Threads2 extends Thread{
	
	private long start, end;
	private int step;
	private double sum=0;
	
	Threads2() {
		this(1,10000000,1);
	}
	
	Threads2(long start, long end) {
		this(start,end,1);
	}
	
	Threads2(long start, long end,int step) {
		super();
		this.step=step;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		sum=0;
		for(long i = start; i <= end;i+=step) {
			sum+=Math.sqrt((double)i);
		}
		System.out.println("Thread: " + getId() + " Ergebnis: " + sum + " Bereich: " + start + " - " + end);
	}
	
	double getSum() {
		return sum;
	}

}
