package projet.model.reservation;

import java.util.ArrayList;

import projet.model.date.Date;
import projet.model.material.Material;
import projet.model.state.State;

/**
 * Define a reservation
 * 
 * @author Maxime Dito, edited by Jean-Christophe Isoard
 * 
 */
public class Reservation {

	private ArrayList<Material> materials = new ArrayList<Material>();
	private Date dateStart;
	private Date dateEnd;
	private State state = State.Wait;

	/**
	 * Construct a reservation with one material
	 * 
	 * @param m
	 *            Material
	 */
	public Reservation(Material m) {
		this.materials.add(m);
		this.dateStart = new Date(); // current date
		this.setReservedMaterials();
		// so date end is automatically the longest possible
		this.dateEnd = calculateDateEnd();
	}

	/**
	 * Construct a reservation with a list of materials
	 * 
	 * @param m
	 *            ArrayList of materials
	 */
	public Reservation(ArrayList<Material> m) {
		this.materials.addAll(m);
		this.dateStart = new Date();
		this.setReservedMaterials();
		// so date end is automatically the longest possible
		this.dateEnd = calculateDateEnd();
	}

	/**
	 * Construct a reservation with a list of materials and a number of days
	 * from now
	 * 
	 * @param m
	 *            ArrayList of materials
	 * @param nbdays
	 *            Integer
	 */
	public Reservation(ArrayList<Material> m, int nbdays) {
		this.materials.addAll(m);
		this.dateStart = new Date(nbdays);
		this.setReservedMaterials();
		this.dateEnd = calculateDateEnd();
	}

	/**
	 * Construct a reservation with a list of materials and a number of days
	 * from now and a maximum time
	 * 
	 * @param m
	 *            ArrayList of materials
	 * @param nbdays
	 *            Integer
	 */
	public Reservation(ArrayList<Material> m, int nbdays, int maximumtime) {
		this.materials.addAll(m);
		this.dateStart = new Date(nbdays);
		this.setReservedMaterials();
		this.dateEnd = calculateDateEnd(maximumtime);
	}

	/**
	 * Return the maximal authorized end date
	 * 
	 * @param materials
	 *            ArrayList of the materials
	 * @return Date The end date
	 */
	public Date calculateDateEnd() {
		int total = 0;
		for (int i = 0; i < materials.size(); i++) {
			if (materials.get(i).getTimeMax() > total) {
				total = materials.get(i).getTimeMax();
			}
		}
		return new Date(total / materials.size() + 1);
	}

	/**
	 * Return the calculated end date based on the maximumtime given value
	 * 
	 * @param maximumtime
	 * @return Date The end date
	 */
	public Date calculateDateEnd(int maximumtime) {
		return new Date(maximumtime / materials.size() + 1);
	}

	/**
	 * Set all the material associated to the reservation to reserved
	 */
	public void setReservedMaterials() {
		for (int i = 0; i < materials.size(); i++) {
			materials.get(i).setReserved(true);
		}
	}

	@Override
	public String toString() {
		return "Start: " + dateStart + "\nEnd: " + dateEnd
				+ "\nObjet emprunté : \n" + materials + state;
	}

	/**
	 * Set the material list given to this reservation
	 * 
	 * @param m
	 *            ArrayList of materials
	 */
	public void setMaterials(ArrayList<Material> m) {
		this.materials = m;
	}

	/**
	 * Get the materials associated to this reservation
	 * 
	 * @return ArrayList The materials
	 */
	public ArrayList<Material> getMaterials() {
		return materials;
	}

	/**
	 * Change the material of the index i
	 * 
	 * @param m
	 *            The material to put here
	 * @param i
	 *            The index
	 */
	public void setMaterial(int i, Material m) {
		this.materials.set(i, m);
	}

	/**
	 * Get the material of the index i
	 * 
	 * @param i
	 *            The index
	 * @return Material The material at the index i
	 */
	public Material getMaterial(int i) {
		return materials.get(i);
	}

	/**
	 * Change the value of the reservation start date
	 * 
	 * @param date
	 *            Date
	 */
	public void setDateStart(Date date) {
		this.dateStart = date;
	}

	/**
	 * Get the start date
	 * 
	 * @return Date dateStart
	 */
	public Date getDateStart() {
		return this.dateStart;
	}

	/**
	 * Get the state (validate or not)
	 * 
	 * @return State (enum)
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Set the state (validate or not)
	 * 
	 * @param state
	 *            State (enum)
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Get the end date of the reservation
	 * 
	 * @return Date dateEnd
	 */
	public Date getDateEnd() {
		return this.dateEnd;
	}

	/**
	 * Set the end date of the reservation
	 * 
	 * @param dateEnd
	 */
	public void setDateEnd(Date enddate) {
		this.dateEnd = enddate;
	}

	/**
	 * Boolean: Reservation is validated or not
	 * 
	 * @return Boolean
	 */
	public boolean isWaiting() {
		if (this.state == State.Wait)
			return true;
		return false;
	}

	/**
	 * Delete the reservation and set back all the materials to unreserved
	 */
	public void finalize() {
		try {
			super.finalize();
			for (int i = 0; i < materials.size(); i++) {
				this.getMaterial(i).setReserved(false);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
