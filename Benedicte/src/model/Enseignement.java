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
    
    public boolean verifieEnum(String s) 
    {
    	for (Enseignement e : Enseignement.values)
    	{
    		if (s.equals(e))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public Enum<Enseignement> getEnum(String s) 
    {
    	for (Enseignement e : Enseignement.values)
    	{
    		if (s.equals(e))
    		{
    			return e;
    		}
    	}
    	
    	return null;
    }
}
