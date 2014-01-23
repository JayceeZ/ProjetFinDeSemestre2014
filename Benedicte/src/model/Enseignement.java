package model;

/**
 * Enumeration de la liste des enseignement
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public enum Enseignement {
    SI("SI"), ELEC("ELEC"), GE("GE"), GB("GB"), MAM("MAM"), BAT("BAT");
    
    private String nom;
    private Enseignement(String n) {
    	this.nom = n;
    }
    
    
    public String toString() {
    	return nom;
    }
    
    public static boolean verifieEnum(String s) {
    	if(getEnum(s) != null) {
    		return true;
    	}
    	return false;
    }
    
    public static Enseignement getEnum(String s) {
    	for (Enseignement e : values()) {
    		if (e.toString().equals(s)) {
    			return e;
    		}
    	}
    	return null;
    }
}
