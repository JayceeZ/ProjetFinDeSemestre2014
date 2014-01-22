package model;

/**
 * Classe representant un etudiant, derivant d'un emprunteur.
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public class Etudiant extends Emprunteur {

    /**
     * Constructeur par defaut
     */
    public Etudiant() {
        super();
    }

    /**
     * Premier constructeur d'un objet etudiant
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
    public Etudiant(String n, String motDePasse,String id, Enseignement[] m) {
        super(n, motDePasse, 20, id, 3, m);
    }

}
