package projet.view.printer;

import java.util.Scanner;

/**
 * Used to display a user interface String only obviously.
 * 
 * @author Maxime Dito
 */

public class Menu {
	private String[] arg;

	public Menu() {
	}

	/**
	 * Create a menu, for this app use only.
	 * 
	 * @param menu
	 *            The item list
	 */
	public Menu(String[] menu) {
		for (int i = 0; i < menu.length; i++)
			this.arg = menu;
	}

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < this.arg.length; i++) {
			string += i + " : " + this.arg[i]
					+ System.getProperty("line.separator");
		}
		return string;
	}

	/**
	 * Send back the string corresponding to the argument choosen
	 * 
	 * @param i
	 *            Integer
	 * @return The string which correspond
	 */
	public String getArg(int i) {
		return this.arg[i];
	}

	/**
	 * Display a menu with integer choices and wait for a user response.
	 * 
	 * @param littleText
	 *            The text header of the menu
	 * @return The integer choosen by the user
	 */
	public int menuWait(String littleText) {
		Scanner sc;
		int val = -1;
		while (val < 0 || val >= (Integer) this.arg.length) {
			System.out.println(littleText);
			sc = new Scanner(System.in);
			System.out.println(this.toString());
			System.out.println("Press a key value between 0 and "
					+ ((Integer) this.arg.length - 1) + ".");
			try {
				val = sc.nextInt();
			} catch (Exception e) {
				System.out.println("An integer is required !");
			}
			System.out.println();
		}
		return val;
	}

	/**
	 * Mini menu for other chooses
	 * 
	 * @param littleText
	 *            The text header of the menu
	 * @param arg
	 *            The number of possibility of choice
	 * @return The integer choosen by the user
	 */
	public int menuWaitOneMore(String littleText, int arg) {
		Scanner sc;
		int val = -1;
		while (val < 0 || val > arg) {
			System.out.println(littleText);
			sc = new Scanner(System.in);
			System.out.println("Press a key value between 0 and " + arg + ".");
			try {
				val = sc.nextInt();
			} catch (Exception e) {
				System.out.println("An integer is required !");
			}
			System.out.println();
		}
		return val;
	}

}