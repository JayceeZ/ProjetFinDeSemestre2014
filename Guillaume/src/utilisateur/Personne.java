package utilisateur;

/**
 * Cette classe permet de creer une personne. C'est une classe abstraite car on ne veut pas instancier d'objets de cette classe.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

abstract class Personne
{
	// L'identifiant de la personne.
	protected  String identifiant;
	// Le mot de passe de la personne.
	protected  String motDePasse;
	// Le nombre de materiels que la personne a emprunte.
	protected int nombreMaterielEmprunter;

	/**
	* Cree une Personne en specifiant un identifiant et un mot de passe.
	* @param identifiant L'identifiant de la personne.
	* @param motDePasse Le mot de passe de la personne.
	*/
	public Personne(String identifiant, String motDePasse)
	{
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		nombreMaterielEmprunter = 0;
	}

	/**
     * Cree une Personne avec des valeurs par defaut.
     */
	public Personne() 
	{
		this("Admin", "Admin");
	}

	/**
     * La methode getIdentifiant permet de renvoyer l'identifiant de la personne.
     * @return Un String indiquant l'identifiant de la personne.
     */
	public String getIdentifiant() 
	{
		return identifiant;
	}

	/**
     * La methode getMotDePasse permet de renvoyer le mot de passe de la personne.
     * @return Un String indiquant le mot de passe de la personne.
     */
	public String getMotDePasse() 
	{
		return motDePasse;
	}

	/**
     * La methode getNombreMaterielEmprunter permet de renvoyer le nombre de materiels que la personne a emprunte.
     * @return Un int indiquant le nombre de materiels que la personne a emprunte.
     */
	public int getNombreMaterielEmprunter()
	{
		return nombreMaterielEmprunter;
	}

	/**
     * La methode augmenterNombreMaterielEmprunter permet d'incrementer de 1 le nombre de materiels que la personne a emprunte.
     */
	public void augmenterNombreMaterielEmprunter()
	{
		nombreMaterielEmprunter = nombreMaterielEmprunter + 1;
	}

	/**
     * La methode toString permet de renvoyer la liste de materiels empruntes par la personne.
     * @return Un String indiquant la liste de materiels empruntes par la personne.
     */
	public String toString()
	{
		return "Nombre de materiels actuellement empruntes par " + identifiant + " : "+ nombreMaterielEmprunter;
	}
}
