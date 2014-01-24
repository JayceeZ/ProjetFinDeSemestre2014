package model;

import java.util.ArrayList;

/**
 * Super classe representant les emprunteurs en general.
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public abstract class Emprunteur {

    // Nom de l'emprunteur
    private String nom;
    
    // Mot de passe de l'emprunteur
    private String motDePasse;

    // Duree max d'un emprunt
    private int dureeMaxEmprunt;

    // Id de l'emprunteur
    private String id;

    // Nombre max d'appareils a emprunter
    private int nbMaxAppareils;

    // Liste des enseignement de l'emprunteur
    private ArrayList<Enseignement> matieres;

    /**
     * Constructeur par defaut
     */
    public Emprunteur() {

    }

    /**
     * Premier constructeur d'un objet emprunteur
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
    public Emprunteur(String n, String motDePasse, int duree, String id, int nbMaxAppareils,
            ArrayList<Enseignement> m) {
        // Initialisation du nom
        this.nom = n;
        
        // Initialisation du mot de passe
        this.motDePasse = motDePasse;

        // Initialisation de la duree max d'emprunt
        this.dureeMaxEmprunt = duree;

        // Initialisation de l'id
        this.id = id;

        // Initialisation du nombre max d'appareils
        this.nbMaxAppareils = nbMaxAppareils;

        // Initialisation de la liste des enseignements
        this.matieres = m;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * @return the nom
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * @param nom
     *            the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the dureeMaxEmprunt
     */
    public int getDureeMaxEmprunt() {
        return dureeMaxEmprunt;
    }

    /**
     * @param dureeMaxEmprunt
     *            the dureeMaxEmprunt to set
     */
    public void setDureeMaxEmprunt(int dureeMaxEmprunt) {
        this.dureeMaxEmprunt = dureeMaxEmprunt;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nbMaxMateriel
     */
    public int getNbMaxMateriel() {
        return nbMaxAppareils;
    }

    /**
     * @param nbMaxMateriel
     *            the nbMaxMateriel to set
     */
    public void setNbMaxMateriel(int nbMaxMateriel) {
        this.nbMaxAppareils = nbMaxMateriel;
    }

    /**
     * @return the matieres
     */
    public ArrayList<Enseignement> getMatieres() {
        return matieres;
    }

    /**
     * @param matieres
     *            the matieres to set
     */
    public void setMatieres(ArrayList<Enseignement> matieres) {
        this.matieres = matieres;
    }
    
    @Override
    public String toString(){
        return nom;
    }
}
