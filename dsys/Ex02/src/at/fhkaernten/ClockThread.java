package at.fhkaernten;

import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ClockThread extends Threads1 {

	@Override
	public void run() {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
		Date date = new Date();
		TimeZone tz = TimeZone.getDefault();
		
		long msec = date.getTime() % 86400000 + tz.getOffset(date.getTime());

		while (!stopped) {
			date = new Date();
			System.out.println("Systemzeit: " + dateFormat.format(date)
					+ " ClockThread Zeit: " + msec / 3600000 + ":" + msec
					/ 60000 % 60 + ":" + msec / 1000 % 60 + ":" + msec
					% 1000);
			try {
				Thread.sleep(1000);
				msec += 1000;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
