/**
 * Define a material
 * 
 * @author: Isoard Jean-Christophe
 * @version: 
 */

package projet.model.material;

public abstract class Material {

	private int timeMax;
	private boolean reserved;
	private String reference;
	private int nbBorrowed = 0;

	/**
	 * Construct a Material
	 * 
	 * @param tm
	 *            Maximum time we can borrow an object
	 */
	public Material(int tm) {
		this.timeMax = tm;
		this.reserved = false;
	}

	@Override
	public String toString() {
		return "Name : " + this.getClass().getSimpleName() + "\nReference : "
				+ reference + "\nTemps max : " + timeMax + "\nReserved : "
				+ reserved + "\nNbBorrowed :" + nbBorrowed + "\n";
	}

	/**
	 * Return the value of timemax
	 * 
	 * @return timemax
	 */
	public int getTimeMax() {
		return timeMax;
	}

	/**
	 * Return the value of the boolean reserved
	 * 
	 * @return Boolean reserved
	 */
	public boolean getReserved() {
		return this.reserved;
	}

	/**
	 * Return the quantity of borrowed materials
	 * 
	 * @return Integer The value
	 */
	public int getNbBorrowed() {
		return this.nbBorrowed;
	}

	/**
	 * Return the reference
	 * 
	 * @return String reference
	 */
	public String getReference() {
		return this.reference;
	}

	/**
	 * Set the value of nbBorrowed
	 * 
	 * @param i
	 */
	public void setNbBorrowed(int i) {
		this.nbBorrowed = i;
	}

	/**
	 * To change the reservation state
	 * 
	 * @param Boolean
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	/**
	 * Set the value of the maximum borrowing time
	 * 
	 * @param timeMax
	 *            the interger choosen
	 */
	public void setTimeMax(int timeMax) {
		this.timeMax = timeMax;
	}

	/**
	 * Set the reference
	 * 
	 * @param reference
	 *            The string to setup
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Increment the number of borrows
	 */
	public void incNbBorrow() {
		this.nbBorrowed++;
	}

}
