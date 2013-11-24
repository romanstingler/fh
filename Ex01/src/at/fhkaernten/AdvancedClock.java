package at.fhkaernten;

import java.util.Calendar;
import java.util.GregorianCalendar;

// 10.4
public class AdvancedClock extends SimpleClock implements
		at.fhkaernten.Calendar {

	private GregorianCalendar calendar;

	AdvancedClock() {
		this.calendar = new GregorianCalendar();
	}

	@Override
	public String getDate() {
		return calendar.get(Calendar.MONTH) + 1 + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.get(Calendar.YEAR);
	}

}
