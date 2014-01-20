package projet.model.material.classic.operatingsystempowered;

import projet.model.os.OS;

/**
 * Define a tablette
 * 
 * @author user
 * 
 */
public class Tablette extends OperatingSystemPowered {

	/**
	 * Construct a tablette with os given by user and default timemax value
	 * 
	 * @param os
	 *            OS
	 */
	public Tablette(OS os) {
		super(180, os);
	}

	/**
	 * Construct a tablette with os and timemax given
	 * 
	 * @param os
	 *            OS
	 * @param timeMax
	 *            Integer
	 */
	public Tablette(OS os, int timeMax) {
		super(timeMax, os);
	}
}
