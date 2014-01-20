package projet.model.date;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * For date manipulations
 * 
 * @author Jean-Christophe Isoard
 * 
 */
public class Date {
	static DateFormat df = DateFormat.getDateInstance();
	GregorianCalendar g;

	/**
	 * Create a date with the actual time
	 */
	public Date() {
		g = (GregorianCalendar) Calendar.getInstance();
	}

	/**
	 * Create a date with the actual time plus the days number given
	 * 
	 * @param after
	 *            Number of days to add from now
	 */
	public Date(int after) {
		this();
		g.add(GregorianCalendar.DAY_OF_MONTH, after);
	}

	@Override
	public String toString() {
		return df.format(g.getTime());
	}

	@Override
	public boolean equals(Object d) {
		if (!(d instanceof Date)) {
			if (this.toString() == d.toString())
				return true;
		}
		return true;
	}

	/**
	 * Get the gregorian calendar
	 * 
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getG() {
		return g;
	}

	/**
	 * Set the gregorian calendar
	 * 
	 * @param g
	 *            GregorianCalendar
	 */
	public void setG(GregorianCalendar g) {
		this.g = g;
	}
}
