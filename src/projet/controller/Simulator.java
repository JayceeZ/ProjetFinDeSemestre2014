package projet.controller;

import java.util.ArrayList;

import projet.model.material.Material;
import projet.model.material.classic.Camera;
import projet.model.material.classic.Headphone;
import projet.model.material.classic.operatingsystempowered.Phone;
import projet.model.material.classic.operatingsystempowered.Tablette;
import projet.model.os.OS;
import projet.model.reservation.Borrow;
import projet.model.reservation.Reservation;
import projet.model.user.User;
import projet.model.user.administrator.Administrator;
import projet.model.user.borrower.Borrower;
import projet.model.user.borrower.Student;
import projet.model.user.borrower.Teacher;
import projet.view.printer.Printer;

/**
 * Contains all the methods for running the app
 * 
 * @author Maxime Dito
 * 
 */

public class Simulator {

	private ArrayList<User> users;
	private ArrayList<Material> materials;
	private ReferenceGenerator referenceGenerator;

	private User cmd;
	private boolean exit = false;

	/**
	 * Construct the class
	 * 
	 * @param rg
	 *            The referencegenerator used in the main
	 * @param users
	 *            Users previously generated
	 * @param materials
	 *            Materials previously generated
	 */
	public Simulator(ReferenceGenerator rg, ArrayList<User> users,
			ArrayList<Material> materials) {
		this.referenceGenerator = rg;
		this.users = users;
		this.materials = materials;

	}

	/**
	 * Send back the users arraylist
	 * 
	 * @return Arraylist users
	 */
	public ArrayList<User> getUsers() {
		return this.users;
	}

	/**
	 * Send back the materials arraylist
	 * 
	 * @return ArrayList materials
	 */
	public ArrayList<Material> getMaterials() {
		return this.materials;
	}

	/**
	 * Send back the ieme user corresponding to the number
	 * 
	 * @param i
	 *            The index
	 * @return The user
	 */
	public User getUser(int i) {
		return this.users.get(i);
	}

	/**
	 * Send back the ieme material corresponding to the number
	 * 
	 * @param i
	 *            The index
	 * @return Obviously, the material corresponding
	 */
	public Material getMaterials(int i) {
		return this.materials.get(i);
	}

	/**
	 * Construct a reservation for the given borrower with the given materials
	 * 
	 * @param borrower
	 *            The borrower
	 * @param materials
	 *            The materials
	 */
	public void makeReservation(Borrower borrower, ArrayList<Material> materials) {
		if (this.allIsBorrowing(materials)) {
			Borrow borrow;
			if (borrower.getTimeLimit() >= 0) {
				borrow = new Borrow(materials, borrower.getTimeLimit());
			} else {
				borrow = new Borrow(materials);
			}
			borrower.addReservations(borrow);
		}
	}

	/**
	 * Construct a reservation for the given borrower with the given materials,
	 * the start date is today with start days more.
	 * 
	 * @param borrower
	 *            The borrower
	 * @param materials
	 *            The materials
	 * @param start
	 *            The number of days to add to the start date
	 */
	public void makeReservation(Borrower borrower,
			ArrayList<Material> materials, int start) {
		if (this.allIsBorrowing(materials)) {
			Reservation reservation;
			if (borrower.getTimeLimit() >= 0) {
				reservation = new Reservation(materials, start,
						borrower.getTimeLimit());
			} else {
				reservation = new Reservation(materials, start);
			}
			borrower.addReservations(reservation);
		}
	}

	/**
	 * Used to define an entire list of materials borrowed parameter to true
	 * 
	 * @param materials
	 *            An arraylist of Material
	 */
	public boolean allIsBorrowing(ArrayList<Material> materials) {
		for (int i = 0; i < materials.size(); i++) {
			if (materials.get(i).getReserved()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Create an user with firstname and lastname asked to the user, the type of
	 * user is define by the integer i
	 * 
	 * @param i
	 *            An integer (0=Admin, 1=Student, 2=Teacher)
	 */
	public boolean createUseUser(int i) {
		boolean admin = false;
		String firstname = Printer.giveMeStringUser("Firstname");
		String lastname = Printer.giveMeStringUser("Lastname");
		if (i == 0) {
			this.users.add(new Administrator(firstname, lastname));
			admin = true;
		} else if (i == 1) {
			this.users.add(new Student(firstname, lastname));
		} else {
			this.users.add(new Teacher(firstname, lastname));
		}
		this.referenceGenerator
				.createReference(this.getUser(this.users.size() - 1));
		this.cmd = this.getUser(this.users.size() - 1);
		return admin;
	}

	/**
	 * Get the all list of the non reserved Materials
	 * 
	 * @return Unreserved materials arraylist
	 */
	public ArrayList<Material> getNotReservedItem() {
		ArrayList<Material> notReserved = new ArrayList<Material>();
		for (int i = 0; i < this.materials.size(); i++) {
			if (!this.getMaterials(i).getReserved()) {
				notReserved.add(this.getMaterials(i));
			}
		}
		return notReserved;
	}

	/**
	 * Get the all list of waiting reservations
	 * 
	 * @return Waiting materials arraylist
	 */
	public ArrayList<Reservation> getWaitingReservations() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		ArrayList<User> onlyBorrower = this.getOnlyBorrower();
		for (int i = 0; i < onlyBorrower.size(); i++) {
			reservations.addAll(((Borrower) onlyBorrower.get(i))
					.getWaitingReservations());
		}
		return reservations;
	}

	/**
	 * Get all the reservations, waiting or not
	 * 
	 * @return Reservations arraylist
	 */
	public ArrayList<Reservation> getAllReservations() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		ArrayList<User> onlyBorrower = this.getOnlyBorrower();
		for (int i = 0; i < onlyBorrower.size(); i++) {
			reservations.addAll(((Borrower) onlyBorrower.get(i))
					.getReservations());
		}
		return reservations;
	}

	/**
	 * Display the list for user selection, user choose in the enumeration
	 * automatically generated.
	 * 
	 * @param littleText
	 *            A text to explain what user need to do
	 * @param list
	 *            An arraylist of objects for user choose
	 * @return Arraylist of objects selected by user
	 */
	public ArrayList<? extends Object> getListChooseIntoList(String littleText,
			ArrayList<? extends Object> list) {
		Printer.printMessage(littleText);
		int indexItem;
		ArrayList<Object> listChoosen = new ArrayList<Object>();
		if (!list.isEmpty()) {
			do {
				Printer.printAllList(list);
				indexItem = Printer.printMenuSpecial("Choose one of this :\n",
						list.size());
				if (indexItem != 0) {
					listChoosen.add(list.get(indexItem - 1));
					list.remove(indexItem - 1);
				}
			} while (indexItem != 0 && !list.isEmpty());
		} else {
			Printer.printMessage("Empty List");
		}
		return listChoosen;
	}

	/**
	 * Selector for making a reservation
	 */
	public void doSomethingBorrowerMakeReserv() {
		ArrayList<Material> listChoosen = (ArrayList<Material>) this
				.getListChooseIntoList("Make a Reservation/Borrow",
						this.getNotReservedItem());
		if (!listChoosen.isEmpty()) {
			doSomethingBorrowerMakeReservFinalize(listChoosen);
		}
	}

	/**
	 * Question asked after selector for time selection, then create reservation
	 */
	public void doSomethingBorrowerMakeReservFinalize(
			ArrayList<Material> listChoosen) {
		int nbDayDecay = Printer.printMenuSpecial("How many days from now ?",
				((Borrower) cmd).getTimeLimitReserved());
		if (nbDayDecay == 0) {
			this.makeReservation((Borrower) this.cmd, listChoosen);
		} else {
			this.makeReservation((Borrower) this.cmd, listChoosen, nbDayDecay);
		}
	}

	/**
	 * Get the list of the borrow able users. (ie: Teachers and Students)
	 */
	public ArrayList<User> getOnlyBorrower() {
		ArrayList<User> onlyBorrower = new ArrayList<User>();
		for (int i = 0; i < this.users.size(); i++) {
			if (this.getUser(i) instanceof Borrower) {
				onlyBorrower.add(this.getUser(i));
			}
		}
		return onlyBorrower;
	}

	/**
	 * List the reservations of the current logged user
	 */
	public void doSomethingBorrowerSeeReserv() {
		Printer.printMessage("My Reservations/Borrow",
				((Borrower) this.cmd).toStringReservations());
	}

	/**
	 * Menu of a borrower
	 */
	public boolean doSomethingBorrower(int i) {
		if (i == 0) {
			this.doSomethingBorrowerSeeReserv();
		} else if (i == 1) {
			this.doSomethingBorrowerMakeReserv();
		} else if (i == 2) {
			return this.changeUser();
		} else {
			this.exit = true;
		}
		return false;
	}

	/**
	 * Selector for changing a reservation
	 */
	public void doSomethingAdminChangeReserv() {
		ArrayList<Reservation> listChoosen = (ArrayList<Reservation>) this
				.getListChooseIntoList("Change Reservation/Borrow",
						this.getWaitingReservations());
		if (!listChoosen.isEmpty()) {
			doSomethingAdminChangeReservFinalize(listChoosen);
		}
	}

	/**
	 * Question asked after change reservation selector for validate the
	 * changings in reservation
	 */
	public void doSomethingAdminChangeReservFinalize(
			ArrayList<Reservation> listChoosen) {
		for (int i = 0; i < listChoosen.size(); i++) {
			((Administrator) cmd).valide(listChoosen.get(i));
		}
	}

	/**
	 * Get the user who have own the reservation given
	 * 
	 * @param reservation
	 *            The reservation to search with
	 */
	public User whoHasThisReserv(Reservation reservation) {
		ArrayList<User> onlyBorrower = this.getOnlyBorrower();
		for (int i = 0; i < onlyBorrower.size(); i++) {
			if (((Borrower) onlyBorrower.get(i)).isMyReservation(reservation)) {
				return onlyBorrower.get(i);
			}
		}
		return null;
	}

	/**
	 * Delete the reservation of the list given
	 * 
	 * @param listChoosen
	 *            ArrayList of reservations to delete
	 */
	public void doSomethingAdminDeleteReservFinalize(
			ArrayList<Reservation> listChoosen) {
		for (int i = 0; i < listChoosen.size(); i++) {
			((Administrator) cmd).delete(listChoosen.get(i));
			((Borrower) this.whoHasThisReserv(listChoosen.get(i)))
					.subReservations(listChoosen.get(i));
		}
	}

	/**
	 * Selector for reservation deletion
	 */
	public void doSometingAdminDeleteReserv() {
		ArrayList<Reservation> listChoosen = (ArrayList<Reservation>) this
				.getListChooseIntoList("Delete Reservation/Borrow",
						this.getAllReservations());
		if (!listChoosen.isEmpty()) {
			doSomethingAdminDeleteReservFinalize(listChoosen);
		}
	}

	/**
	 * Menu for the Administrator users
	 */
	public boolean doSomethingAdmin(int i) {
		if (i == 0) {
			this.doSomethingAdminChangeReserv();
		} else if (i == 1) {
			this.doSometingAdminDeleteReserv();
		} else if (i == 2) {
			Printer.printAllList(this.materials);
		} else if (i == 3) {
			Printer.printAllList(this.users);
		} else if (i == 4) {
			return this.changeUser();
		} else {
			this.exit = true;
		}
		return true;
	}

	/**
	 * Get the user with the id given, return null if nothing found
	 * 
	 * @param id
	 *            An existing id is prefered
	 * @return User, null if not found
	 */
	public User getUserById(String id) {
		for (int i = 0; i < this.users.size(); i++) {
			if (this.getUser(i).getId().equals(id)) {
				return this.getUser(i);
			}
		}
		return null;
	}

	/**
	 * For user selection
	 */
	public boolean changeUser() {
		Printer.printMessage("Enter the ID of an user:");
		User newUser = this.getUserById(Printer.giveMeStringUser("ID"));
		if (newUser != null) {
			cmd = newUser;
			return (cmd instanceof Administrator);
		} else {
			Printer.printMessage("User not found, create one :");
			return this.createUseUser(Printer.printMenuUser());
		}
	}

	/**
	 * Start point of the application
	 */
	public void Simulate() {
		boolean isAdmin = this.createUseUser(Printer.printMenuUser());
		while (!this.exit) {
			if (!isAdmin) {
				isAdmin = this.doSomethingBorrower(Printer
						.printMenuBorrower(cmd));
			} else {
				isAdmin = this.doSomethingAdmin(Printer.printMenuAdmin(cmd));
			}
		}
	}

}
