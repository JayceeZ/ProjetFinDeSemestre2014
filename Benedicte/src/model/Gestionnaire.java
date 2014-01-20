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
     * @param duree
     *            Duree max d'un emprunt
     * @param id
     *            Id de l'emprunteur
     * @param nbMaxAppareils
     *            Nombre max d'appareils a emprunter
     * @param m
     *            Liste des enseignement
     */
    public Gestionnaire(String n, int duree, int id, int nbMaxMateriel,
            Enseignement[] m) {
        super(n, duree, id, nbMaxMateriel, m);
    }

}
