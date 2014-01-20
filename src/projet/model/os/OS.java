package projet.model.os;

/**
 * Define OS enum
 * 
 * @author Maxime Dito
 * 
 */
public enum OS {

	// OS de base, dont on a besoin lors de l'execution.
	IOS("IOS", "7"), Android("Android", "KitKat");

	private String name;
	private String version;

	// Constructeur
	OS(String name, String version) {
		this.name = name;
		this.version = version;
	}

	/**
	 * Methode donnant le nom de l'OS.
	 * 
	 * @return Le nom de l'OS.
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Methode donnant le nom de version.
	 * 
	 * @return La version.
	 * 
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Change OS name
	 * 
	 * @param name
	 *            String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Change version
	 * 
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "OS : " + this.name + "\nVersion : " + this.version + "\n";
	}

}
