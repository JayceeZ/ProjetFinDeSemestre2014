/**
 * @author Isoard Jean-Christophe
 */

package projet.model.material.specific;

import projet.model.material.Material;

/**
 * Define a specific material
 * 
 * @author Jean-Christophe Isoard
 * 
 */
public abstract class Specific extends Material {

	/**
	 * Construct a specific material
	 * 
	 * @param tm
	 *            Integer for maximum time borrow allowed
	 */
	public Specific(int tm) {
		super(tm);
	}

}
