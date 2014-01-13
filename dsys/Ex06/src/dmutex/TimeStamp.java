package dmutex;

import java.io.Serializable;
import java.util.Random;

public class TimeStamp implements Serializable {

	private static final long serialVersionUID = 1L;

	int ID; // Processor-ID (Node-ID)
	int time; // Local Time

	public TimeStamp(int ID) {
		this.ID = ID;
		this.time = (new Random()).nextInt(100) + 1;
	}

	public TimeStamp(int ID, int time) {
		this.ID = ID;
		this.time = time;
	}

	public boolean isSmaller(TimeStamp ts) {
		if (this.time < ts.getTime()) {
			return true;
		} else {
			if (this.time == ts.getTime()) {
				if (this.ID < ts.getID()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public TimeStamp next() {
		return new TimeStamp(this.ID, this.time + 1);
	}

	public TimeStamp next(TimeStamp ts) {
		TimeStamp newts = new TimeStamp(ID, time + 1);
		if (newts.getTime() <= ts.getTime()) {
			// System.out.println("corrected timestamp from " + newts + " to " +
			// (ts.getTime()+1) + "." + ID);
			newts.setTime(ts.getTime() + 1);
		}

		return newts;
	}

	public String toString() {
		return "" + this.time + "." + this.ID;
	}

	public int getTime() {
		return time;
	}

	public int getID() {
		return ID;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
