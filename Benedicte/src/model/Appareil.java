package model;

import java.util.Map;

/**
 * Classe modelisant un appareil/materiel de l'application. Un appareil est
 * compose par un id, un nom, un type, un os, une duree d'emprunt maximal et une
 * liste de matieres associe.
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public class Appareil {

    // Id de l'appareil
    private int id;

    // Reference de l'appareil
    private String reference;

    // Type de l'appareil
    private Type type;

    // OS de l'appareil
    private OS os;

    // Duree d'un emprunt maximal
    private int dureeEmpruntMax;

    // Liste des enseignement associes
    private Enseignement[] enseignementAssociee;

    /**
     * Constructeur par defaut
     */
    public Appareil() {

    }

    /**
     * Constructeur a partir d'un dictionnaire de configuration.
     * 
     * @param configuration
     *            Dictionnaire contenant la description de l'appareil
     */
    public Appareil(Map<String, Object> configuration) {
        // Initialisation de l'id
        this.id = (Integer) configuration.get("Id");

        // Initialisation de la reference de l'appareil
        this.reference = ((String) configuration.get("Nom"));

        // Initialisation du type de l'appareil
        this.type = (Type) configuration.get("Type");

        // Initialisation de l'os
        this.os = (OS) configuration.get("OS");

        // Initialisation de la duree maximal pour un emprunt
        this.dureeEmpruntMax = (Integer) configuration.get("DureeEmpruntMax");

        // Initialisation de la liste des enseignement associe
        this.enseignementAssociee = (Enseignement[]) configuration
                .get("Matieres");
    }

    /**
     * Constructeur d'un appareil.
     * 
     * @param reference
     *            reference de l'appareil
     * @param type
     *            type de l'appareil
     * @param os
     *            os de l'appareil
     * @param dureeEmpruntMax
     *            duree max pour un emprunt
     * @param matieresAssociee
     *            liste des matieres associe
     * @param id
     *            id de l'appareil
     */
    public Appareil(String reference, Type type, OS os, int dureeEmpruntMax,
            Enseignement[] matieresAssociee, int id) {
        this.reference = reference;
        this.type = type;
        this.os = os;
        this.dureeEmpruntMax = dureeEmpruntMax;
        this.enseignementAssociee = matieresAssociee;
        this.id = id;
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
     * @return the nom
     */
    public String getNom() {
        return reference;
    }

    /**
     * @param nom
     *            the nom to set
     */
    public void setNom(String nom) {
        this.reference = nom;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the os
     */
    public OS getOs() {
        return os;
    }

    /**
     * @param os
     *            the os to set
     */
    public void setOs(OS os) {
        this.os = os;
    }

    /**
     * @return the dureeEmpruntMax
     */
    public int getDureeEmpruntMax() {
        return dureeEmpruntMax;
    }

    /**
     * @param dureeEmpruntMax
     *            the dureeEmpruntMax to set
     */
    public void setDureeEmpruntMax(int dureeEmpruntMax) {
        this.dureeEmpruntMax = dureeEmpruntMax;
    }

    /**
     * @return the matieresAssociee
     */
    public Enseignement[] getMatieresAssociee() {
        return enseignementAssociee;
    }

    /**
     * @param matieresAssociee
     *            the matieresAssociee to set
     */
    public void setMatieresAssociee(Enseignement[] matieresAssociee) {
        this.enseignementAssociee = matieresAssociee;
    }

    @Override
    public String toString() {
        return id + "   | " + String.format("%-10s", type.name()) + " | " + String.format("%-20s", reference) + " | ";
    }
}
