package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.RowSorterEvent.Type;

import view.Vue;
import model.Enseignant;
import model.Enseignement;
import model.Etudiant;
import model.Appareil;
import model.Database;
import model.Emprunt;
import model.Emprunteur;
import model.Gestionnaire;
import model.OS;
import model.Systeme;
import model.Stock;

/**
 * Classe centrale du projet, coordonne la communication entre la vue et le modele.
 * La classe est appeler par la vue des qu'il y a besoin de modifier ou d'effectuer
 * des traitements sur les données.
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

	// Reference sur le stock empruntable
	private Stock stockEmpruntable;
	
	// Reference sur le stock rendu
	private Stock stockRendu;
	
	// Reference sur le stock reparation
	private Stock stockReparation;

	// Reference sur l'emprunteur courant
	private Emprunteur emp;

	/**
	 * Constructeur par defaut. Initialise les attributs necessaires.
	 */
	public StockProjectController() {
		// Initialisation de la base de donnes
		db = new Database();
		// Initialisation du stock d'appareils empruntables
		stockEmpruntable = new Stock("Empruntable");
		// Initialisation du stock d'appareils rendus
		stockEmpruntable = new Stock("Rendu");
		// Initialisation du stock d'appareils reparations
		stockEmpruntable = new Stock("Reparation");
		// Initialisation du systeme
		systeme = new Systeme(stockEmpruntable, db);
		// Initialisation de la vue
		vue = new Vue(this);
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
	 * Retourne l'objet stock
	 *
	 * @return the stock
	 */
	public Stock getStock(String nom) {
		switch (nom)
		{
		case "Empruntable":
		{
			return stockEmpruntable;
		}
		case "Rendu":
		{
			return stockRendu;
		}
		case "Reparation":
		{
			return stockReparation;
		}
		default:
		{
			return stockEmpruntable;
		}
		}
	}

	/**
	 * Fixe le systeme
	 *
	 * @param systeme
	 * the systeme to set
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
	 * @return the db
	 */
	public Database getDatabase() {
		return db;
	}

	/**
	 * @param db
	 * the db to set
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
	 * l'emprunteur courant a fixer
	 */
	public void setEmprunteur(Emprunteur emp) {
		this.emp = emp;
	}

	/**
	 * La methode reparation permet de changer l'etat de l'appareil en BIEN.
	 * @param id L'id de l'appareil que l'on veut reparer.
	 * @param nb Le nombre d'appareil a reparer.
	 * @return Un boolean indiquant si l'appareil a ete repare ou non.
	 */
	/*
	public boolean reparation(int id, int nb) 
	{
		Appareil appareil = this.stock.getAppareilParId(id);
		if(appareil == null)
		{
			return false;
		}
		this.stock.changerEtat(appareil,Etat.BIEN,nb);
		return true;
	}*/
	
	/**
	 * Cette methode permet de renvoyer la liste des appareils filtres
	 * @param filtre Le filtre choisi.
	 * @return Les appareils filtres.
	 */
	public int affichageFiltreAppareil(ArrayList<String> filtre){
		model.Type typeFiltre = null; //Il y a besoin du "model." � cause du conflit de nommage avec quelque chose en JAVA qui existe d�j�.
		OS osFiltre = null;
		
		// Permet de voir si la personne a rentr� les 2 mots cl�s pour le filtre.
		if(filtre.size() != 2)
			return 2;
		
		boolean passageType = false;
		boolean passageOS = false;
		
		// Boucle sur les types et met passageType a true si l'�tat entr� en premi�re info
		// dans filtre correspond � un type de la classe Type ou � null.
		if (filtre.get(0).equals("null"))
		{
			passageType = true;
		}
		else
		{
			for(model.Type type : model.Type.values()) 
			{
				if(filtre.get(0).toLowerCase().equals(type.name().toLowerCase()))
				{
					passageType = true;
					typeFiltre = type;
				}				
			}
		}
		System.out.println(passageType);
		
		// M�me principe pour cette boucle mais sur les OS.
		if (filtre.get(1).equals("null"))
		{
			passageOS = true;
		}
		else
		{
			for(OS os : OS.values()) 
			{
				if(filtre.get(1).toLowerCase().equals(os.name().toLowerCase()))
				{
					passageOS = true;
					osFiltre = os;
				}	
			}
		}
		System.out.println(passageOS);

		// Si une des informations est fausse.
		if(passageType == false || passageOS == false)
			return 1;
		
		// Sinon
		HashMap<Appareil,Integer> hash =this.stock.getAppareilsParEtatTypeOs(typeFiltre,osFiltre);
		
		System.out.println("Objets trouvés : ");
		System.out
				.println("ID  | Type       | Reference            | Nombre en stock");
		for (Appareil a : hash.keySet()) {
			System.out.println(a.toString() + hash.get(a));
		}
		return 0;
	}
	
	public int achatAppareil(int id, int nombre) {
		Appareil appareil = this.stock.getAppareilParId(id);
		if(appareil == null){
			return 1; //id invalide
		}
		int n = this.stock.get(appareil);
		n = n + nombre;
		//appareil.setEtat(Etat.NEUF);
		this.stock.modifierStock(appareil, n);
		return 0;//achat effectué
	}
	
	/**
	 * La methode ajouterEmprunteur permet d'ajouter un emprunteur a la liste d'emprunteurs.
	 * @param Le type d'utilisateur à créer
	 * @param identifiant Le nom d'utilisateur de l'emprunteur.
	 * @param motDePasse Le mot de passe de l'emprunteur.
	 * @param nomDeFamille Le nom de famille de l'emprunteur.
	 * @param prenom Le prenom de l'emprunteur.
	 * @param matiere La liste des enseignements de l'emprunteur.
	 * @return Un entier<br />1: L'identifiant est déjà pris<br />2: Une des matières choisie est incorrecte
	 */
	public int ajouterEmprunteur(String type, String identifiant, String motDePasse, String nomDeFamille, String prenom, ArrayList<String> matieres) {
		int status = 0;
		int i = 0;
		// Recuperation de la liste des emprunteurs
		ArrayList<Emprunteur> emprunteurs = db.getEmprunteurs();
		// on vérifie si l'identifiant n'est pas déjà pris
		while (i < emprunteurs.size() && status == 0) {
			if (emprunteurs.get(i).getNom().equals(identifiant)) {
				return 1;
			}
			i++;
		}
		// on convertis les matières en Enum
		ArrayList<Enseignement> enumsMatieres = new ArrayList<Enseignement>();
		for(String m:matieres) {
			if(Enseignement.verifieEnum(m)) {
				// Permet d'éviter les doublons
				if (!enumsMatieres.contains(Enseignement.getEnum(m)))
				{
					enumsMatieres.add(Enseignement.getEnum(m));
				}
			} else {
				status = 2;
			}
		}
		Emprunteur emp;
		if (enumsMatieres.size()>0)
		{
			switch(type) {
			case "enseignant":
				emp = new Enseignant(identifiant, motDePasse, nomDeFamille + "." + prenom, enumsMatieres);
				break;
			case "etudiant":
				emp = new Etudiant(identifiant, motDePasse, nomDeFamille + "." + prenom, enumsMatieres);
				break;
			default:
				System.err.println("Ce type d'utilisateur est inconnu.");
				return 3;
			}
			db.ajouterEmprunteur(emp);
			db.enregistrerListeEmprunteur();
		}
		else
		{
			status = 4;
		}
		return status;
	}

	/**
	 * Test si l'utilisateur actuel de l'application existe dans la base de
	 * donnees
	 *
	 * @param nom
	 * Nom de l'utilisateur
	 * @return Vrai si l'utilisateur existe dans la base de donnees
	 */
	public boolean connect(String nom, String motDePasse)
	{
		// variable permettant de savoir si les identifiants correspondent à quelqu'un de la base de donnée.
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
	 * Permet de transferer un certain nombre d'un meme materiel d'un stock vers un autre.
	 * @param stockDepart Le stock de depart.
	 * @param stockArrivee Le stock d'arrivee.
	 * @param id L'id du materiel.
	 * @param nb Le nb de materiel a deplacer.
	 * @return Un boolean inquant si le materiel a ete deplace ou non.
	 */
	public boolean deplacerStockVersStock(Stock stockDepart, Stock stockArrivee, int id, int nb)
	{
		Appareil appareil = stockDepart.getAppareilParId(id);
		int nbAppareil = stockDepart.get(appareil);
		int difference = nbAppareil - nb;
		if (difference >= 0)
		{
			stockDepart.modifierStock(appareil, difference);
			stockArrivee.ajouterAppareil(appareil, nb);
			return true;
		}
		return false;
		
	}
	
	/**
	 * Permet de transferer un certain nombre d'un meme materiel de la liste d'emprunts vers un stock.
	 * @param stockArrivee Le stock d'arrivee.
	 * @param id L'id du materiel.
	 * @param nb Le nb de materiel a deplacer.
	 * @return Un boolean inquant si le materiel a ete deplace ou non.
	 */
	public boolean deplacerEmpruntVersStock(Stock stockArrivee, int id, int nb)
	{
		List<Emprunt> listeEmprunts = db.getEmprunts();
		int nbAppareil = stockDepart.get(appareil);
		int difference = nbAppareil - nb;
		if (difference >= 0)
		{
			stockDepart.modifierStock(appareil, difference);
			stockArrivee.ajouterAppareil(appareil, nb);
			return true;
		}
		return false;
		
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
	 * L'ID de l'emprunteur
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
	 * L'ID de l'emprunteur
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
	 *                         L'emprunteur a promouvoir
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

		db.retirerEmprunteur(emprunteur);
		db.ajouterEmprunteur(gestionnaire);
		db.enregistrerListeEmprunteur();
	}

	/**
	 * Ajoute un appareil a l'emprunt actuel
	 *
	 * @param a
	 * L'appareil a rajouter
	 * @param nb
	 * Le nombre d'appareil à rajouter
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
	 * Liste des id pour creer l'emprunt
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
			Emprunt e = new Emprunt(w, null, null, emp);

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
	 * Date de depart de l'emprunt en cours
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
	 * Date de fin de l'emprunt en cours
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
	 * Liste des id d'emprunts a refuser
	 */
	public void refuser(ArrayList<Integer> id) {
		db.retirerEmprunts(id);
	}

	/**
	 * Quitte l'application
	 */
	public void quitter() {
		System.exit(0);
	}
}

