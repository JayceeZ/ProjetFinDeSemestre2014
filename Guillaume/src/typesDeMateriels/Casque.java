package typesDeMateriels;

/**
 * Cette classe permet de creer un casque. Cette classe herite de la classe TypeDeMateriel.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Casque extends TypeDeMateriel
{
	/**
     * Cree un type de materiel Casque avec une liste de materiels vide.
     */
    public Casque ()
    {
       	super("Casque");
    }

    /**
     * La methode ajouterMateriel permet d'ajouter un Materiel a la liste du TypeDeMateriel Casque et d'incrementer de 1 le nombre de materiels que contient Casque.
     */
    public void ajouterMateriel()
    {
    	super.ajouterMateriel("Casque");
    }
	
}