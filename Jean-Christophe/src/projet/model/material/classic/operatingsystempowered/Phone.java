package projet.model.material.classic.operatingsystempowered;

import projet.model.os.OS;

/**
 * Define an phone
 * 
 * @author Jean-Christophe Isoard
 * 
 */
public class Phone extends OperatingSystemPowered {

	/**
	 * Construct a phone with default timemax parameter and given os
	 * 
	 * @param os
	 *            OS
	 */
	public Phone(OS os) {
		super(365, os);
	}

	/**
	 * Construct a phone with given os and timemax
	 * 
	 * @param os
	 *            OS
	 * @param timeMax
	 *            Integer
	 */
	public Phone(OS os, int timeMax) {
		super(timeMax, os);
	}
}
