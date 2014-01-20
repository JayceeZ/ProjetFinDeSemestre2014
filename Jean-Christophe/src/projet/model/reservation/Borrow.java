/**
 * Define a borrow
 * @author Isoard Jean-Christophe
 */

package projet.model.reservation;

import java.util.ArrayList;

import projet.model.material.Material;

public class Borrow extends Reservation {

	/**
	 * Simplest constructor, max duration is choosed
	 * 
	 * @param m
	 *            A material to borrow
	 */
	public Borrow(Material m) {
		super(m);
	}

	/**
	 * Constructor for multiple materials in borrow, max duration is choosed
	 * 
	 * @param m
	 *            ArrayList of materials
	 */
	public Borrow(ArrayList<Material> m) {
		super(m);
	}

	/**
	 * Constructor with a list of materials and an integer for the duration
	 * 
	 * @param m
	 *            The materials list
	 * @param duration
	 *            The duration of the borrow
	 */
	public Borrow(ArrayList<Material> m, int duration) {
		super(m, duration);
	}

}
