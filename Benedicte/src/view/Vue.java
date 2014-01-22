package view;

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
    private Scanner sc;

    /**
     * Constructeur par defaut
     */
    public Vue() {
    	sc = new Scanner(System.in);
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
    
	///////////////////////////////// PARTIE CONNECTION ET REGISTRATION ///////////////////////////////////////////

	/**
	 * Lance l'affichage de l'application.
     * La methode menuUtilisateur permet de se connecter ou de s'enregistrer.
     */
	public void menuUtilisateur()
	{
		// Variables utiles pour le menu utilisateur
		int choixMenuUtilisateur = 0;

		System.out.println("Menu utilisateur");
		System.out.println("Que voulez-vous faire ? \n 0. Quitter \n 1. Se connecter \n 2. S'enregistrer\n");
        
		try 
		{
			choixMenuUtilisateur = sc.nextInt();
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}

		switch(choixMenuUtilisateur)
		{
			case 0:
			{
				controller.traitementCommande(Commande.QUITTER);
			}
			case 1:
			{
				controller.traitementCommande(Commande.CONNECT); 
				break;
			}
			case 2:
			{
				controller.traitementCommande(Commande.REGISTER);
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1 et 2");
				controller.traitementCommande(Commande.INIT);
			}
		}
	}
	
	/**
     * La methode menuConnection permet a un utilisateur de se connecter si il appartient a liste des utilisateurs.
     */
	public void menuConnection()
	{
		System.out.println("Entrez votre identifiant utilisateur");
		String identifiantUtilisateur = sc.nextLine();
		System.out.println("Entrez votre mot de passe");
		String motDePasseUtilisateur = sc.nextLine();

		if(controller.connect(identifiantUtilisateur, motDePasseUtilisateur))
		{
			controller.traitementCommande(Commande.CHOIX);
		}
	}
	
	/**
     * La methode menuRegistration permet a un utilisateur de s'enregistrer et d'etre ajoute a la liste d'utilisateurs en tant qu'emprunteur.
     */
	public void menuRegistration()
	{
		// Variables utiles pour le menu registration
		int choixMenuRegistration = 0;
		
		System.out.println("Menu Registration");
		System.out.println("Que voulez-vous faire ? \n 0.Retour \n 1. Créer un enseignant \n 2. Créer un étudiant \n");
		
		try 
		{
			choixMenuRegistration = sc.nextInt();
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}

		switch(choixMenuRegistration)
		{
			case 0:
			{
				controller.traitementCommande(Commande.INIT);
				break;
			}
			case 1:
			{
				menuRegistrationEnseignantMatieres(); 
				break;
			}
			case 2:
			{
				menuRegistrationEtudiant();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1 et 2");
				menuRegistration();
			}
		}
	}
	
	private void menuRegistrationEnseignantMatieres()
	{
		System.out.println("Choisissez les enseignements séparés par une virgule sans espaces en respectant les majuscules.");
		System.out.println("Enseignements disponibles : SI, MAM, ELEC, GE, GB, BAT");
		System.out.println("Exemple : SI,MAM");
		
		// Recuperation de l'entree de l'utilisateur
        String line = "";
        line = sc.nextLine();

        // Recupere les matieres de l'utilisateur
        String[] matieres = line.split(",");

        // Liste contenant les matieres
        ArrayList<String> id = new ArrayList<String>();

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
            nouvelEmprunt(stock);
        }
	}
	
	/**
     * La methode menuRegistrationEnseignant permet a un utilisateur de s'enregistrer et d'etre ajoute a la liste d'utilisateurs en tant qu'emprunteur enseignant.
     */
	private void menuRegistrationEnseignant()
	{
		System.out.println("Entrez votre nom d'utilisateur");
		String identifiantUtilisateur = sc.nextLine();

		System.out.println("Entrez votre mot de passe");
		String motDePasseUtilisateur = sc.nextLine();
		
		System.out.println("Entrez votre nom de famille");
		String nomDeFamille = sc.nextLine();
		
		System.out.println("Entrez votre prénom");
		String prenom = sc.nextLine();

		if (controller.ajouterEnseignant(identifiantUtilisateur, motDePasseUtilisateur, nomDeFamille.toLowerCase(), prenom.toLowerCase()))
		{
			menuEmprunteur();
		}
		else
		{
			menuRegistration();
		}
	}
	
	/**
     * La methode menuRegistrationEtudiant permet a un utilisateur de s'enregistrer et d'etre ajoute a la liste d'utilisateurs en tant qu'emprunteur etudiant.
     */
	private void menuRegistrationEtudiant()
	{
		System.out.println("Entrez votre nom d'utilisateur");
		String identifiantUtilisateur = sc.nextLine();

		System.out.println("Entrez votre mot de passe");
		String motDePasseUtilisateur = sc.nextLine();
		
		System.out.println("Entrez votre nom de famille");
		String nomDeFamille = sc.nextLine();
		
		System.out.println("Entrez votre prénom");
		String prenom = sc.nextLine();

		if (controller.ajouterEtudiant(identifiantUtilisateur, motDePasseUtilisateur, nomDeFamille.toLowerCase(), prenom.toLowerCase()))
		{
			menuEmprunteur();
		}
		else
		{
			menuRegistration();
		}
	}
	
	///////////////////////////////// PARTIE GESTIONNAIRE ///////////////////////////////////////////
	/**
     * La methode menuPrincipal permet a un gestionnaire de choisir entre le menu gestionnaire et le menu emprunteur.
     */
	private void menuPrincipal()
	{
		// Variables utiles pour le menu principal
		int choixMenuPrincipal = 0;
				
		System.out.println("Menu Principal");
		System.out.println("Que voulez-vous faire ? \n 0. Se déconnecter \n 1. Accéder au menu Gestionnaire \n 2. Accéder au menu Emprunteur");
		
		try 
		{
			choixMenuPrincipal = sc.nextInt();
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}
        
        switch(choixMenuPrincipal)
        {
        	case 0:
        	{
        		controller.traitementCommande(Commande.INIT);
        		break;
        	}
        	case 1:
        	{
        		controller.traitementCommande(Commande.GESTIONNAIRE);
        		break;
        	}
        	case 2:
        	{
        		controller.traitementCommande(Commande.EMPRUNTEUR);
        		break;
        	}
        	default:
        	{
        		System.out.println("Veuillez choisir entre 0, 1 et 2");
        		controller.traitementCommande(Commande.PRINCIPAL);
        	}
        }
	}
	
	/**
     * La methode menuGestionnaire permet a un gestionnaire de choisir entre plusieurs commandes.
     */
    public void menuGestionnaire() {
        System.out
                .println("Vous êtes connecte en tant que gestionnaire, voici la liste des emprunts");

        affichageEmprunts();

        System.out
                .println("Indiquez les id des emprunts que vous voulez rejeter, separer par une virgule : ");
        String line = "";
        try {
            line = sc.nextLine();
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

	///////////////////////////////// PARTIE EMPRUNTEUR ///////////////////////////////////////////
	
    /**
     * La methode menuEmprunteur permet a un emprunteur d'emprunter.
     */
    public void menuEmprunteur() 
    {
    	// Variables utiles pour le menu registration
    	int choixMenuEmprunteur = 0;
    	
    	System.out.println("Menu Emprunteur");
        System.out.println("Que voulez vous faire ? \n 0. Se déconnecter \n 1. Emprunter");

        try 
		{
			choixMenuEmprunteur = sc.nextInt();
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}
        
        switch(choixMenuEmprunteur)
        {
        	case 0:
        	{
        		controller.traitementCommande(Commande.CONNECT);
        		break;
        	}
        	case 1:
        	{
        		controller.traitementCommande(Commande.EMPRUNT);
        		break;
        	}
        	default:
        	{
        		System.out.println("Veuillez choisir entre 0 et 1");
				menuEmprunteur();
        	}
        }
    }

    /**
     * Affichage des messages pour un nouvel emprunt et traitement des entrees
     * utilisateur.
     */
    public void nouvelEmprunt(Stock stock) {
        // Affichage du stock pour un nouvel emprunt
        printStock(stock);
        System.out
                .println("Entrez les id des appareils que vous voulez emprunter, espace "
                        + "par une virgule");

        // Recuperation de l'entree de l'utilisateur
        String line = "";
        try {
            line = sc.nextLine();
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
            nouvelEmprunt(stock);
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
            line = sc.nextLine();
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
            line = sc.nextLine();
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
                line = sc.nextLine();
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
            menuEmprunteur();
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
        menuEmprunteur();

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
}
