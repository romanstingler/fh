package at.fhkaernten;

import java.util.Calendar;
import java.util.GregorianCalendar;

// 10.3
public class SimpleClock implements at.fhkaernten.Clock {

	private GregorianCalendar calendar;

	SimpleClock() {
		this.calendar = new GregorianCalendar();
	}

	@Override
	public String getTime() {
		return calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE);
	}

}
