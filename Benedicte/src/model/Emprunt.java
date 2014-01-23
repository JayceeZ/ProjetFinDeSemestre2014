package model;

import java.util.HashMap;
import java.util.Calendar;

/**
 * Classe representant un emprunt. Un emprunt est compos� un dictionnaire lui
 * m�me contenant la liste des appareils et le nombre voulu, une date de debut,
 * de fin et l'emprunteur.
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
public class Emprunt {

    // Dictionnaire contenant les appareils empruntes ainsi que leurs nombres
    private HashMap<Appareil, Integer> listeAppareils;

    // Date de debut de l'emprunt
    private Calendar dateDebut;

    // Date de fin de l'emprunt
    private Calendar dateFin;

    // Reference sur l'emprunteur
    private Emprunteur emprunteur;
    
    // Id de l'emprunt
    private int id;
    
    /**
     * Constructeur par defaut
     */
    public Emprunt() {

    }

    /**
     * Construteur d'un emprunt
     * 
     * @param a
     *            Dictionnaire des appareils et leurs nombres
     * @param debut
     *            Date de debut de l'emprunt
     * @param fin
     *            Date de fin de l'emprunt
     * @param emprunteur
     *            Emprunteur
     */
    public Emprunt(HashMap<Appareil, Integer> a, Calendar debut, Calendar fin,
            Emprunteur emprunteur) {
        
        // Initialisation du dictionnaire
        this.listeAppareils = a;

        // Initialisation de la date de debut
        this.dateDebut = debut;

        // Initialisation de la date de fin
        this.dateFin = fin;

        // Initialisation de l'emprunteur
        this.emprunteur = emprunteur;
        
        this.id = 0;
        
    }

    /**
     * @return the dateDebut
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * @param dateDebut
     *            the dateDebut to set
     */
    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * @return the dateFin
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * @param dateFin
     *            the dateFin to set
     */
    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * @return the emprunteur
     */
    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    /**
     * @param emprunteur
     *            the emprunteur to set
     */
    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    /**
     * @return the emprunte
     */
    public HashMap<Appareil, Integer> getEmprunte() {
        return listeAppareils;
    }

    /**
     * @param emprunte
     *            the emprunte to set
     */
    public void setEmprunte(HashMap<Appareil, Integer> emprunte) {
        this.listeAppareils = emprunte;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ajout d'un appareil et du nombre voulu dans l'emprunt
     * 
     * @param a
     *            Appareil a ajouter
     * @param i
     *            Nombre voulu de l'appareil
     */
    public void addAppareil(Appareil a, Integer i) {
        if (a == null || i == null)
            return;
        listeAppareils.put(a, i);
    }

    /**
     * Modifier le nombre emprunt d'un appareil donne
     * 
     * @param a
     *            Appareil dont le nombre voulu sera modifie
     * @param i
     *            Nombre voulu d'appareils
     */
    public void modifierEmprunte(Appareil a, Integer i) {
        if (a == null || i == null)
            return;
        if (listeAppareils.get(a) == null)
            return;
        listeAppareils.put(a, i);
    }

    /**
     * Retire un appareil de l'emprunt
     * 
     * @param a
     *            Appareil a enlever
     */
    public void retirerAppareil(Appareil a) {
        if (listeAppareils.get(a) == null)
            return;
        listeAppareils.remove(a);
    }

}
