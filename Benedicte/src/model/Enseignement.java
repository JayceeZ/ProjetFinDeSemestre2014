package model;

/**
 * Enumeration de la liste des enseignement
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public enum Enseignement {
    SI("SI"), ELEC("ELEC"), GE("GE"), GB("GB"), MAM("MAM"), BAT("BAT");
    
    // Le nom de l'enseignement
    private String nom;
    
    /**
     * Le constructeur
     * @param n
     */
    private Enseignement(String n) {
    	this.nom = n;
    }
    
    /**
     * Permet de renvoyer le nom correspondant a l'enseignement.
     * @return Un String indiquant le nom correspondant a l'enseignement.
     */
    public String toString() {
    	return nom;
    }
    
    /**
     * Permet de verifier que la chaine de caractere entree correspond bien a un enseignement.
     * @param s La chaine de caractere qui doit etre verifiee.
     * @return Un boolean indiquant si la chaine de caractere est un enseignement ou pas.
     */
    public static boolean verifieEnum(String s) {
    	if(getEnum(s) != null) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Permet de renvoyer l'enseignement correspondant a la chaine de caractere entree.
     * @param s La chaine de caractere correspondant a l'enseignement desire.
     * @return Un Enseignement correspondant a la chaine de caractere entree.
     */
    public static Enseignement getEnum(String s) {
    	for (Enseignement e : values()) {
    		if (e.toString().equals(s)) {
    			return e;
    		}
    	}
    	return null;
    }
}
