package utilisateur;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Cette classe permet de creer une liste de Personne. Le fait d'utiliser un HashSet permet que les personnes soient uniques.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Utilisateurs
{
	// La liste des personnes.
	private ArrayList<Personne> listePersonnes;

	/**
	* Cree un objet Utilisateurs qui n'est rien d'autres qu'une liste de Personne. Cette liste ne contient que l'administrateur.
	*/
	public Utilisateurs()
	{
		listePersonnes = new ArrayList<Personne>();
		listePersonnes.add(new Administrateur ());
	}

	/**
	* La methode transformerGestionnaire permet de transformer un emprunteur en un gestionnaire. On rappelle qu'un gestionnaire est un emprunteur qui est lui meme une personne.
	* @param identifiant Le nom d'utilisateur du gestionnaire.
	*/
	public void transformerGestionnaire (String identifiant)
	{
		boolean booleanTMP = false;
		int i = 0;
		Personne emprunteur = listePersonnes.get(0);
		// La boucle while permet de mettre booleanTMP a true s'il existe un utilisateur avec cet identifiant, sinon c'est que l'utilisateur n'existe pas.
		while (i < listePersonnes.size() && !booleanTMP)
		{
			emprunteur = listePersonnes.get(i);
			if (emprunteur.getIdentifiant().equals(identifiant))
			{
				booleanTMP = true;
			}
			else
			{
				i = i + 1;
			}
		}
		// Si l'utilisateur avec cet identifiant existe
		if (booleanTMP)
		{
			listePersonnes.remove(emprunteur);
			listePersonnes.add(new Gestionnaire (emprunteur.getIdentifiant(), emprunteur.getMotDePasse()));
			System.out.println("Votre compte Gestionnaire a bien ete cree et ajoute a notre base de donnee");
		}
		else
		{
			System.out.println("Cet emprunteur n'existe pas");
		}
	}

	/**
	* La methode ajouterEmprunteur permet d'ajouter un emprunteur a la liste. On rappelle qu'un emprunteur est une personne.
	* @param identifiant Le nom d'utilisateur de l'emprunteur.
	* @param motDePasse Le mot de passe de l'emprunteur.
	* @return Un boolean indiquant true si l'utilisateur a ete cree et false si il existait deja.
	*/
	public boolean ajouterEmprunteur (String identifiant, String motDePasse)
	{
		boolean booleanTMP = true;
		int i = 0;
		// La boucle while permet de mettre booleanTMP a false s'il existe deja un utilisateur avec cet identifiant, sinon c'est que l'on peut creer un utilisateur.
		while (i < listePersonnes.size() && booleanTMP)
		{
			if (listePersonnes.get(i).getIdentifiant().equals(identifiant))
			{
				booleanTMP = false;
			}
			else
			{
				i = i + 1;
			}
		}
		// Si booleanTMP = true alors on ajoute l'emprunteur a la liste des personnes.
		if (booleanTMP)
		{
			listePersonnes.add(new Emprunteur (identifiant, motDePasse));
			System.out.println("Votre compte Emprunteur a bien ete cree et ajoute a notre base de donnee");
			booleanTMP = true;
		}
		else
		{
			System.out.println("Veuillez choisir un autre nom d'utilisateur");
		}
		return booleanTMP;
	}

	/**
	* La methode verifierIdentifiants permet de verifier qu'il y a bien une personne dans la liste qui possède l'identifiant et le mot de passe renseignes. Cette methode permet egalement de savoir a quel classe appartient la personne.
	* @param identifiant Le nom d'utilisateur de la personne.
	* @param motDePasse Le mot de passe de la personne.
	* @return Un int. Si l'entier renvoye est 0 alors les identifiants sont incorrects, si c'est 1 alors c'est un emprunteur, si c'est 2 alors c'est un gestionnaire, si c'est 3 c'est un administrateur.
	*/
	public int verifierIdentifiants (String identifiant, String motDePasse)
	{
		boolean booleanIdentifiants = false;
		int typePersonne = 0;
		Iterator<Personne> it = listePersonnes.iterator();
		while (!booleanIdentifiants && it.hasNext())
		{
			Personne personneSuivante = it.next();
			if (personneSuivante.getIdentifiant().equals(identifiant) && personneSuivante.getMotDePasse().equals(motDePasse))
			{
				booleanIdentifiants = true;
				switch((""+ personneSuivante.getClass()).substring(18)) // Il faut transformer cela en string car on ne peut faire un switch sur des classes.
				{
					case "Emprunteur":
					{
						typePersonne = 1;
						break;
					}
					case "Gestionnaire":
					{
						typePersonne = 2;
						break;
					}
					case "Administrateur":
					{
						typePersonne = 3;
						break;
					}
					default:
					{
						typePersonne = 0;
						System.out.println("Erreur sur le fonctionnement de la fonction avec le polymorphisme");
					}
				}
			}
		}
		return typePersonne;
	}

	/**
     * La methode lePlusGrosEmprunteur permet de savoir quel est le plus gros emprunteur.
     * @return Une Personne qui est le plus gros emprunteur.
     */
	public Personne lePlusGrosEmprunteur()
	{
		Personne lePlusGrosEmprunteur = listePersonnes.get(0);
		for (int i=1; i<listePersonnes.size(); i=i+1)
		{
			if (listePersonnes.get(i).getNombreMaterielEmprunter() >= lePlusGrosEmprunteur.getNombreMaterielEmprunter())
			{
				lePlusGrosEmprunteur = listePersonnes.get(i);
			}
		}
		return lePlusGrosEmprunteur;
	}

	/**
	* La methode vide permet de savoir si la liste de personnes est vide ou pas.
	* @return Un boolean indiquant si la lise est vide ou non.
	*/
	public boolean vide ()
	{
		if (listePersonnes.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	* La methode afficherEmprunteurs permet d'afficher les personnes qui appartiennent a la classe Emprunteur.
	*/
	public void afficherEmprunteurs()
	{
		String affichageTMP = "[";
		Iterator<Personne> it = listePersonnes.iterator();
		while (it.hasNext())
		{
			Personne personneSuivante = it.next();
			if (((personneSuivante.getClass() + "")).substring(18).equals("Emprunteur"))
			{
				affichageTMP = affichageTMP + ", " + personneSuivante.getIdentifiant();
			}
		}
		affichageTMP = "[" + affichageTMP.substring(3) + "]";
		System.out.println(affichageTMP);
	}
	
	/**
     * La methode toString permet de renvoyer la liste des utilisateurs.
     * @return Un String affichant tous les utilisateurs avec leur materiels empruntes.
     */
	public String toString()
	{
		return listePersonnes + "";
	}
}