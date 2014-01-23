package projet.model.user.borrower;

import java.util.ArrayList;
import java.util.LinkedList;

import projet.model.reservation.Reservation;
import projet.model.user.User;

/**
 * Define a borrower user
 * 
 * @author Maxime Dito
 * 
 */
public abstract class Borrower extends User {

	private LinkedList<Reservation> reservations;

	/**
	 * Construct a borrow with firstname and lastname
	 * 
	 * @param firstname
	 * @param lastname
	 */
	public Borrower(String firstname, String lastname) {
		super(firstname, lastname);
		this.reservations = new LinkedList<Reservation>();
	}

	/**
	 * Getter for getting all the reservations
	 * 
	 * @return reservations LinkedList
	 */
	public LinkedList<Reservation> getReservations() {
		return this.reservations;
	}

	/**
	 * Getter for one reservation
	 * 
	 * @param i
	 *            Index of the reservation
	 * @return reservation Reservation
	 */
	public Reservation getReservations(int i) {
		return this.reservations.get(i);
	}

	/**
	 * Setter for change a reservation to the LinkedList at a choosen index
	 * 
	 * @param i
	 *            Index where to change
	 * @param reservation
	 *            The reservation to add
	 */
	public void setReservations(int i, Reservation reservation) {
		this.reservations.set(i, reservation);
	}

	/**
	 * Add the reservation given parameter at the end of the linked list
	 * 
	 * @param reservation
	 *            The reservation to add
	 */
	public void addReservations(Reservation reservation) {
		this.reservations.add(reservation);
	}

	/**
	 * Delete the reservation of the index i and remove the index
	 * 
	 * @param i
	 */
	public void subReservations(int i) {
		this.reservations.get(i).finalize();
		this.reservations.remove(i);
	}

	/**
	 * Remove in the list all the occurrences of the reservation given
	 * 
	 * @param reservation
	 *            The reservation to remove
	 */
	public void subReservations(Reservation reservation) {
		for (int i = 0; i < this.reservations.size(); i++) {
			if (this.getReservations(i).equals(reservation)) {
				this.reservations.remove(i);
			}
		}
	}

	@Override
	public String toString() {
		return super.toString() + toStringReservations();
	}

	/**
	 * Return for display only the user's reservations
	 * 
	 * @return String reservations of the user
	 */
	public String toStringReservations() {
		String reservations = "";
		for (int i = 0; i < this.reservations.size(); i++) {
			reservations += this.getReservations(i).toString() + "\n";
		}
		return reservations + "\n";
	}

	/**
	 * Return for display only non validated user's reservations
	 * 
	 * @return
	 */
	public ArrayList<Reservation> getWaitingReservations() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		for (int i = 0; i < this.reservations.size(); i++) {
			if (this.getReservations(i).isWaiting()) {
				reservations.add(this.getReservations(i));
			}
		}
		return reservations;
	}

	/**
	 * Test if the given reservation is from this user
	 * 
	 * @param reservation
	 * @return Boolean
	 */
	public boolean isMyReservation(Reservation reservation) {
		for (int i = 0; i < this.getReservations().size(); i++) {
			if (this.getReservations(i).equals(reservation)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the value of timeLimitReserved defined in the class
	 * 
	 * @return Integer
	 */
	public int getTimeLimitReserved() {
		try {
			return (int) this.getClass().getField("timeLimitReserved")
					.getInt((Object) this);
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}

	/**
	 * Return the value of timeLimitReserved defined in the class
	 * 
	 * @return Integer
	 */
	public int getTimeLimit() {
		try {
			return (int) this.getClass().getField("timeLimit")
					.getInt((Object) this);
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}

}
