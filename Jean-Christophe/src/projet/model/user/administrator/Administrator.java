package projet.model.user.administrator;

import projet.model.reservation.Reservation;
import projet.model.state.State;
import projet.model.user.User;

/**
 * Define an administrator
 * 
 * @author Jean-Christophe Isoard
 * 
 */
public class Administrator extends User {

	/**
	 * Construct an administrator
	 * 
	 * @param firstname
	 * @param lastname
	 */
	public Administrator(String firstname, String lastname) {
		super(firstname, lastname);
	}

	/**
	 * Validate a reservation
	 * 
	 * @param reservation
	 *            Reservation
	 */
	public void valide(Reservation reservation) {
		reservation.setState(State.Accept);
		for (int i = 0; i < reservation.getMaterials().size(); i++) {
			reservation.getMaterial(i).incNbBorrow();
		}
	}

	/**
	 * Delete a reservation
	 * 
	 * @param reservation
	 *            Reservation
	 */
	public void delete(Reservation reservation) {
		reservation.finalize();
	}

}
