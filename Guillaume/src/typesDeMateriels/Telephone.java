package typesDeMateriels;

/**
 * Cette classe permet de creer un telephone. Cette classe herite de la classe TypeDeMateriel.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Telephone extends TypeDeMateriel
{
	/**
     * Cree un type de materiel Telephone avec une liste de materiels vide.
     */
    public Telephone ()
    {
       	super("Telephone");
    }

    /**
     * La methode ajouterMateriel permet d'ajouter un Materiel a la liste du TypeDeMateriel Telephone et d'incrementer de 1 le nombre de materiels que contient Telephone.
     */
    public void ajouterMateriel()
    {
    	super.ajouterMateriel("Telephone");
    }
	
}