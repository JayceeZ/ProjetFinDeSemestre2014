/**
 * @author: Isoard Jean-Christophe
 */

package projet.model.material.classic;

import projet.model.material.Material;

/**
 * Define a classic material
 * 
 * @author Jean-Christophe Isoard
 * 
 */

public abstract class Classic extends Material {

	/**
	 * Construct a classic material with timeMax given
	 * 
	 * @param tm
	 *            Integer
	 */
	public Classic(int tm) {
		super(tm);
	}

}
