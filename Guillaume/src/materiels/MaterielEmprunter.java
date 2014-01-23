package materiels;

import java.util.Calendar;

/**
 * Une classe permettant de gerer un Materiel emprunte par une personne.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class MaterielEmprunter extends Materiel
{
	// Nom de la personne qui emprunte le materiel.
	private String nomEmprunteur;
	// Date a laquelle la personne emprunte le materiel.
	private Calendar dateEmprunt;
	// Duree pendant laquelle la personne emprunte le materiel
	private int dureeEmprunt;
	
	/**
	 * Cree un materiel emprunte en renseignant le nom du materiel, le nom de l'emprunteur, et la duree de l'emprunt.
	 * @param nom Nom du materiel.
	 * @param nomEmprunteur Le nom de la personne qui emprunte le materiel.
	 * @param dureeEmprunt La duree pendant laquelle la personne emprunte le materiel.
	 */
	public MaterielEmprunter (String nom, String nomEmprunteur, int dureeEmprunt)
	{
		super(nom);
		this.nomEmprunteur = nomEmprunteur;
		dateEmprunt = Calendar.getInstance();
		this.dureeEmprunt = dureeEmprunt;
	}
	
	/**
	 * Cree un materiel emprunte en renseignant des parametres par défaut.
	 */
	public MaterielEmprunter()
	{
		super();
		this.nomEmprunteur = "test";
		dateEmprunt = Calendar.getInstance();
		this.dureeEmprunt = 2;
	}
}
