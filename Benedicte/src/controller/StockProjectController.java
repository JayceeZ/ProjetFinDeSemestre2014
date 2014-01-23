package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import view.graphic.Window;
import model.Enseignant;
import model.Enseignement;
import model.Etudiant;
import model.Appareil;
import model.Database;
import model.Emprunt;
import model.Emprunteur;
import model.Gestionnaire;
import model.Systeme;
import model.Stock;

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
    private Window vue;

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
        vue = new Window();

        // Envoie de la reference du controleur a la vue
        vue.setController(this);

        // Initialisation de la base de donnes
        db = new Database();

        // Initialisation du stock d'appareils
        stock = new Stock();

        // Initialisation du systeme
        systeme = new Systeme(stock, db);

        // Initialisation de l'application
        traitementCommande(Commande.INIT);
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
    public Object getVue() {
        return vue;
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
	* La methode ajouterEnseignant permet d'ajouter un enseignant a la liste d'emprunteurs.
	* @param identifiant Le nom d'utilisateur de l'emprunteur.
	* @param motDePasse Le mot de passe de l'emprunteur.
	* @param nomDeFamille Le nom de famille de l'emprunteur.
	* @param prenom Le prenom de l'emprunteur.
	* @param matiere La liste des enseignements de l'emprunteur.
	* @return Un boolean indiquant true si l'utilisateur a ete cree et false si il existait deja.
	*/
	public boolean ajouterEnseignant (String identifiant, String motDePasse, String nomDeFamille, String prenom, ArrayList<Enseignement> matiere)
	{
		boolean booleanTMP = true;
		int i = 0;
		// Recuperation de la liste des emprunteurs
        ArrayList<Emprunteur> emprunteurs = db.getEmprunteurs();
		// La boucle while permet de mettre booleanTMP a false s'il existe deja un utilisateur avec cet identifiant, sinon c'est que l'on peut creer un utilisateur.
		while (i < emprunteurs.size() && booleanTMP)
		{
			if (emprunteurs.get(i).getNom().equals(identifiant))
			{
				booleanTMP = false;
			}
			else
			{
				i = i + 1;
			}
		}
		// Si booleanTMP = true alors on ajoute l'emprunteur a la liste des emprunteurs.
		if (booleanTMP)
		{
			db.ajouterEmprunteur(new Enseignant (identifiant, motDePasse, nomDeFamille + "." + prenom, matiere));
			db.enregistrerListeEmprunteur();
			System.out.println("Votre compte Emprunteur a bien ete cree et ajoute a notre base de donnee");
			booleanTMP = true;
		}
		else
		{
			System.out.println("Veuillez choisir un autre nom d'utilisateur");
		}
		return booleanTMP;
	}
	
	/**
	* La methode ajouterEtudiant permet d'ajouter un etudiant a la liste d'emprunteurs.
	* @param identifiant Le nom d'utilisateur de l'emprunteur.
	* @param motDePasse Le mot de passe de l'emprunteur.
	* @param nomDeFamille Le nom de famille de l'emprunteur.
	* @param prenom Le prenom de l'emprunteur.
	* @param matiere La matiere de l'etudiant.
	* @return Un boolean indiquant true si l'utilisateur a ete cree et false si il existait deja.
	*/
	public boolean ajouterEtudiant (String identifiant, String motDePasse, String nomDeFamille, String prenom, ArrayList<Enseignement> matiere)
	{
		boolean booleanTMP = true;
		int i = 0;
		// Recuperation de la liste des emprunteurs
        ArrayList<Emprunteur> emprunteurs = db.getEmprunteurs();
		// La boucle while permet de mettre booleanTMP a false s'il existe deja un utilisateur avec cet identifiant, sinon c'est que l'on peut creer un utilisateur.
		while (i < emprunteurs.size() && booleanTMP)
		{
			if (emprunteurs.get(i).getNom().equals(identifiant))
			{
				booleanTMP = false;
			}
			else
			{
				i = i + 1;
			}
		}
		// Si booleanTMP = true alors on ajoute l'emprunteur a la liste des emprunteurs.
		if (booleanTMP)
		{
			db.ajouterEmprunteur(new Etudiant (identifiant, motDePasse, nomDeFamille + "." + prenom, matiere));
			db.enregistrerListeEmprunteur();
			System.out.println("Votre compte Emprunteur a bien ete cree et ajoute a notre base de donnee");
			booleanTMP = true;
		}
		else
		{
			System.out.println("Veuillez choisir un autre nom d'utilisateur");
		}
		return booleanTMP;
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

        // Initialisation de la vue - Menu de d�part
        case INIT:
            vue.menuUtilisateur();
            break;
        case PRINCIPAL:
        	vue.menuPrincipal();
        	break;
        case GESTIONNAIRE:
        	vue.menuGestionnaire();
        	break;
        case EMPRUNTEUR:
        	vue.menuEmprunteur();
        	break;
        case CHOIX:
        	if (emp instanceof Enseignant)
        	{
        		vue.menuEmprunteur();
        	}
        	if (emp instanceof Etudiant)
        	{
        		vue.menuEmprunteur();
        	}
        	if (emp instanceof Gestionnaire)
        	{
        		vue.menuPrincipal();
        	}
        // Creation d'un nouvel emprunt
        case EMPRUNT:
            vue.nouvelEmprunt(stock);
            break;
        case CONNECT:
            vue.menuConnection();
            break;
        case REGISTER:
        	vue.menuRegistration();
        	break;
        case LISTE:
            vue.affichageEmprunts();
            break;
        case QUITTER:
            System.exit(0);
            break;
        default:
            break;
        }
    }

    /**
     * Test si l'utilisateur actuel de l'application existe dans la base de
     * donnees
     * 
     * @param nom
     *            Nom de l'utilisateur
     * @return Vrai si l'utilisateur existe dans la base de donnees
     */
    public boolean connect(String nom, String motDePasse) 
    {
    	// variable permettant de savoir si les identifiants correspondent � quelqu'un de la base de donn�e.
    	boolean booleanIdentifiants = false;
        // Recuperation de la liste des emprunteurs
        ArrayList<Emprunteur> emprunteurs = db.getEmprunteurs();
        Iterator<Emprunteur> it = emprunteurs.iterator();

        // Boucle sur la liste des emprunteurs
        while (!booleanIdentifiants && it.hasNext())
		{
			Emprunteur emprunteur = it.next();
			if (emprunteur.getNom().equals(nom) && emprunteur.getMotDePasse().equals(motDePasse))
			{
				emp = emprunteur;
				booleanIdentifiants = true;
			}
		}
		return booleanIdentifiants;
    }
    
    /**
     * Affiche la liste des emprunteurs sans les gestionnaires
     */
    public void afficherEmprunteurs()
    {
    	// L'arraylist contenant tous les utilisateurs
    	ArrayList<Emprunteur> utilisateurs = db.getEmprunteurs();
    	// l'arraylist contenant tous le sutilisateurs sauf les gestionnaires
    	ArrayList<String> emprunteurs = new ArrayList<String>();
    	for (Emprunteur emp : utilisateurs)
    	{
    		if (emp instanceof Etudiant || emp instanceof Enseignant)
    		{
    			emprunteurs.add(emp.getId());
    		}
    	}
    	System.out.println(emprunteurs);
    }
    
    /**
     * Verifie si un emprunteur existe a partir de son id.
     * 
     * @param idEmprunteur
     *            L'ID de l'emprunteur
     * @return Vrai si l'utilisateur existe dans la base de donnees
     */
    public boolean verifierEmprunteur(String idEmprunteur)
    {
    	// L'arraylist contenant tous les utilisateurs
    	ArrayList<Emprunteur> utilisateurs = db.getEmprunteurs();
    	for (Emprunteur emp : utilisateurs)
    	{
    		if (emp.getId().equals(idEmprunteur))
    		{
    			return true;
    		}    		
    	}
    	return false;
    }
    
    /**
     * Permet de renvoyer un emprunteur a partir de son ID.
     * 
     * @param idEmprunteur
     *            L'ID de l'emprunteur
     * @return L'emprunteur
     */
    public Emprunteur renvoyerEmprunteur(String idEmprunteur)
    {
    	// L'arraylist contenant tous les utilisateurs
    	ArrayList<Emprunteur> utilisateurs = db.getEmprunteurs();
    	for (Emprunteur emp : utilisateurs)
    	{
    		if (emp.getId().equals(idEmprunteur))
    		{
    			return emp;
    		}    		
    	}
    	return null;
    }
    
    /**
     * Permet de transformer un emprunteur en gestionnaire. 
     * L'emprunteur est alors remplace dans la database par un gestionnaire ayant les memes
     * parametres que lui .
     * @param emprunteur
     * 			L'emprunteur a promouvoir
     */
    public void transformerEmprunteur(Emprunteur emprunteur){
    	if(emprunteur instanceof Gestionnaire)
    		return;
    	String nom = emprunteur.getNom();
    	String motDePasse = emprunteur.getMotDePasse();
    	int dureeMaxEmprunt = emprunteur.getDureeMaxEmprunt();
    	String id = emprunteur.getId();
    	int nbMaxAppareils = emprunteur.getNbMaxMateriel();
    	ArrayList<Enseignement> matieres = emprunteur.getMatieres();
    	Gestionnaire gestionnaire = new Gestionnaire(nom, motDePasse, dureeMaxEmprunt, id, nbMaxAppareils,matieres);
    	
    	db.getEmprunteurs().remove(emprunteur);
    	db.getEmprunteurs().add(gestionnaire);
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
    public boolean creerEmprunt(ArrayList<Integer> id) {
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
