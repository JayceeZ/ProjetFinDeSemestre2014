package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.Vue;
import model.Gestionnaire;
import model.Appareil;
import model.DataXML;
import model.Database;
import model.Emprunt;
import model.Emprunteur;
import model.Systeme;
import model.Stock;

@SuppressWarnings("unchecked")
/**
 * Classe central du projet, coordonne la communication entre la vue et le modele.
 * La classe est appeler par la vue des qu'il y a besoin de modifier ou d'effectuer
 * des traitements sur les donn�es.
 * @author Nabil ELMOUSSAID
 *
 */
public class StockProjectController {

    // Reference sur le systeme
    private Systeme systeme;

    // Reference sur la vue
    private Vue vue;

    // Reference sur la base de donnees
    private Database db;

    // Reference sur le stock
    private Stock stock;

    // Reference sur l'emprunteur courant
    private Emprunteur emp;

    /**
     * Constructeur par defaut. Initialise les attributs necessaires.
     */
    public StockProjectController() {

        // Initialisation de la vue
        vue = new Vue();

        // Envoie de la reference du controleur a la vue
        vue.setController(this);

        // Initialisation de la base de donnes
        db = new Database();

        // Initialisation du stock d'appareils
        stock = new Stock();

        // Initialisation du systeme
        systeme = new Systeme(stock, db);

        // Initialisation de l'application
        initialisation();
    }

    /**
     * Retourne l'objet systeme
     * 
     * @return the systeme
     */
    public Systeme getGestionnaire() {
        return systeme;
    }

    /**
     * Fixe le systeme
     * 
     * @param systeme
     *            the systeme to set
     */
    public void setGestionnaire(Systeme systeme) {
        this.systeme = systeme;
    }

    /**
     * Retourne la vue
     * 
     * @return the vue
     */
    public Vue getVue() {
        return vue;
    }

    /**
     * Definit la vue
     * 
     * @param vue
     *            the vue a fixer
     */
    public void setVue(Vue vue) {
        this.vue = vue;
    }

    /**
     * @return the db
     */
    public Database getDatabase() {
        return db;
    }

    /**
     * @param db
     *            the db to set
     */
    public void setDatabase(Database db) {
        this.db = db;
    }

    /**
     * Retourne l'emprunteur courant
     * 
     * @return the emprunteur
     */
    public Emprunteur getEmprunteur() {
        return emp;
    }

    /**
     * Fixe l'emprunteur courant
     * 
     * @param emp
     *            l'emprunteur courant a fixer
     */
    public void setEmprunteur(Emprunteur emp) {
        this.emp = emp;
    }

    /**
     * Recoie les commandes de la vue et les traite
     * 
     * @param commande
     */
    public void traitementCommande(Commande commande) {
        // Test si la commande est nulle
        if (commande == null)
            return;

        // Switch sur les cas possibles
        switch (commande) {

        // Initialisation de la vue
        case INIT:
            initialisation();

            // Creation d'un nouvel emprunt
        case EMPRUNT:
            nouvelEmprunt();

        case CONNECT:
            vue.connexion();

        case LISTE:
            vue.affichageEmprunts();

        case QUITTER:
            System.exit(0);

        default:
            break;
        }
    }

    /**
     * Appel des methodes necessaires a la creation d'un nouvel emprunt
     * 
     */
    private void nouvelEmprunt() {
        // Affichage du stock pour un nouvel emprunt
        vue.printStock(stock);

        // Affichage des informations necessaires
        vue.nouvelEmprunt();
    }

    /**
     * Initialise le stock, et lance la vue
     */
    private void initialisation() {
        // Lancement de l'affichage
        vue.initialisation();
    }

    /**
     * Test si l'utilisateur actuel de l'application existe dans la base de
     * donnees
     * 
     * @param nom
     *            Nom de l'utilisateur
     * @return Vrai si l'utilisateur existe dans la base de donnees
     */
    public boolean connect(String nom) {
        // Recuperation de la liste des personnes pouvant emprunter
        List<Emprunteur> emprunteurs = db.getEmprunteurs();

        // Boucle sur la liste des emprunteurs
        for (Emprunteur i : emprunteurs) {
            if (i instanceof Gestionnaire && i.getNom().equalsIgnoreCase(nom)) {
                vue.afficherVueGestionnaire();
            }
            // Verification que le nom existe dans le base de donnees
            if (i.getNom().equalsIgnoreCase(nom)) {
                // Initialisation de l'emprunteur actuel
                emp = i;
                return true;
            }
        }
        return false;

    }

    /**
     * Ajoute un appareil a l'emprunt actuel
     * 
     * @param a
     *            L'appareil a rajouter
     * @param nb
     *            Le nombre d'appareil � rajouter
     * @return Vrai si le nombre d'appareil voulu est correct
     */
    public boolean ajouterAppareilEmprunt(Appareil a, int nb) {
        if (nb == 0)
            return false;
        // Test si l'appareil peut etre ajouter a l'emprunt
        if (systeme.testAjoutAppareil(a, nb)) {
            // Ajout de l'appareil a l'emprunt
            systeme.getEmpruntEnCours().addAppareil(a, nb);
            return true;
        } else {
            return false;
        }
    }

    public void retirerAppareil(Appareil a) {
        systeme.getEmpruntEnCours().retirerAppareil(a);
    }

    /**
     * Creer un emprunt a partir de la liste d'id correspondant a des appareils.
     * Test egalement si les id fournis en parametre existent dans la base de
     * donnees.
     * 
     * @param id
     *            Liste des id pour creer l'emprunt
     * @return Vrai si l'emprunt a ete cree, faux sinon
     */
    public boolean creerEmprunt(ArrayList id) {
        // Creation d'un hashmap temporaire
        HashMap<Appareil, Integer> w = new HashMap<Appareil, Integer>();

        // Boucle sur le hashmap
        for (Appareil a : stock.getStock().keySet()) {
            // Test si les id dans la liste existent dans le stock
            if (id.contains(a.getId())) {
                // Ajout de l'appareil le dictionnaire
                w.put(a, 0);
            }
        }

        // Test si les id correspondait a des appareils existant
        if (id.size() == w.size()) {

            int nbEmprunts = db.getEmprunts().size();
            nbEmprunts++;
            // Creation d'un nouvel emprunt
            Emprunt e = new Emprunt(w, null, null, emp, nbEmprunts);

            // Ajout de l'emprunt en cours pour le systeme
            systeme.setEmpruntEnCours(e);

            // Ajout de l'emprunt dans la base de donnees
            db.ajouterEmprunt(e);

            // Retourne vrai
            return true;
        } else
            return false;

    }

    /**
     * Ajoute la date de depart a l'emprunt en cours
     * 
     * @param date
     *            Date de depart de l'emprunt en cours
     * @return Vrai si la date est correct, faux sinon
     */
    public boolean ajouterDateDebutEmprunt(Calendar date) {
        // Test de la date
        if (systeme.testDateDebut(date)) {
            systeme.getEmpruntEnCours().setDateDebut(date);
            return true;
        } else
            return false;
    }

    /**
     * Ajoute la date de fin a l'emprunt en cours
     * 
     * @param dateFin
     *            Date de fin de l'emprunt en cours
     * @return Vrai si la date est correct, faux sinon
     */
    public boolean ajouterDateFinEmprunt(Calendar dateFin) {
        // Test de la date de fin
        if (systeme.testDateFin(dateFin)) {
            systeme.getEmpruntEnCours().setDateFin(dateFin);
            return true;
        } else
            return false;
    }

    /**
     * Ajoute l'emprunt en cours finaliser a la liste des emprunts
     */
    public void ajouterEmpruntFinal() {
        db.ajouterEmprunt(systeme.getEmpruntEnCours());
        enregisterEmprunt();
    }

    /**
     * Sauvegarde la liste des emprunts dans un fichier xml
     */
    public void enregisterEmprunt() {
        db.enregistrerListeEmprunt();
    }

    /**
     * Retire les emprunts refuser par le gestionnaire
     * 
     * @param id
     *            Liste des id d'emprunts a refuser
     */
    public void refuser(ArrayList<Integer> id) {
        db.retirerEmprunts(id);
    }
}