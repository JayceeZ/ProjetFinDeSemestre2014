package materiels;

/**
 * Une classe permettant de gerer un Materiel.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Materiel 
{
    // Nom du Materiel
	protected String nom;

    /**
    * Cree un materiel en renseignant le nom de celui-ci.
    * @param nom Nom du materiel
    */
    public Materiel(String nom)
    {
    	this.nom = nom;
    }
    
    /**
    * Cree un materiel en renseignant de sparametres par defaut.
    */
    public Materiel()
    {
        this("test");
    }
    
    /**
    * La methode getNom permet de recuperer le nom du materiel.
    * @return String nom
    */
    public String getNom ()
    {
    	return nom;
    }

    /**
    * La methode toString permet d'afficher le nom du materiel.
    * @return Un String indiquant le nom du materiel.
    */
    public String toString() 
    {
    	return " Nom du Materiel : " + nom;
    }
}
