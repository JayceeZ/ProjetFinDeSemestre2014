package model;

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
    public Gestionnaire(String n, String motDePasse, int duree, int nbMaxMateriel,
            Enseignement[] m) {
        super(n, motDePasse, duree, nbMaxMateriel, m);
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
    	int nbMaxAppareils = emprunteur.getNbMaxMateriel();
    	Enseignement[] matieres = emprunteur.getMatieres();
    	Gestionnaire gestionnaire = new Gestionnaire(nom, motDePasse, dureeMaxEmprunt, nbMaxAppareils,matieres);
    	
    	database.getEmprunteurs().remove(emprunteur);
    	database.getEmprunteurs().add(gestionnaire);
    }
}
