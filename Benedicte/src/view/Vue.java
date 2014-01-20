package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

import model.Appareil;
import model.Emprunt;
import model.Stock;

import controller.Commande;
import controller.StockProjectController;

/**
 * Classe modelisant la vue, soit l'interface graphique de l'application. 
 * Cette classe s'occupe de l'affichage en console de l'application
 * 
 * @author Nabil ELMOUSSAID
 *
 */
public class Vue {

    // Reference vers le controleur
    private StockProjectController controller;

    // Entree de la lecture clavier
    private BufferedReader reader;

    /**
     * Constructeur par defaut
     */
    public Vue() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Fixe le controleur
     * 
     * @param c
     *            controleur de l'application
     */
    public void setController(StockProjectController c) {
        this.controller = c;
    }

    /**
     * Lance l'affichage de l'application
     */
    public void initialisation() {
        // Message de bienvenue
        System.out
                .println("Bienvenue, veuillez renseigner votre nom pour vous connecter : ");

        // Recuperation de l'entree de l'utilisateur
        String line = "";
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println("Probleme dans la lecture");
        }

        if (!(controller.connect(line))) {
            System.out
                    .println("Votre nom n'existe pas dans la base de donnees");
            initialisation();
        }

        // Lance le traitement de l'entree clavier
        traiterCommande();
    }

    /**
     * Traite l'ensemble des entrees de l'utilisateur
     */
    public void traiterCommande() {

        System.out
                .println("Que voulez vous faire ? : Tapez E pour emprunter, Q pour quitter, C pour changer de compte ");

        // Recuperation de l'entree de l'utilisateur
        String line = "";
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println("Problème dans la lecture");
        }

        // Switch sur la commande de l'utilisateur
        Commande action = null;
        
        switch (line) {
        case "e":
        case "E":
            action = Commande.EMPRUNT;
            break;
        case "liste":
        case "l":
            action = Commande.LISTE;
            break;
        case "q":
        case "Q":
            action = Commande.QUITTER;
            break;
        case "c":
        case "C":
            action = Commande.CONNECT;
            break;
        }

        // Lance le traitement de la commande
        controller.traitementCommande(action);
    }

    /**
     * Affichage des messages pour un nouvel emprunt et traitement des entrees
     * utilisateur.
     */
    public void nouvelEmprunt() {
        System.out
                .println("Entrez les id des appareils que vous voulez emprunter, espace "
                        + "par une virgule");

        // Recuperation de l'entree de l'utilisateur
        String line = "";
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println("Probleme dans la lecture");
        }

        // Recupere les id des appareils que l'utilisateur veut emprunter
        String[] ids = line.split(",");

        // Liste contenant les id des appareils
        ArrayList<Integer> id = new ArrayList<Integer>();

        // boucle sur les id et ajout a la liste
        for (String i : ids) {
            id.add(Integer.parseInt(i));
        }

        // Test si les id entreer sont correct
        if (controller.creerEmprunt(id)) {
            // Lance le traitement de la date d'emprnt
            nouvelleDate();
        } else {
            // Les id n'existent pas
            System.out.println("Veuillez entrer des id existants");

            // Recommence l'emprunt
            nouvelEmprunt();
        }
    }

    /**
     * Affichage des messages pour traiter la date de debut de l'emprunt
     */
    private void nouvelleDate() {
        System.out
                .println("Veuillez entrer la date de debut de votre emprunt au format suivant : "
                        + "JJ/MM/AAAA");
        Calendar dateDebut = Calendar.getInstance();

        // Recuperation de l'entree de l'utilisateur
        String line = "";
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println("Probleme dans la lecture");
        }

        // Recupere la date
        String[] date = line.split("/");
        if (date.length < 3) {
            System.out.println("Erreur dans la date");
            nouvelleDate();
        }

        // Stockage de la date
        dateDebut.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
        dateDebut.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
        dateDebut.set(Calendar.YEAR, Integer.parseInt(date[2]));

        // Verification que la date est correct
        if (!(controller.ajouterDateDebutEmprunt(dateDebut))) {
            System.out
                    .println("La date entree n'est pas valide (la date de début est avant aujourd'hui)");
            nouvelleDate();
        }

        dateFin();

    }

    /**
     * Affichage des messages pour traiter la date de fin de l'emprunt
     * 
     * @param dateDebut
     *            Date de debut de l'emprunt
     */
    private void dateFin() {
        System.out
                .println("Veuillez entrer la date de fin de votre emprunt au format suivant : "
                        + "JJ/MM/AAAA");

        // Recuperation de l'entree de l'utilisateur
        String line = "";
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println("Probleme dans la lecture");
        }

        // Creation de la date de fin
        Calendar dateFin = Calendar.getInstance();

        // Recuperation de la date entree par l'utilisateur
        String[] date = line.split("/");
        date = line.split("/");

        // Test si la date entree est correct
        if (date.length < 3) {
            System.out.println("La date entree n'est pas valide");
            nouvelleDate();
        }

        // Stockage de la date
        dateFin.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
        dateFin.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
        dateFin.set(Calendar.YEAR, Integer.parseInt(date[2]));

        // Verification que la date est correct
        if (!(controller.ajouterDateFinEmprunt(dateFin))) {
            System.out
                    .println("La date entree n'est pas valide (la date de fin est avant le début de l'emprunt)");
            dateFin();
        }

        nombreAppareils();
    }

    /**
     * Affichage des messages pour traiter le nombre d'appareils pour l'emprunt
     */
    private void nombreAppareils() {
        Emprunt enCours = controller.getGestionnaire().getEmpruntEnCours();
        System.out
                .println("Veuillez entrer le nombre d'appareils que vous souhaitez emprunter pour chaque reference : ");
        String line = " ";
        boolean vide = true;
        ArrayList<Appareil> aRetirer = new ArrayList<Appareil>();

        // Boucle sur la liste des appareils de l'emprunt
        for (Appareil a : enCours.getEmprunte().keySet()) {
            System.out.println("Combien voulez vous de : " + a.getNom() + " ?");

            // Recuperation de l'entree de l'utilisateur
            try {
                line = reader.readLine();
            } catch (Exception e) {
                System.out.println("Problème dans la lecture");
            }

            // Test sur le nombre d'appareil voulu par l'utilisateur
            if (controller.ajouterAppareilEmprunt(a, Integer.parseInt(line))) {
                System.out.println(line + " " + a.getNom()
                        + " ajoute a l'emprunt");
                vide = false;
            } else {
                System.out.println("Il n'y a pas assez de " + a.getNom()
                        + " pour cet emprunt");
                aRetirer.add(a);
            }
        }

        if (aRetirer.size() > 0) {
            for (Appareil w : aRetirer) {
                controller.retirerAppareil(w);
            }
        }
        if (!vide)
            resume();
        else {
            System.out
                    .println("Aucun appareil voulu n'est disponible pour la date voulue, veuillez recommencer l'emprunt");
            traiterCommande();
        }
    }

    /**
     * Affichage final de l'emprunt complet de l'utilisateur.
     */
    private void resume() {
        System.out.println("\nResume de l'emprunt : \nMonsieur "
                + controller.getGestionnaire().getEmpruntEnCours()
                        .getEmprunteur().getNom() + " emprunte :");

        // Boucle sur la liste des appareils de l'emprunt
        for (Appareil a : controller.getGestionnaire().getEmpruntEnCours()
                .getEmprunte().keySet()) {
            System.out.println(controller.getGestionnaire().getEmpruntEnCours()
                    .getEmprunte().get(a)
                    + " " + a.getNom());
        }

        // Formatage de la date
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

        // Application du formatage aux dates d'emprunt
        String dateDebut = format1.format(controller.getGestionnaire()
                .getEmpruntEnCours().getDateDebut().getTime());
        String dateFin = format1.format(controller.getGestionnaire()
                .getEmpruntEnCours().getDateFin().getTime());

        // Affichage de la date
        System.out.println("du " + dateDebut + " au " + dateFin);

        // Sauvegarder l'emprunt dans un fichier
        controller.ajouterEmpruntFinal();

        // Demande la prochaine action a l'utilisateur
        traiterCommande();

    }

    /**
     * Affichage du stock
     * 
     * @param s
     *            Stock a afficher
     */
    public void printStock(Stock s) {
        System.out.println("Stock : ");
        System.out
                .println("ID  | Type       | Réference            | Nombre en stock");
        for (Appareil a : s.getStock().keySet()) {
            System.out.println(a.toString() + s.getStock().get(a));
        }
    }

    /**
     * Affichage du texte pour le gestionnaire
     */
    public void afficherVueGestionnaire() {
        System.out
                .println("Vous êtes connecte en tant que gestionnaire, voici la liste des emprunts");

        affichageEmprunts();

        System.out
                .println("Indiquez les id des emprunts que vous voulez rejeter, separer par une virgule : ");
        String line = "";
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println("Probleme dans la lecture");
        }

        // Recupere les id des appareils que l'utilisateur veut emprunter
        String[] ids = line.split(",");

        // Liste contenant les id des appareils
        ArrayList<Integer> id = new ArrayList<Integer>();

        for (String i : ids) {
            id.add(Integer.parseInt(i));
        }

        controller.refuser(id);

        controller.enregisterEmprunt();

        System.exit(0);
    }

    /**
     * Affichage de la liste des emprunts
     */
    public void affichageEmprunts() {
        Iterator<Emprunt> iterator = controller.getDatabase()
                .getEmprunts().iterator();
        while(iterator.hasNext()){
            Emprunt tmp = iterator.next();
            if(iterator.hasNext()){
                if(iterator.equals(iterator.next())){
                    iterator.next();
                }
            }
            System.out.println("Numero de l'emprunt : " + tmp.getId());
            System.out.println(tmp.getEmprunteur() + " veut emprunter");
            for (Appareil a : tmp.getEmprunte().keySet()) {
                System.out.println(tmp.getEmprunte().get(a) + " " + a.getNom());
            }
            System.out.println("Du : " + tmp.getDateDebut().getTime() + " au "
                    + tmp.getDateFin().getTime());
            System.out.println("-----------------");
            
        }
    }

    /**
     * Affichage du message de connexion
     */
    public void connexion() {
        // Message de bienvenue
        System.out
                .println("Veuillez renseigner votre nom pour vous connecter : ");

        // Recuperation de l'entree de l'utilisateur
        String line = "";
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println("Probleme dans la lecture");
        }

        if (!(controller.connect(line))) {
            System.out
                    .println("Votre nom n'existe pas dans la base de donnees, veuillez recommencer");
            connexion();
        }
    }    
}
