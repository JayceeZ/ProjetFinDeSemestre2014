package model;

import java.util.ArrayList;

/**
 * Modelise le gestionnaire de l'application
 * @author Nabil ELMOUSSAID
 *
 */
public class Gestionnaire extends Emprunteur {

     /**
     * Premier constructeur d'un objet gestionnaire
     * 
     * @param n
     *            Nom de l'emprunteur
     * @param motDePasse
     * 			  Mot de passe de l'emprunteur
     * @param duree
     *            Duree max d'un emprunt
     * @param id
     *            Id de l'emprunteur
     * @param nbMaxAppareils
     *            Nombre max d'appareils a emprunter
     * @param m
     *            Liste des enseignement
     */
    public Gestionnaire(String n, String motDePasse, int duree, String id, int nbMaxMateriel,
    		ArrayList<Enseignement> m) {
        super(n, motDePasse, duree, id, nbMaxMateriel, m);
    }

    /**
     * Permet au gestionnaire de nommer gestionnaire un enseignant ou un etudiant . 
     * L'emprunteur est alors remplace dans la databse par un gestionnaire ayant les memes
     * parametres que lui .
     * @param emprunteur
     * 			L'emprunteur a promouvoir
     * @param database
     * 			La database 
     */
    public void creerGestionnaire(Emprunteur emprunteur,Database database){
    	if(emprunteur instanceof Gestionnaire)
    		return;
    	String nom = emprunteur.getNom();
    	String motDePasse = emprunteur.getMotDePasse();
    	int dureeMaxEmprunt = emprunteur.getDureeMaxEmprunt();
    	String id = emprunteur.getId();
    	int nbMaxAppareils = emprunteur.getNbMaxMateriel();
    	ArrayList<Enseignement> matieres = emprunteur.getMatieres();
    	Gestionnaire gestionnaire = new Gestionnaire(nom, motDePasse, dureeMaxEmprunt, id, nbMaxAppareils,matieres);
    	
    	database.getEmprunteurs().remove(emprunteur);
    	database.getEmprunteurs().add(gestionnaire);
    }
}
