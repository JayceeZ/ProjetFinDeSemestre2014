package projet.controller;

import java.util.ArrayList;

import projet.model.material.Material;
import projet.model.material.classic.Camera;
import projet.model.material.classic.Headphone;
import projet.model.material.classic.operatingsystempowered.Phone;
import projet.model.material.classic.operatingsystempowered.Tablette;
import projet.model.os.OS;
import projet.model.user.User;
import projet.model.user.administrator.Administrator;
import projet.model.user.borrower.Student;
import projet.model.user.borrower.Teacher;

/**
 * Class containing the main method
 * 
 * @author Isoard Jean-Christophe
 * 
 */

public class StockGenerator {

	public static void main(String[] args) {
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Material> materials = new ArrayList<Material>();

		for (int i = 0; i < 7; i++)
			materials.add(new Camera());
		for (int i = 0; i < 7; i++)
			materials.add(new Headphone());
		for (int i = 0; i < 4; i++)
			materials.add(new Phone(OS.Android));
		for (int i = 0; i < 3; i++)
			materials.add(new Phone(OS.IOS));
		for (int i = 0; i < 3; i++)
			materials.add(new Tablette(OS.IOS));
		for (int i = 0; i < 4; i++)
			materials.add(new Tablette(OS.Android));
		for (int i = 0; i < 7; i++)
			users.add(new Student("Toto", "Flock"));
		for (int i = 0; i < 7; i++)
			users.add(new Teacher("Tata", "Flack"));
		users.add(new Administrator("Admin", "admin"));

		ReferenceGenerator rg = new ReferenceGenerator();

		initiateReference(rg, users, materials);

		(new Simulator(rg, users, materials)).Simulate();
	}

	public static void initiateReference(ReferenceGenerator rg,
			ArrayList<User> users, ArrayList<Material> materials) {
		for (int i = 0; i < materials.size(); i++) {
			rg.createReference(materials.get(i));
		}
		for (int i = 0; i < users.size(); i++) {
			rg.createReference(users.get(i));
		}

	}
}
