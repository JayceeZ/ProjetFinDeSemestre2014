package model;

/**
 * Enumeration des type d'appareils
 * @author Nabil ELMOUSSAID
 * 
 */
public enum Type {
    SMARTPHONE("Smartphone"), 
    TABLETTE("Tablette"), 
    CASQUE("Casque"), 
    CAMERA("Camera");
    
	private String description;
	private Type(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
