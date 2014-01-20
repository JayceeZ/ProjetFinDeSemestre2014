package projet.controller;

import java.util.HashMap;

import projet.model.material.Material;
import projet.model.user.User;

/**
 * This class is used to generate some references
 * 
 * @author Jean-Christophe Isoard
 * 
 */
public class ReferenceGenerator {
	private HashMap<Class, Integer> classs;

	/**
	 * Construct a ReferenceGenerator, initialise the hashmap for storage
	 */
	public ReferenceGenerator() {
		classs = new HashMap<Class, Integer>();
	}

	/**
	 * Getter for the Hashmap, empty on first instance of ReferenceGenerator
	 * Class represent the class and Interger the number of time it was accessed
	 * 
	 * @return Hashmap of the ReferenceGenerator
	 */
	public HashMap<Class, Integer> getClasss() {
		return this.classs;
	}

	/**
	 * That methode generate the references (identificators) for a material<br />
	 * If the material class isn't in the hashmap add it, add one to the integer
	 * corresponding on call
	 * 
	 * @param Material
	 *            The material for which we wan't to add a reference
	 */
	public void createReference(Material material) {
		Class classe = material.getClass();
		// add the class to the hashmap if isn't already
		if (!this.getClasss().containsKey(classe)) {
			this.getClasss().put(classe, 0);
		}
		// increment the number of access of one
		this.getClasss().put(classe, this.getClasss().get(classe) + 1);
		material.setReference((classe.getSimpleName().substring(0, 1) + this
				.getClasss().get(classe)).toLowerCase());
	}

	/**
	 * That methode generate the references (identificators) for a user<br />
	 * If the user class isn't in the hashmap add it, add one to the integer
	 * corresponding on call
	 * 
	 * @param User
	 *            The user for which we wan't to add a reference
	 */
	public void createReference(User user) {
		Class classe = user.getClass();
		if (!this.getClasss().containsKey(classe)) {
			this.getClasss().put(classe, 0);
		}
		this.getClasss().put(classe, this.getClasss().get(classe) + 1);
		user.setId((user.getFirstname().substring(0, 1)
				+ user.getLastname().substring(0, 1)
				+ classe.getSimpleName().substring(0, 1) + this.getClasss()
				.get(classe)).toLowerCase());
	}
}
