package projet.view.printer;

import java.util.ArrayList;
import java.util.Scanner;

import projet.model.user.User;

/**
 * Displayer
 * 
 * @author Dito Maxime.
 * @version 1.0
 */

public class Printer {

	/**
	 * Display the complete list an ArrayList
	 * 
	 * @param list
	 *            ArrayList to display
	 */
	public static void printAllList(ArrayList<? extends Object> list) {
		if (!list.isEmpty()) {
			String affichage = "--- List ---\n\n";
			for (int i = 0; i < list.size(); i++) {
				affichage += list.get(i).getClass().getSimpleName() + " N°"
						+ (i + 1) + "\n";
				affichage += list.get(i).toString() + "\n";
			}
			affichage += "--- End List ---\n";
			System.out.println(affichage);
		} else {
			System.out.println("--- Empty List ---\n");
		}
	}

	/**
	 * Display a message for the user followed by something else
	 * 
	 * @param text
	 *            A message to display
	 * @param affichage
	 *            Something else to display after
	 */
	public static void printMessage(String text, String affichage) {
		System.out.println("--- " + text + " ---\n\n");
		System.out.println(affichage + "\n");
	}

	/**
	 * Display a single message for the user
	 * 
	 * @param text
	 *            A message to display
	 */
	public static void printMessage(String text) {
		System.out.println("--- " + text + " ---\n\n");
	}

	/**
	 * Display the user selection menu
	 * 
	 * @return Integer What choice user made
	 */
	public static int printMenuUser() {
		String[] menuUsers = { "Administrator", "User - Student",
				"User - Teacher" };
		Menu menu = new Menu(menuUsers);
		return menu.menuWait("Choose the User you want to test :\n");
	}

	/**
	 * Display the borrower menu
	 * 
	 * @param user
	 *            The user currently connected
	 * @return Integer What choice user made
	 */
	public static int printMenuBorrower(User user) {
		String[] menuUsers = { "See my Reservations/Borrow",
				"Make a Reservation/Borrow", "Change User", "Exit" };
		Menu menu = new Menu(menuUsers);
		return menu.menuWait("User : " + user.getFirstname() + " "
				+ user.getLastname() + ", " + user.getId()
				+ "\n\nChoose what you want to do :\n");
	}

	/**
	 * Display the admin menu
	 * 
	 * @param user
	 *            The user currently connected
	 * @return Integer What choice user made
	 */
	public static int printMenuAdmin(User user) {
		String[] menuUsers = { "Accept waiting Reservations/Borrow",
				"Delete Reservations/Borrow", "See all Materials",
				"See all Users", "Change User", "Exit" };
		Menu menu = new Menu(menuUsers);
		return menu.menuWait("User : " + user.getFirstname() + " "
				+ user.getLastname() + ", " + user.getId()
				+ "\n\nChoose what you want to do :\n");
	}

	/**
	 * Ask for a particular user <b>integer</b> input value
	 * 
	 * @param littleText
	 *            The question
	 * @param arg
	 *            The number answer can't overflow
	 * @return Integer What choice user made
	 */
	public static int printMenuSpecial(String littleText, int arg) {
		Menu menu = new Menu();
		return menu.menuWaitOneMore(littleText, arg);
	}

	/**
	 * Ask for a particular user <b>string</b> input value
	 * 
	 * @param text
	 *            The question you ask
	 * @return String User response
	 */
	public static String giveMeStringUser(String text) {
		Scanner sc;
		String stringUser = "";
		while (stringUser.length() < 3) {
			sc = new Scanner(System.in);
			System.out.println("Please give \"" + text + "\" (min 3 char)");
			try {
				stringUser = sc.nextLine();
			} catch (Exception e) {
			}
			System.out.println();
		}
		return stringUser;
	}

}
