import java.util.InputMismatchException;
import java.util.Scanner;

import stocks.StockManager;
import utilisateur.Utilisateurs;

/**
 * Une classe permettant de creer le menu interface utilisateur.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Menu
{
	// Le stock
	private StockManager stockManager;
	// La liste des utilisateurs
	private Utilisateurs utilisateurs;
	// Variables retenant les parametres de connection d'un utilisateur
	private String identifiantUtilisateur;
	private String motDePasseUtilisateur;
	// Variable utile pour le menu remise en stock
	private String choixNomMaterielRemise;
	// Le scanner
	private Scanner sc;

	/**
     * Cree le Menu avec une intialisation de toutes les variables necessaire au fonctionnement.
     */
	public Menu()
	{
		// Instanciation du stock
		stockManager = new StockManager();

		// La liste des utilisateurs
		utilisateurs = new Utilisateurs();

		// Variables retenant les parametres de connection d'un utilisateur
		identifiantUtilisateur = "";
		motDePasseUtilisateur = "";

		// Variable utile pour le menu remise en stock
		choixNomMaterielRemise = "";

		// Scanner
		sc = new Scanner(System.in);
		menuUtilisateur();
	}

	///////////////////////////////// PARTIE CONNECTION ET REGISTRATION ///////////////////////////////////////////

	/**
     * La methode menuUtilisateur permet de se connecter ou de s'enregistrer.
     */
	private void menuUtilisateur()
	{

		System.out.println(" _____ _             _     ___  ___");
		System.out.println("/  ___| |           | |    |  \\/  |");
		System.out.println("\\ `--.| |_ ___   ___| | __ | .  . | __ _ _ __   __ _  __ _  ___ _ __");
		System.out.println(" `--. \\ __/ _ \\ / __| |/ / | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|");
		System.out.println("/\\__/ / || (_) | (__|   <  | |  | | (_| | | | | (_| | (_| |  __/ |");
		System.out.println("\\____/ \\__\\___/ \\___|_|\\_\\ \\_|  |_/\\__,_|_| |_|\\__,_|\\__, |\\___|_|");
		System.out.println("                                                      __/ |");
		System.out.println("                 2013-2014  Version 2.0              |___/");
		
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
				;
				break;
			}
			case 1:
			{
				menuConnection(); 
				break;
			}
			case 2:
			{
				menuRegistration();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 1 et 2");
				menuUtilisateur();
			}
		}
	}

	/**
     * La methode menuConnection permet a un utilisateur de se connecter si il appartient a liste des utilisateurs.
     */
	private void menuConnection()
	{
		System.out.println("______________________________________________________________________________ \n");

		if (utilisateurs.vide()) 
		{
			System.out.println("Il n'y a pas encore d'utilisateurs dans le systeme, veuillez vous enregistrer");
			menuRegistration();
		}
		else
		{
			System.out.println("Entrez votre identifiant utilisateur");
			identifiantUtilisateur = sc.nextLine();
			System.out.println("Entrez votre mot de passe");
			motDePasseUtilisateur = sc.nextLine();

			switch(utilisateurs.verifierIdentifiants(identifiantUtilisateur, motDePasseUtilisateur))
			{
				case 0:
				{
					System.out.println("Une des informations entrees est invalide");
					menuUtilisateur();
					break;
				}
				case 1:
				{
					//menuEmprunteur();
					break;
				}
				case 2:
				{
					menuPrincipal();
					break;
				}
				case 3:
				{
					menuPrincipal();
					break;
				}
				default:
				{
					System.out.println("Je ne sais pas comment t'es arrive dans ce cas, va te faire foutre :)");
				}
			}
		}
	}

	/**
     * La methode menuRegistration permet a un utilisateur de s'enregistrer et d'etre ajoute a la liste d'utilisateurs en tant qu'emprunteur.
     */
	private void menuRegistration()
	{
	    System.out.println("______________________________________________________________________________ \n");

		System.out.println("Entrez votre nom d'utilisateur");
		identifiantUtilisateur = sc.nextLine();

		System.out.println("Entrez votre mot de passe");
		motDePasseUtilisateur = sc.nextLine();

		if (utilisateurs.ajouterEmprunteur(identifiantUtilisateur, motDePasseUtilisateur))
		{
			//menuEmprunteur();
		}
		else
		{
			menuRegistration();
		}
	}

	//////////////////////////////////////////////// MENU PRINCIPAL //////////////////////////////////////////////////

	/**
     * La methode menuPrincipal permet de creer le menu Principal.
     */
	private void menuPrincipal()
	{
	    System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour le menu principal
		int choixMenuPrincipal = 0;

		System.out.println("Menu principal");
		System.out.println("Que voulez-vous faire ? \n O. Se deconnecter \n 1. Gestionnaire \n 2. Emprunteur\n");

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
				menuUtilisateur();
				break;
			}
			case 1:
			{
				menuGestionnaire();
				break;
			}
			case 2:
			{
				//menuEmprunteur();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1 et 2");
				menuPrincipal();
			}
		}
	}

	//////////////////////////////////////////////////////// Partie Gestionnaire ////////////////////////////////////////////////////

	/**
     * La methode menuGestionnaire permet de creer le menu Gestionnaire.
     */
	private void menuGestionnaire()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixMenuGestionnaire = 0;

		System.out.println("Menu Gestionnaire");
		System.out.println("Que voulez-vous faire ? \n 0. Retour \n 1. Promouvoir un emprunteur \n 2. Acheter un materiel \n 3. Envoyer un materiel en reparation \n 4. Remettre un materiel en stock \n 5. Informations sur les stocks \n 6. Statistiques \n");
		
		try 
		{
			choixMenuGestionnaire = sc.nextInt();
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
		}

		switch(choixMenuGestionnaire)
		{
			case 0:
			{
				menuPrincipal();
				break;
			}
			case 1:
			{
				menuPromotion();
				break;
			}
			case 2:
			{
				acheterMateriel();
				break;
			}
			case 3:
			{
				envoyerMaterielReparation();
				break;
			}
			case 4:
			{
				remettreMateriel();
				break;
			}
			case 5:
			{
				information();
				break;
			}
			case 6:
			{
				statistique();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1, 2, 4, 5 et 6");
				menuGestionnaire();
			}
		}
	}

	// Partie Promotion

	private void menuPromotion()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		String choixMenuPromotion = "";

		System.out.println("Menu Promotion");
		System.out.println("Choisissez un emprunteur a promouvoir : \n");
		
		utilisateurs.afficherEmprunteurs();
		
		choixMenuPromotion = sc.nextLine();
		
		utilisateurs.transformerGestionnaire(choixMenuPromotion);
		
		menuGestionnaire();
	}
	
	// Partie Achat Materiel

	/**
     * La methode acheterMateriel permet d'acheter un materiel et de l'ajoute au stock empruntable.
     */
	private void acheterMateriel()
	{
		System.out.println("Achat Materiel");
		choixTypeDeMaterielMenuAchat();
	}

	/**
     * La methode choixTypeDeMaterielMenuAchat permet de choisir le type de materiel a acheter.
     */
	private void choixTypeDeMaterielMenuAchat()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixTypeDeMateriel = 0;

		System.out.println("Choisissez un type de materiel : \n 0. Retour \n 1. Tablette \n 2. Telephone \n 3. Casque \n 4. Camera \n");

		try 
		{
			choixTypeDeMateriel = sc.nextInt();		
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixTypeDeMateriel)
		{
			case 0:
			{
				menuGestionnaire();
				break;			
			}
			case 1:
			{
				acheterTablette();
				break;
			}
			case 2:
			{
				acheterTelephone();
				break;
			}
			case 3:
			{
				acheterCasque();
				break;
			}
			case 4:
			{
				acheterCamera();
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1, 2, 3 et 4");
				acheterMateriel();
			}
		}
	}

    /**
    *  La methode acheterTablette permet d'acheter une tablette et de l'ajouter au stock contenant les materiels empruntables.
    */
	private void acheterTablette()
	{
		stockManager.ajouterMaterielEmpruntable("Tablette");
		menuPrincipal();
	}

    /**
    *  La methode acheterTelephone permet d'acheter un telephone et de l'ajouter au stock contenant les materiels empruntables.
    */
	private void acheterTelephone()
	{
		stockManager.ajouterMaterielEmpruntable("Telephone");
		menuPrincipal();
	}

    /**
    *  La methode acheterCasque permet d'acheter un casque et de l'ajouter au stock contenant les materiels empruntables.
    */
	private void acheterCasque()
	{
		stockManager.ajouterMaterielEmpruntable("Casque");
		menuPrincipal();
	}

    /**
    *  La methode acheterCamera permet d'acheter une camera et de l'ajouter au stock contenant les materiels empruntables.
    */
	private void acheterCamera()
	{
		stockManager.ajouterMaterielEmpruntable("Camera");
		menuPrincipal();
	}

	// Partie Materiel en Reparation

	/**
     * La methode envoyerMaterielReparation permet d'envoyer un materiel en reparation.
     */
	private void envoyerMaterielReparation()
	{
		System.out.println("Envoyer un materiel en reperation");
		choixTypeDeMaterielMenuReparation();
	}

	/**
     * La methode choixTypeDeMaterielMenuReparation permet de choisir le type de materiel a envoyer en reparation.
     */
	private void choixTypeDeMaterielMenuReparation()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixTypeDeMateriel = 0;

		System.out.println("Choisissez un type de materiel : \n 0. Retour \n 1. Tablette \n 2. Telephone \n 3. Casque \n 4. Camera \n");

		try 
		{
			choixTypeDeMateriel = sc.nextInt();		
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixTypeDeMateriel)
		{
			case 0:
			{
				menuGestionnaire();
				break;
			}
			case 1:
			{
				envoyerTablette();
				break;
			}
			case 2:
			{
				envoyerTelephone();
				break;
			}
			case 3:
			{
				envoyerCasque();
				break;
			}
			case 4:
			{
				envoyerCamera();
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1, 2, 3 et 4");
				envoyerMaterielReparation();
			}
		}
	}

	/**
    *  La methode envoyerTablette permet de deplacer une tablette du stock rendu vers le stock atelier.
    */
	private void envoyerTablette()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		String choixNomMateriel = "";

		System.out.println("Entrez le nom de la tablette a envoyer en reparation parmi celles proposees :");
		System.out.println(stockManager.afficherStockEmpruntableTablette());
		choixNomMateriel = sc.nextLine();

		if (stockManager.reparation("Tablette", choixNomMateriel))
		{
			menuPrincipal();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			envoyerMaterielReparation();
		}
	}

    /**
    *  La methode envoyerTelephone permet de deplacer un telephone du stock rendu vers le stock atelier.
    */
	private void envoyerTelephone()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		String choixNomMateriel = "";

		System.out.println("Entrez le nom du telephone a envoyer en reparation parmi ceux proposes :");
		System.out.println(stockManager.afficherStockEmpruntableTelephone());
		choixNomMateriel = sc.nextLine();

		if (stockManager.reparation("Telephone", choixNomMateriel))
		{
			menuPrincipal();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			envoyerMaterielReparation();
		}
	}

	/**
    *  La methode envoyerCasque permet de deplacer un casque du stock rendu vers le stock atelier.
    */
	private void envoyerCasque()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		String choixNomMateriel = "";

		System.out.println("Entrez le nom du casque a envoyer en reparation parmi ceux proposes :");
		System.out.println(stockManager.afficherStockEmpruntableCasque());
		choixNomMateriel = sc.nextLine();

		if (stockManager.reparation("Casque", choixNomMateriel))
		{
			menuPrincipal();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			envoyerMaterielReparation();
		}
	}

    /**
    *  La methode envoyerCamera permet de deplacer une camera du stock rendu vers le stock atelier.
    */
	private void envoyerCamera()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		String choixNomMateriel = "";

		System.out.println("Entrez le nom de la camera a envoyer en reparation parmi celles proposees :");
		System.out.println(stockManager.afficherStockEmpruntableCamera());
		choixNomMateriel = sc.nextLine();

		if (stockManager.reparation("Camera", choixNomMateriel))
		{
			menuPrincipal();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			envoyerMaterielReparation();
		}
	}

	// Partie remise materiel en stock

	/**
     * La methode remettreMateriel permet de remettre un materiel du stock rendu vers le stock empruntable ou le stock atelier.
     */
	private void remettreMateriel()
	{
		System.out.println("Remettre un materiel en stock");
		choixTypeDeMaterielMenuRemise();
	}

	/**
     * La methode choixTypeDeMaterielMenuRemise permet de choisir le type de materiel a rendre.
     */
	private void choixTypeDeMaterielMenuRemise()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixTypeDeMateriel = 0;

		System.out.println("Choisissez un type de materiel : \n 0. Retour \n 1. Tablette \n 2. Telephone \n 3. Casque \n 4. Camera \n");

		try 
		{
			choixTypeDeMateriel = sc.nextInt();		
			sc.nextLine();
		}
		catch (InputMismatchException e) 
		{
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixTypeDeMateriel)
		{
			case 0:
			{
				menuGestionnaire();
				break;
			}
			case 1:
			{
				remettreTablette();
				break;
			}
			case 2:
			{
				remettreTelephone();
				break;
			}
			case 3:
			{
				remettreCasque();
				break;
			}
			case 4:
			{
				remettreCamera();
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1, 2, 3 et 4");
				remettreMateriel();
			}
		}
	}

    /**
    *  La methode remettreTablette permet de remettre une tablette du stock rendu vers le stock empruntable ou le stock atelier.
    */
	private void remettreTablette()
	{
		System.out.println("______________________________________________________________________________ \n");

		System.out.println("Entrez le nom de la tablette a examiner");
		System.out.println(stockManager.afficherStockRenduTablette());
		choixNomMaterielRemise = sc.nextLine();

		if (stockManager.contientRendu("Tablette",choixNomMaterielRemise))
		{
			choixActionTablette();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			remettreMateriel();
		}
	}

	/**
     * La methode choixActionTablette permet de choisir entre remettre une tablette dans le stock empruntable ou l'envoyer en reparation.
     */
	private void choixActionTablette()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixAction = 0;

		System.out.println("Remettre une tablette");
		System.out.println("Choisissez une action \n 0. Retour \n 1. Remettre en stock empruntable \n 2. Envoyer en reparation \n");

        try 
        {
			choixAction = sc.nextInt();
			sc.nextLine();
		 }
	    catch (InputMismatchException e) 
	    {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixAction)
		{
			case 0:
			{
				remettreMateriel();
				break;
			}
			case 1:
			{
				stockManager.remettre("Tablette", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			case 2:
			{
				stockManager.reparation("Tablette", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1 et 2");
				choixActionTablette();
			}	
		}
	}

    /**
    *  La methode remettreTelephone permet de remettre un telephone du stock rendu vers le stock empruntable ou le stock atelier.
    */
	private void remettreTelephone()
	{
		System.out.println("______________________________________________________________________________ \n");

		System.out.println("Entrez le nom du telephone a examiner");
		System.out.println(stockManager.afficherStockRenduTelephone());
		choixNomMaterielRemise = sc.nextLine();

		if (stockManager.contientRendu("Telephone",choixNomMaterielRemise))
		{
			choixActionTelephone();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			remettreMateriel();
		}
	}

	/**
     * La methode choixActionTelephone permet de choisir entre remettre un telephone dans le stock empruntable ou l'envoyer en reparation.
     */
	private void choixActionTelephone()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixAction = 0;

		System.out.println("Remettre un telephone");
		System.out.println("Choisissez une action \n 0. Retour \n 1. Remettre en stock empruntable \n 2. Envoyer en reparation \n");

        try 
        {
			choixAction = sc.nextInt();
			sc.nextLine();
		 }
	    catch (InputMismatchException e) 
	    {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixAction)
		{
			case 0:
			{
				remettreMateriel();
				break;
			}
			case 1:
			{
				stockManager.remettre("Telephone", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			case 2:
			{
				stockManager.reparation("Telephone", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1 et 2");
				choixActionTelephone();
			}	
		}
	}

    /**
    *  La methode remettreCasque permet de remettre un casque du stock rendu vers le stock empruntable ou le stock atelier.
    */
	private void remettreCasque()
	{
		System.out.println("______________________________________________________________________________ \n");

		System.out.println("Entrez le nom du casque a examiner");
		System.out.println(stockManager.afficherStockRenduCasque());
		choixNomMaterielRemise = sc.nextLine();

		if (stockManager.contientRendu("Casque",choixNomMaterielRemise))
		{
			choixActionCasque();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			remettreMateriel();
		}
	}

	/**
     * La methode choixActionCasque permet de choisir entre remettre un casque dans le stock empruntable ou l'envoyer en reparation.
     */
	private void choixActionCasque()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixAction = 0;

		System.out.println("Remettre un casque");
		System.out.println("Choisissez une action \n 0. Retour \n 1. Remettre en stock empruntable \n 2. Envoyer en reparation \n");

        try 
        {
			choixAction = sc.nextInt();
			sc.nextLine();
		 }
	    catch (InputMismatchException e) 
	    {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixAction)
		{
			case 0:
			{
				remettreMateriel();
				break;
			}
			case 1:
			{
				stockManager.remettre("Casque", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			case 2:
			{
				stockManager.reparation("Casque", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1 et 2");
				choixActionCasque();
			}	
		}
	}

    /**
    *  La methode remettreCamera permet de remettre une camera du stock rendu vers le stock empruntable ou le stock atelier.
    */
	private void remettreCamera()
	{
		System.out.println("______________________________________________________________________________ \n");

		System.out.println("Entrez le nom de la camera a examiner");
		System.out.println(stockManager.afficherStockRenduCamera());
		choixNomMaterielRemise = sc.nextLine();

		if (stockManager.contientRendu("Tablette",choixNomMaterielRemise))
		{
			choixActionCamera();
		}
		else
		{
			System.out.println("Ce nom n'existe pas");
			remettreMateriel();
		}
	}

	/**
     * La methode choixActionCamera permet de choisir entre remettre une camera dans le stock empruntable ou l'envoyer en reparation.
     */
	private void choixActionCamera()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixAction = 0;

		System.out.println("Remettre une camera");
		System.out.println("Choisissez une action \n 0. Retour \n 1. Remettre en stock empruntable \n 2. Envoyer en reparation \n");

        try 
        {
			choixAction = sc.nextInt();
			sc.nextLine();
		 }
	    catch (InputMismatchException e) 
	    {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixAction)
		{
			case 0:
			{
				remettreMateriel();
				break;
			}
			case 1:
			{
				stockManager.remettre("Camera", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			case 2:
			{
				stockManager.reparation("Camera", choixNomMaterielRemise);
				menuPrincipal();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1 et 2");
				choixActionCamera();
			}	
		}
	}

	// Partie Informations sur le materiel

	/**
     * La methode information permet au gestionnaire d'afficher certaines informations sur les differents stocks.
     */
	private void information()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixMenuInformation = 0;

		System.out.println("Choisissez une action \n 0. Retour \n 1. Afficher la liste de materiel empruntes \n 2. Afficher la liste du materiel empruntable \n 3. Afficher la liste du materiel en reparation \n");

		try 
        {
			choixMenuInformation = sc.nextInt();
			sc.nextLine();
		 }
	    catch (InputMismatchException e) 
	    {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixMenuInformation)
		{
			case 0:
			{
				menuGestionnaire();
				break;
			}
			case 1:
			{
				System.out.println(stockManager.afficherStockEmprunter());
				menuGestionnaire();
				break;
			}
			case 2:
			{
				System.out.println(stockManager.afficherStockEmpruntable());
				menuGestionnaire();
				break;
			}
			case 3:
			{
				System.out.println(stockManager.afficherStockAtelier());
				menuGestionnaire();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1, 2 et 3");
				information();
			}
		}
	}

	// Partie Statistique

	/**
     * La methode statistique permet au gestionnaire d'afficher certaines statistiques sur les differents stocks.
     */
	private void statistique()
	{
		System.out.println("______________________________________________________________________________ \n");

		// Variables utiles pour cette methode
		int choixMenuStatistique = 0;

		System.out.println("Choisissez une action \n 0. Retour \n 1. Le type de materiel le plus emprunte \n 2. Le plus gros emprunteur \n 3. Le type de materiel le plus souvent en panne \n");
		
		try 
        {
			choixMenuStatistique = sc.nextInt();
			sc.nextLine();
		 }
	    catch (InputMismatchException e) 
	    {
			System.out.println("Veuillez saisir un chiffre.");
			sc.next();
	    }

		switch(choixMenuStatistique)
		{
			case 0:
			{
				menuGestionnaire();
				break;
			}
			case 1:
			{
				System.out.println(stockManager.typeDeMaterielLePlusEmprunter());
				statistique();
				break;
			}
			case 2:
			{
				System.out.println(utilisateurs.lePlusGrosEmprunteur());
				statistique();
				break;
			}
			case 3:
			{
				System.out.println(stockManager.typeDeMaterielLePlusEnPanne());
				statistique();
				break;
			}
			default:
			{
				System.out.println("Veuillez choisir entre 0, 1, 2 et 3");
				statistique();
			}
		}
	}

	//////////////////////////////////////////////////////// Partie Emprunteur ////////////////////////////////////////////////////

	
	/**
	* La methode menuEmprunteur permet a un emprunteur de choisir entre emprunter, voir les emprunts qu'il a effectuer ou rendre un materiel
	*/
	/*private void menuEmprunteur () {
		System.out.println("______________________________________________________________________________ \n");
		int choixMenuEmprunteur=0;
		System.out.println("choisissez une action: \n 0. retour \n 1. emprunter \n 2. consulter vos emprunts \n 3. rendre un materiel");
		try {

		choixMenuEmprunteur = sc.nextInt();
		sc.nextLine();
		 }
		 catch (InputMismatchException e) {
		System.out.println("saisissez un chiffre.");
		sc.next();
	     }
 	if (choixMenuEmprunteur == 0 || choixMenuEmprunteur == 1 || choixMenuEmprunteur == 2 || choixMenuEmprunteur == 3) {
		switch (choixMenuEmprunteur) {
			case 0: {
           menuUtilisateur();
           break;
			}
			case 1: {
           menuEmprunt();
           break;
			}
			case 2: {
           menuVisualisationEmprunt();
           break;
		}
		    case 3: {
		    menuRestitution();
		    }
	}
}
else {
	System.out.println("Veuillez rentrer 0, 1, 2 ou 3");
	menuEmprunteur();
}
	}


	    private void menuRestitution() {
        System.out.println("______________________________________________________________________________ \n");
		int i;
		int k;
		int j;
		int l;
	    String choixNom;
		System.out.println("Rentrez le nom du materiel de votre choix \n (si le stock est vide appuyez sur n'importe quelle touche) : \n");
		for (k=0;k<listeUtilisateur.size();k++) {
			if (listeUtilisateur.get(k).getIdentifiant().equals(identifiantUtilisateur)) {
						System.out.println(listeUtilisateur.get(k).getlisteEmprunt());
		         		choixNom = sc.nextLine();
				for(j=0;j<listeUtilisateur.get(k).getlisteEmprunt().size();j++) {
					if (listeUtilisateur.get(k).getlisteEmprunt().get(j).getMaterielEmprunter().getNom().equals(choixNom)) {
						 for (i=0;i<stockRendu.getListeTypeDeMateriel().size();i++) {
						 	if (stockRendu.getListeTypeDeMateriel().get(i).getNom().equals(listeUtilisateur.get(k).getlisteEmprunt().get(j).getNomTypeDeMateriel())) {
		         			stockRendu.getListeTypeDeMateriel().get(i).getListe().add(new Materiel(listeUtilisateur.get(k).getlisteEmprunt().get(j).getMaterielEmprunter().getNom()));
		         			listeUtilisateur.get(k).getlisteEmprunt().remove(j);
}
                  break;
                  }
		     }
		     break;
		     }

			}
			break;
		}
		ecriturePack();
		menuEmprunteur();
}



/**
* La methode menuEmprunt permet a l'emprunteur de choisir quel type de materiel il souhaite emprunter
*/
	/*private void menuEmprunt () {
		System.out.println("______________________________________________________________________________ \n");
		 int choixMenuEmprunt=0;
		 System.out.println("Que voulez vous emprunter ? \n 0. Retour \n 1. Tablette \n 2. Telephone \n 3. Casque \n 4. Camera");
		 try {
		 choixMenuEmprunt = sc.nextInt();
		 sc.nextLine();
		  }
		  catch (InputMismatchException e) {
		System.out.println("Saisissez un chiffre.");
		sc.next();
	      }

		 if(choixMenuEmprunt == 0 || choixMenuEmprunt == 1 || choixMenuEmprunt == 2 || choixMenuEmprunt == 3 || choixMenuEmprunt == 4) {
		 	switch(choixMenuEmprunt) {
		 		case 0: {
		 			menuEmprunteur();
		 			break;
		 		}
		 		case 1: {
		 			menuEmprunterTablette();
		 			break;
		 		}
		 		case 2: {
		 			menuEmprunterTelephone();
		 			break;
		 		}
		 		case 3: {
		 			menuEmprunterCasque();
		 			break;
		 		}
		 		case 4: {
		 			menuEmprunterCamera();
		 		}
		 	}
		 }
		 else {
		 	System.out.println("Veuillez entrer un nombre entre 0 et 4.");
		 	menuEmprunt();
		 }
	}
/**
* La methode menuEmprunterTablette permet d'emprunter une tablette
*/
	/*private void menuEmprunterTablette() {
		System.out.println("______________________________________________________________________________ \n");
		int i;
	    String choixNomTablette;
		System.out.println("Rentrez le nom du materiel de votre choix \n (si le stock est vide appuyez sur n'importe quelle touche) : \n");
		System.out.println(tabletteEmpruntable);
		choixNomTablette = sc.nextLine();

		if (tabletteEmpruntable.verifieMaterielRetirable(choixNomTablette)){
			tabletteEmpruntable.retirerMateriel (choixNomTablette);
			for (i=0;i<listeUtilisateur.size();i++) {
				if (listeUtilisateur.get(i).getIdentifiant().equals(identifiantUtilisateur)) {
					Emprunt empruntSelectionnee = new Emprunt(identifiantUtilisateur,  new Materiel(choixNomTablette), "Tablette" , 7);
					listeUtilisateur.get(i).getlisteEmprunt().add(empruntSelectionnee);
					stockEmprunter.add(empruntSelectionnee);
                    tabletteEmpruntable.augmenterNombreEmprunt();
                    ecriturePack();
                    menuEmprunteur();
                    
				}
			}
		}
			else {
				System.out.println("Materiel non-retirable.");
                menuEmprunt();
			}
		
	}
/**
* La methode menuEmprunterTelephone permet d'emprunter un telephone.
*/
	 /*private void menuEmprunterTelephone() {
	 	System.out.println("______________________________________________________________________________ \n");
	 	int i;
	 	String choixNomTelephone;
	    System.out.println("Rentrez le nom du materiel de votre choix \n (si le stock est vide appuyez sur n'importe quelle touche): \n");
		System.out.println(telephoneEmpruntable);
		choixNomTelephone = sc.nextLine();

		if (telephoneEmpruntable.verifieMaterielRetirable(choixNomTelephone)){
			telephoneEmpruntable.retirerMateriel (choixNomTelephone);
			for (i=0;i<listeUtilisateur.size();i++) {
				if (listeUtilisateur.get(i).getIdentifiant().equals(identifiantUtilisateur)) {
					Emprunt empruntSelectionnee = new Emprunt(identifiantUtilisateur,  new Materiel(choixNomTelephone), "Telephone" , 7);
					listeUtilisateur.get(i).getlisteEmprunt().add(empruntSelectionnee);
					stockEmprunter.add(empruntSelectionnee);
                    telephoneEmpruntable.augmenterNombreEmprunt();
                    ecriturePack();
                    menuEmprunteur();
                    
				}
			}
		}
			else {
				System.out.println("Materiel non-retirable.");
                menuEmprunt();
			}
	 }

/**
* La methode menuEmprunterCasque permet d'emprunter un casque
*/
	 	 /*private void menuEmprunterCasque() {
	 	 System.out.println("______________________________________________________________________________ \n");
	 	int i;
	 	String choixNomCasque;
	    System.out.println("Rentrez le nom du materiel de votre choix \n (si le stock est vide appuyez sur n'importe quelle touche): \n");
		System.out.println(casqueEmpruntable);
		choixNomCasque = sc.nextLine();

		if (casqueEmpruntable.verifieMaterielRetirable(choixNomCasque)){
			casqueEmpruntable.retirerMateriel (choixNomCasque);
			for (i=0;i<listeUtilisateur.size();i++) {
				if (listeUtilisateur.get(i).getIdentifiant().equals(identifiantUtilisateur)) {
					Emprunt empruntSelectionnee = new Emprunt(identifiantUtilisateur,  new Materiel(choixNomCasque), "Casque" , 7);
					listeUtilisateur.get(i).getlisteEmprunt().add(empruntSelectionnee);
					stockEmprunter.add(empruntSelectionnee);
                    casqueEmpruntable.augmenterNombreEmprunt();
                    ecriturePack();
                    menuEmprunteur();
                    
				}
			}
		}
			else {
				System.out.println("Materiel non-retirable.");
                menuEmprunt();
			}
	 }
/**
* La methode menuEmprunterCamera permet d'emprunter une camera
*/
	 	 /*private void menuEmprunterCamera() {
	 	 System.out.println("______________________________________________________________________________ \n");
	 	int i;
	 	String choixNomCamera;
	    System.out.println("Rentrez le nom du materiel de votre choix a examiner \n (si le stock est vide appuyez sur n'importe quelle touche): \n");
		System.out.println(cameraEmpruntable);
		choixNomCamera = sc.nextLine();

		if (cameraEmpruntable.verifieMaterielRetirable(choixNomCamera)){
			casqueEmpruntable.retirerMateriel (choixNomCamera);
			for (i=0;i<listeUtilisateur.size();i++) {
				if (listeUtilisateur.get(i).getIdentifiant().equals(identifiantUtilisateur)) {
					Emprunt empruntSelectionnee = new Emprunt(identifiantUtilisateur,  new Materiel(choixNomCamera), "Camera" , 7);
					listeUtilisateur.get(i).getlisteEmprunt().add(empruntSelectionnee);
					stockEmprunter.add(empruntSelectionnee);
                    cameraEmpruntable.augmenterNombreEmprunt();
                    ecriturePack();
                    menuEmprunteur();
                    
				}
			}
		}
			else {
				System.out.println("Materiel non-retirable.");
                menuEmprunt();
			}
	 }
	 /**
	 * La methode menuVisualisationEmprunt permet de choisir entre visualiser les emprunts ou retourner en arriere.
	 */
	 /*private void menuVisualisationEmprunt() {
	 	System.out.println("______________________________________________________________________________ \n");
	 	int i;
	 	int choixVisualisation=0;
	 	System.out.println("Choisissez ce que vous voulez faire : \n 0. Retour \n 1. Visualiser vos emprunts");
	 	try {
	 	choixVisualisation = sc.nextInt();
		 sc.nextLine();
		}
		catch (InputMismatchException e) {
		System.out.println("Saisissez un chiffre.");
		sc.next();
		}
		 if (choixVisualisation == 0 || choixVisualisation == 1) {
		 	switch (choixVisualisation) {
		 		case 0: {
		 			menuEmprunteur();
		 			break;
		 		}
		 		case 1: {
		 			visualisationEmprunt();
		 		}
		 	}
		 }
		 else {
		 	System.out.println("Entrez 0 ou 1.");
		 	menuVisualisationEmprunt();
		 }
	 }
/**
* La methode visualisationEmprunt permet de visualiser les emprunts.
*/
	 /*private void visualisationEmprunt() {
	 	int i;
	 	for (i=0;i<listeUtilisateur.size();i++){
	 		if(listeUtilisateur.get(i).getIdentifiant().equals(identifiantUtilisateur)) {
	 			System.out.println("Voici vos emprunts : \n");
	 			System.out.println(listeUtilisateur.get(i) + "\n");
                menuEmprunteur();
	 		}
	 	}
	 } */
}

