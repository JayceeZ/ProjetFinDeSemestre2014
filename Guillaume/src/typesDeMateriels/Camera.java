package typesDeMateriels;

/**
 * Cette classe permet de creer une camera. Cette classe herite de la classe TypeDeMateriel.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Camera extends TypeDeMateriel
{
	/**
     * Cree un type de materiel Camera avec une liste de materiels vide.
     */
    public Camera ()
    {
       	super("Camera");
    }

    /**
     * La methode ajouterMateriel permet d'ajouter un Materiel a la liste du TypeDeMateriel Camera et d'incrementer de 1 le nombre de materiels que contient Camera.
     */
    public void ajouterMateriel()
    {
    	super.ajouterMateriel("Camera");
    }
	
}