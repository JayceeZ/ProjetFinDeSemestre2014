package projet.model.user;

/**
 * Define an User
 * 
 * @author Jean-Christophe Isoard
 * 
 */
public abstract class User {

	private String id;
	private String firstname;
	private String lastname;

	/**
	 * Construct an User
	 * 
	 * @param firstname
	 * @param lastname
	 */
	public User(String firstname, String lastname) {
		this.id = "";
		this.firstname = firstname;
		this.lastname = lastname;
	}

	/**
	 * Getter for id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for firstname
	 * 
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Getter for lastname
	 * 
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Setter for firstname
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Setter for id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Setter for lastname
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Id : " + this.id + "\n" + this.getClass().getSimpleName()
				+ "\nFirstname : " + this.firstname + "\nLastName : "
				+ this.lastname + "\n";
	}

}
