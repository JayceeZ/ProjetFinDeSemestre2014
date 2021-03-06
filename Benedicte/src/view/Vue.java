package view;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.event.RowSorterEvent.Type;

import model.Appareil;
import model.Emprunt;
import model.Gestionnaire;
import model.Enseignement;
import model.OS;
import model.Stock;
import controller.StockProjectController;

/**
 * Classe modelisant la vue, soit l'interface graphique de l'application. Cette
 * classe s'occupe de l'affichage en console de l'application
 * 
 * @author Guillaume RAHBARI
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
	public Vue(StockProjectController c) {
		this.controller = c;
		this.sc = new Scanner(System.in);
		menuUtilisateur();
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

	// /////////////////////////////// PARTIE CONNECTION ET REGISTRATION
	// ///////////////////////////////////////////

	/**
	 * Lance l'affichage de l'application. La methode menuUtilisateur permet de
	 * se connecter ou de s'enregistrer.
	 */
	public void menuUtilisateur() {
		// Variables utiles pour le menu utilisateur
		int choixMenuUtilisateur = 0;

		System.out.println("Menu utilisateur");
		System.out
		.println("Que voulez-vous faire ? \n 0. Quitter \n 1. Se connecter \n 2. S'enregistrer\n");

		try {
			choixMenuUtilisateur = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}

		switch (choixMenuUtilisateur) {
		case 0:
			controller.quitter();
			break;
		case 1:
			menuConnexion();
			break;
		case 2:
			menuRegistration();
			break;
		default:
			System.out.println("Entree invalide");
			menuUtilisateur();
		}
	}

	/**
	 * La methode menuConnection permet a un utilisateur de se connecter si il
	 * appartient a la liste des utilisateurs.
	 */
	private void menuConnexion() {
		System.out.println("Entrez votre identifiant utilisateur");
		String identifiantUtilisateur = sc.nextLine();
		System.out.println("Entrez votre mot de passe");
		String motDePasseUtilisateur = sc.nextLine();

		if (!controller.connect(identifiantUtilisateur, motDePasseUtilisateur)) {
			// Si on a pas reconnu l'utilisateur
			System.err.println("Identifiants non connus du systeme.");
			menuUtilisateur();
		} else {
			if (controller.getEmprunteur() instanceof Gestionnaire) {
				menuPrincipal();
			} else {
				menuEmprunteur();
			}
		}
	}

	/**
	 * La methode menuRegistration permet a un utilisateur de s'enregistrer et
	 * d'etre ajoute a la liste d'utilisateurs en tant qu'emprunteur.
	 */
	private void menuRegistration() {
		// Variables utiles pour le menu registration
		int choixMenuRegistration = 0;

		System.out.println("Menu d'enregistrement");
		System.out
		.println("Que voulez-vous faire ? \n 0.Retour \n 1. Creer un enseignant \n 2. Creer un etudiant \n");

		try {
			choixMenuRegistration = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Entree invalide.");
			sc.next();
		}

		switch (choixMenuRegistration) {
		case 0:
			menuUtilisateur();
			break;
		case 1:
			registration("enseignant");
			break;
		case 2:
			registration("etudiant");
			break;
		default:
			System.out.println("Veuillez choisir entre 0, 1 et 2");
			menuRegistration();
		}
	}

	/**
	 * La methode menuSelector permet a l'utilsiateur de choisir parmis des
	 * possibilites
	 */
	private ArrayList<String> menuSelector(String what, Enum[] choix) {
		System.out.println("Choisissez les " + what + ":");
		System.out.println("Ne mettez pas d'espaces, et s�parez les choix par une virgule");
		System.out.print("Choix disponibles : ");
		for (Enum e : choix) {
			System.out.print(e+" ");
		}
		System.out.print("\n");
		// Recuperation de l'entree de l'utilisateur
		String line = "";
		line = sc.nextLine();

		// Recupere les matieres que l'utilisateur a entre
		ArrayList<String> strings = new ArrayList<String>();
		for (String m : line.split(",")) {
			strings.add(m);
		}
		return strings;
	}

	/**
	 * La methode registration permet a un utilisateur de s'enregistrer et
	 * d'etre ajoute a la liste d'utilisateurs en tant qu'emprunteur.
	 */
	private void registration(String typeUtilisateur) {
		System.out.println("Entrez votre nom d'utilisateur");
		String identifiantUtilisateur = sc.nextLine();

		System.out.println("Entrez votre mot de passe");
		String motDePasseUtilisateur = sc.nextLine();

		System.out.println("Entrez votre nom de famille");
		String nomDeFamille = sc.nextLine();

		System.out.println("Entrez votre prenom");
		String prenom = sc.nextLine();

		// On demande des matieres
		if (typeUtilisateur.equals("etudiant")) {
			System.out
			.println("Un etudiant ne peut choisir qu'une seule matiere !");
		}
		ArrayList<String> matieres = menuSelector("enseignements", Enseignement.values());
		while(matieres.size() < 1) {
			System.err.println("Vous devez choisir au moins une matiere.");
			matieres = menuSelector("enseignements", Enseignement.values());
		}
		while (typeUtilisateur.equals("etudiant") && matieres.size() != 1) {
			System.err
			.println("Un etudiant ne peut choisir qu'une seule matiere !");
			matieres = menuSelector("enseignements", Enseignement.values());
		}

		switch (controller.ajouterEmprunteur(typeUtilisateur,
				identifiantUtilisateur, motDePasseUtilisateur,
				nomDeFamille.toLowerCase(), prenom.toLowerCase(), matieres)) {
				case 1:// nom d'utilisateur deja pris
					System.err.println("Le nom d'utilisateur est deje pris");
					menuRegistration();
					break;
				case 2:// matieres ignoree
					System.out
					.println("Certaine matieres n'existaient pas et ont ete ignoree.");
				case 0:
					System.out.println("Compte cree, vous pouvez vous connecter.");
					menuConnexion();
					break;
				case 3:
					System.err.println(typeUtilisateur + " est inconnu");
					menuUtilisateur();
					break;
				case 4:
					System.err.println("Pas assez de mati�res");
					menuRegistration();
					break;
				default:
					System.err.println("Evenement inconnu.");
					menuUtilisateur();
		}
	}

	// /////////////////////////////// PARTIE GESTIONNAIRE
	// ///////////////////////////////////////////
	/**
	 * La methode menuPrincipal permet a un gestionnaire de choisir entre le
	 * menu gestionnaire et le menu emprunteur.
	 */
	private void menuPrincipal() {
		// Variables utiles pour le menu principal
		int choixMenuPrincipal = 0;

		System.out.println("Menu Principal");
		System.out
		.println("Que voulez-vous faire ? \n 0. Se deconnecter \n 1. Acceder au menu Gestionnaire \n 2. Acceder au menu Emprunteur");

		try {
			choixMenuPrincipal = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}

		switch (choixMenuPrincipal) {
		case 0: {
			menuUtilisateur();
			break;
		}
		case 1: {
			menuGestionnaire();
			break;
		}
		case 2: {
			menuEmprunteur();
			break;
		}
		default: {
			System.out.println("Veuillez choisir entre 0, 1 et 2");
			menuPrincipal();
		}
		}
	}

	/**
	 * La methode menuGestionnaire permet a un gestionnaire de choisir entre
	 * plusieurs commandes.
	 */
	private void menuGestionnaire() {
		// Variables utiles pour cette methode
		int choixMenuGestionnaire = 0;

		System.out.println("Menu Gestionnaire");
		System.out
		.println("Que voulez-vous faire ? " +
				"\n 0. Retour " +
				"\n 1. Promouvoir un emprunteur " +
				"\n 2. Acheter un materiel " +
				"\n 3. Informations sur les stocks " +
				"\n 4. Statistiques " +
				"\n 5. Rejeter des emprunts" +
				"\n 6. Envoyer un materiel en reparation");

		try {
			choixMenuGestionnaire = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}

		switch (choixMenuGestionnaire) {
		case 0:
			menuPrincipal();
			break;
		case 1:
			menuPromotion();
			break;
			// TODO Le reste
		case 2:
			acheterMateriel();
			break;
		case 3:
			information();
			break;
		case 4:
			// statistique();
			break;
		case 5:
			validerEmprunts();
			break;
		case 6:
			reparation();
			break;
		default:
			System.out.println("Veuillez choisir entre 0, 1, 2, 3 et 4");
			menuGestionnaire();
		}
	}

	/**
	 * Cette méthode permet au gestionnaire de réparer un certain nombre d'appareil .
	 */
	private void reparation() {
		printStock(this.controller.getStock("Rendu"));
		System.out.println("Indiquez l'id de l'appareil a envoyer en reparation : ");
		int id = 0;
		try 
		{
			id = sc.nextInt();
		} 
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez entrer un entier");
			sc.next();
		}
		
		System.out.println("Indiquez le nombre d'appareil de cette id que vous voulez envoyer en reparation : ");
		int nb = 0;
		try 
		{
			nb = sc.nextInt();
		} 
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez entrer un entier");
			sc.next();
		}
		
		if (controller.deplacerStockVersStock(controller.getStock("Rendu"), controller.getStock("Reparation"), id, nb))
		{
			System.out.println("Le nombre de materiels a ete deplace");
		}
		else
		{
			System.out.println("Impossible de deplacer le nombres de materiels demande");
		}
		menuGestionnaire();
	}
	/**
	 * Cette methode permet au gestionnaire de recuperer des informations sur les objets 
	 * presents dans le stock actuel. Il peut lister les objet selon le type et l'os.
	 */
	private void information() {
		// Variables utiles pour cette methode
		int choixInfo = 0;

		System.out.println("Informations sur les stocks");
		System.out
		.println("Que voulez-vous faire ? " +
				"\n 0. Retour " +
				"\n 1. Afficher la totalite du stock empruntable" +
				"\n 2. Filtrer des appareils du stock empruntable" +
				"\n 3. Afficher la totalite du stock rendu" +
				"\n 4. Filtrer des appareils du stock rendu" +
				"\n 5. Afficher la totalite du stock reparation" +
				"\n 6. Filtrer des appareils du stock reparation");

		try {
			choixInfo= sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.next();
		}

		switch (choixInfo) {
		case 0:
			menuGestionnaire();
			break;
		case 1:
			printStock(this.controller.getStock("Empruntable"));
			information();
			break;
			// TODO Le reste
		case 2:
			System.out.println("On peut filtrer par type et os.");
			System.out.print("Choisissez un type parmi : ");
			for (model.Type t : model.Type.values())
			{
				System.out.print(t + " ");
			}
			System.out.println("null");
			System.out.print("Choississez un OS parmi : ");
			for (OS os : OS.values())
			{
				System.out.print(os + " ");
			}
			System.out.println("null");
			System.out.println("Separez chaque choix par une virgule sans espace.");
			System.out.println("null indique que l'on ne veut pas filtrer selon cette categorie.");
			//explication pas du tout claire u_u
			//des exemples : si par exemple le gestionnaire écrit "bon,null,android" on cherche
			//les appareils en bon état d'os android et on ne prend pas compte du type de l'appareil

			// Recuperation de l'entree de l'utilisateur
			String line1 = "";
			line1 = sc.nextLine();

			// Recupere les mots cles pour le filtre. Ce que le gestionnaire veut voir.
			String[] ids1 = line1.split(",");

			// Liste contenant les mots cles pour le filtre.
			ArrayList<String> filtre1 = new ArrayList<String>();

			// boucle sur les mots cles et ajout a la liste
			for (String i : ids1) {
				filtre1.add(i);
			}
			
			System.out.println(filtre1);

			int f1 = this.controller.affichageFiltreAppareil(filtre1, controller.getStock("Empruntable"));
			if(f1 == 2){
				System.out.println("Il manque des informations pour le filtre.");
			}
			else if(f1 ==1) {
				System.out.println("Les informations donnees sont incorrectes.");
			}
			information();
			break;
		case 3:
			printStock(this.controller.getStock("Rendu"));
			information();
			break;
		case 4:
			System.out.println("On peut filtrer par type et os.");
			System.out.print("Choisissez un type parmi : ");
			for (model.Type t : model.Type.values())
			{
				System.out.print(t + " ");
			}
			System.out.println("null");
			System.out.print("Choississez un OS parmi : ");
			for (OS os : OS.values())
			{
				System.out.print(os + " ");
			}
			System.out.println("null");
			System.out.println("Separez chaque choix par une virgule sans espace.");
			System.out.println("null indique que l'on ne veut pas filtrer selon cette categorie.");
			//explication pas du tout claire u_u
			//des exemples : si par exemple le gestionnaire écrit "bon,null,android" on cherche
			//les appareils en bon état d'os android et on ne prend pas compte du type de l'appareil

			// Recuperation de l'entree de l'utilisateur
			String line2 = "";
			line2 = sc.nextLine();

			// Recupere les mots cles pour le filtre. Ce que le gestionnaire veut voir.
			String[] ids2 = line2.split(",");

			// Liste contenant les mots cles pour le filtre.
			ArrayList<String> filtre2 = new ArrayList<String>();

			// boucle sur les mots cles et ajout a la liste
			for (String i : ids2) {
				filtre2.add(i);
			}
			
			System.out.println(filtre2);

			int f2 = this.controller.affichageFiltreAppareil(filtre2, controller.getStock("Rendu"));
			if(f2 == 2){
				System.out.println("Il manque des informations pour le filtre.");
			}
			else if(f2 ==1) {
				System.out.println("Les informations donnees sont incorrectes.");
			}
			information();
			break;
		case 5:
			printStock(this.controller.getStock("Reparation"));
			information();
			break;
		case 6:
			System.out.println("On peut filtrer par type et os.");
			System.out.print("Choisissez un type parmi : ");
			for (model.Type t : model.Type.values())
			{
				System.out.print(t + " ");
			}
			System.out.println("null");
			System.out.print("Choississez un OS parmi : ");
			for (OS os : OS.values())
			{
				System.out.print(os + " ");
			}
			System.out.println("null");
			System.out.println("Separez chaque choix par une virgule sans espace.");
			System.out.println("null indique que l'on ne veut pas filtrer selon cette categorie.");
			//explication pas du tout claire u_u
			//des exemples : si par exemple le gestionnaire écrit "bon,null,android" on cherche
			//les appareils en bon état d'os android et on ne prend pas compte du type de l'appareil

			// Recuperation de l'entree de l'utilisateur
			String line3 = "";
			line3 = sc.nextLine();

			// Recupere les mots cles pour le filtre. Ce que le gestionnaire veut voir.
			String[] ids3 = line3.split(",");

			// Liste contenant les mots cles pour le filtre.
			ArrayList<String> filtre3 = new ArrayList<String>();

			// boucle sur les mots cles et ajout a la liste
			for (String i : ids3) {
				filtre3.add(i);
			}
			
			System.out.println(filtre3);

			int f3 = this.controller.affichageFiltreAppareil(filtre3, controller.getStock("Reparation"));
			if(f3 == 2){
				System.out.println("Il manque des informations pour le filtre.");
			}
			else if(f3 ==1) {
				System.out.println("Les informations donnees sont incorrectes.");
			}
			information();
			break;
		default:
			System.out.println("Veuillez choisir entre 0, 1, et 2");
			information();
		}
	}
	/**
	 * La methode acheterMateriel permet a un gestionnaire 
	 * d'acheter un nouveau materiel pour le stock.
	 */
	private void acheterMateriel(){
		// TODO pouvoir ajouter des vrais nouveaux materiels.
		System.out.println("Choisissez le type de materiel parmi : ");
		for (model.Type t : model.Type.values())
		{
			System.out.print(t + " ");
		}
		String type = sc.nextLine();
		
		System.out.println("\n Choisissez l'OS parmi : ");
		for (OS os : OS.values())
		{
			System.out.print(os + " ");
		}
		String os = sc.nextLine();
		
		System.out.println("\n Entrez un nom pour l'appareil.");
		String nom = sc.nextLine();
		
		System.out.println("Entrez le nombre d'appareil.");
		int nombre=0;
		try {
			nombre = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Probleme dans la lecture");
			sc.next();
		}
		
		int a = this.controller.achatAppareil(type, os, nom, nombre);
		if ( a == 1) {
			System.out.println("L'id indiqué est invalide .");
			acheterMateriel();
		}
		else {
			System.out.println("Modification effectuée");
			acheterMateriel();
		}
		
	}



	/**
	 * Affiche la liste de tous emprunts en attente de validation
	 */
	public void validerEmprunts() {
		affichageEmprunts();
		System.out
		.println("Indiquez les id des emprunts que vous voulez rejeter, separes par une virgule : ");
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
	}

	/**
	 * La methode menuPromotion permet de promouvoir un emprunteur en
	 * gestionnaire.
	 */
	private void menuPromotion() {
		// Variables utiles pour le menu promotion
		String choixMenuPromotion = "";

		System.out
		.println("Veuillez choisir un emprunteur dans la liste suivante :");
		controller.afficherEmprunteurs();

		choixMenuPromotion = sc.nextLine();

		if (controller.verifierEmprunteur(choixMenuPromotion)) {
			controller.transformerEmprunteur(controller
					.renvoyerEmprunteur(choixMenuPromotion));
			menuGestionnaire();
		} else {
			System.out.println("Nom invalide");
			menuGestionnaire();
		}
	}

	// /////////////////////////////// PARTIE EMPRUNTEUR
	// ///////////////////////////////////////////

	/**
	 * La methode menuEmprunteur permet a un emprunteur d'emprunter.
	 */
	public void menuEmprunteur() {
		// Variables utiles pour le menu emprunteur
		int choixMenuEmprunteur = 0;

		System.out.println("Menu Emprunteur");
		System.out
		.println("Que voulez vous faire ? \n 0. Se deconnecter \n 1. Emprunter \n 2. Remettre un materiel");

		try {
			choixMenuEmprunteur = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}

		switch (choixMenuEmprunteur) {
		case 0: {
			menuUtilisateur();
			break;
		}
		case 1: {
			nouvelEmprunt();
			break;
		}
		case 2:
		{
			remettreMateriel();
			break;
		}
		default: {
			System.out.println("Veuillez choisir entre 0 et 1");
			menuEmprunteur();
		}
		}
	}
	
	/**
	 * Cette methode permet a un emprunteur de rendre un materiel.
	 */
	private void remettreMateriel()
	{
		affichageEmprunts();
		System.out.println("Indiquez l'id de l'appareil a remettre : ");
		int id = 0;
		try 
		{
			id = sc.nextInt();
		} 
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez entrer un entier");
			sc.next();
		}
		
		System.out.println("Indiquez le nombre d'appareil de cette id que vous voulez remettre : ");
		int nb = 0;
		try 
		{
			nb = sc.nextInt();
		} 
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez entrer un entier");
			sc.next();
		}
		
		if (controller.deplacer(controller.getStock("Rendu"), controller.getStock("Reparation"), id, nb))
		{
			System.out.println("Le nombre de materiels a ete deplace");
		}
		else
		{
			System.out.println("Impossible de deplacer le nombres de materiels demande");
		}
		menuGestionnaire();
	}

	/**
	 * Affichage des messages pour un nouvel emprunt et traitement des entrees
	 * utilisateur.
	 */
	public void nouvelEmprunt() {
		// Affichage du stock pour un nouvel emprunt
		printStock(controller.getStock());
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
			.println("La date entree n'est pas valide (la date de debut est avant aujourd'hui)");
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
			.println("La date entree n'est pas valide (la date de fin est avant le debut de l'emprunt)");
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
				System.out.println("Probleme dans la lecture");
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
		.println("ID  | Type       | Reference            | Nombre en stock");
		for (Appareil a : s.getStock().keySet()) {
			System.out.println(a.toString() + s.getStock().get(a));
		}
	}

	/**
	 * Affichage de la liste des emprunts
	 */
	public void affichageEmprunts() {
		Iterator<Emprunt> iterator = controller.getDatabase().getEmprunts()
				.iterator();
		while (iterator.hasNext()) {
			Emprunt tmp = iterator.next();
			if (iterator.hasNext()) {
				if (iterator.equals(iterator.next())) {
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
