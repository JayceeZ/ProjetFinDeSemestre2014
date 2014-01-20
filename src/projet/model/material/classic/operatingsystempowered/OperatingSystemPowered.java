package projet.model.material.classic.operatingsystempowered;

import projet.model.material.classic.Classic;
import projet.model.os.OS;

/**
 * Define a material that is os powered
 * 
 * @author Jean-Christophe
 * 
 */

public abstract class OperatingSystemPowered extends Classic {
	private OS os;

	/**
	 * Construct a material with timemax parameter and os
	 * 
	 * @param tm
	 *            Integer
	 * @param os
	 *            OS (see OS.java)
	 */
	public OperatingSystemPowered(int tm, OS os) {
		super(tm);
		this.os = os;
	}

	/**
	 * Send back the OS
	 * 
	 * @return os instanceof OS
	 */
	public OS getOs() {
		return os;
	}

	@Override
	public String toString() {
		return super.toString() + os.toString();
	}
}
