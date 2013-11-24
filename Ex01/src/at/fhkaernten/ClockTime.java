package at.fhkaernten;

// 8.1
public class ClockTime {

	private int seconds;

	// 8.3
	ClockTime() {
		this(0, 0, 0);
	}
	
	// 8.4
	ClockTime(int hours) {
		this(hours,0,0);
	};

	ClockTime(int hours, int minutes) {
		this(hours, minutes, 0);
	}

	ClockTime(int hours, int minutes, int seconds) {
		this.seconds = hours * 3600 + minutes * 60 + seconds;
	}
	
	// 8.7
	public int getSec() {
		return this.seconds;
	}
	
	public int getHours() {
		return this.seconds/3600;
	}
	
	public int getMinutes() {
		return this.seconds/60%60;
	}
	
	public int getSeconds() {
		return this.seconds%60;
	}

	public String getTime() {
		return this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
	}
	
	// 8.5
	public boolean isSame(ClockTime ct) {
		if (this.seconds == ct.getSec())
			return true;
		else
			return false;
	}

	// 8.6
	public int diff(ClockTime ct) {
		int secOther = ct.getSec();

		if (secOther >= this.seconds)
			return (secOther - this.seconds);
		else
			return (secOther - this.seconds + 86400);
	}

}
