package model;

import java.util.ArrayList;

/**
 * Classe representant un enseignant, derivant de la classe emprunteur.
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public class Enseignant extends Emprunteur {

    /**
     * Constructeur par defaut
     */
    public Enseignant() {
        super();
    }

    /**
     * Premier constructeur d'un objet enseignant
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
    public Enseignant(String n, String motDePasse, String id, ArrayList<Enseignement> m) 
    {
        super(n, motDePasse, 30, id, 24, m);
    }

}
