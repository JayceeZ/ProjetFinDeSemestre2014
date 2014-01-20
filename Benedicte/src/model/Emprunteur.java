package model;

/**
 * Super classe representant les emprunteurs en general.
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public abstract class Emprunteur {

    // Nom de l'emprunteur
    private String nom;

    // Duree max d'un emprunt
    private int dureeMaxEmprunt;

    // Id de l'emprunteur
    private int id;

    // Nombre max d'appareils a emprunter
    private int nbMaxAppareils;

    // Liste des enseignement de l'emprunteur
    private Enseignement[] matieres;

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
     * @param duree
     *            Duree max d'un emprunt
     * @param id
     *            Id de l'emprunteur
     * @param nbMaxAppareils
     *            Nombre max d'appareils a emprunter
     * @param m
     *            Liste des enseignement
     */
    public Emprunteur(String n, int duree, int id, int nbMaxAppareils,
            Enseignement[] m) {
        // Initialisation du nom
        this.nom = n;

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
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
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
    public Enseignement[] getMatieres() {
        return matieres;
    }

    /**
     * @param matieres
     *            the matieres to set
     */
    public void setMatieres(Enseignement[] matieres) {
        this.matieres = matieres;
    }
    
    @Override
    public String toString(){
        return nom;
    }
}
