package model;

/**
 * Liste des os pour un appareil
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public enum OS {
	ANDROID("Android"), IOS("IOS"), NOOS("Aucun");
	 
	
	private String description;
	private OS(String d) {
		this.description = d;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
   
}
