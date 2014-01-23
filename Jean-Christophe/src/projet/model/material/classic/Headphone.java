package projet.model.material.classic;

/**
 * Represent an headphone
 * 
 * @author Jean-Christophe Isoard
 * 
 */

public class Headphone extends Classic {

	/**
	 * Construct an Headphone with default parameter timemax = 15
	 */
	public Headphone() {
		super(15);
	}

	/**
	 * Construct an Headphone with parameter timeMax given
	 * 
	 * @param timeMax
	 *            Integer
	 */
	public Headphone(int timeMax) {
		super(timeMax);
	}
}
