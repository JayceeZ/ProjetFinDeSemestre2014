package typesDeMateriels;

/**
 * Cette classe permet de creer une tablette. Cette classe herite de la classe TypeDeMateriel.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Tablette extends TypeDeMateriel
{
	/**
     * Cree un type de materiel Tablette avec une liste de materiels vide.
     */
    public Tablette ()
    {
       	super("Tablette");
    }

    /**
     * La methode ajouterMateriel permet d'ajouter un Materiel a la liste du TypeDeMateriel Tablette et d'incrementer de 1 le nombre de materiels que contient Tablette.
     */
    public void ajouterMateriel()
    {
    	super.ajouterMateriel("Tablette");
    }
	
}