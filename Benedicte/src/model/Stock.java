package model;


import java.util.*;

/**
 * Classe modelisant le stock de l'application, soit la liste des appareils.
 * 
 * @author Nabil ELMOUSSAID
 * 
 */
@SuppressWarnings("unchecked")
public class Stock {

	//le stock
	private Map<Appareil,Integer> stock;

	// Nom du fichier par defaut contenant le stock
	private String fichierStock = "Stock";

	// Version du fichier par defaut contenant le stock
	private String versionFichierStock = "1.0.0";

	/**
	 * Constructeur par defaut. Initialise le dictionnaire et le charge depuis
	 * le fichier xml indiquer dans les attributs.
	 */
	public Stock() {
		stock = new HashMap<Appareil, Integer>();
		stock = (Map<Appareil, Integer>) DataXML.load(fichierStock,
				versionFichierStock);
	}

	/**
	 * Constructeur initialisant le stock a partir d'un dictionnaire passer en
	 * parametre
	 * 
	 * @param s
	 *            Dictionnaire decrivant le stock
	 */
	public Stock(Map<Appareil, Integer> s) {
		this.stock = s;
	}

	/**
	 * Retourne le stock actuel
	 * 
	 * @return le stock
	 */
	public Map<Appareil, Integer> getStock() {
		return stock;
	}

	/**
	 * Retourne l'entier associee a l'appareil
	 * @param a L'appareil.
	 * @return Le nombre de cete appareil.
	 */
	public Integer get(Appareil a) {
		return stock.get(a);
	}

	/**
	 * Ajoute une appareil au stock
	 * 
	 * @param a
	 *            Appareil a ajouter
	 * @param i
	 *            Nombre de l'appareil donne
	 */
	public void ajouterAppareil(Appareil a, Integer i) {
		if (a == null || i == null)
			return;
		int nextId = getPlusGrosId();
		a.setId(nextId);
		stock.put(a, i);
	}

	/**
	 * Modifie le nombre d'un appareil
	 * 
	 * @param a
	 *            Appareil a modifier
	 * @param i
	 *            Nouveau nombre de l'appareil donne
	 */
	public void modifierStock(Appareil a, Integer i) {
		if (a == null || i == null)
			return;
		if (stock.get(a) == null)
			return;
		stock.put(a, i);
	}

	/**
	 * Retire un appareil du stock
	 * 
	 * @param a
	 *            Appareil a retirer du stock
	 */
	public void retirerAppareil(Appareil a) {
		if (stock.get(a) == null)
			return;
		stock.remove(a);
	}

	/**
	 * Récupère l'id le plus grand existant parmis tous les appareils présents .
	 * @return
	 */
	public int getPlusGrosId() {
		int id = 0;
		Set<Appareil> liste = this.stock.keySet();
		for(Appareil app : liste) {
			if (app.getId() > id)
				id = app.getId();
		}
		return id;
	}
	
	public Appareil getAppareilParId(int id) {
		for(Appareil app : this.stock.keySet()){
			if(app.getId()==id)
				return app;
		}
		return null;
	}
	
	/**
	 * Cette methode permet d'avoir un hashmap contenant un repertoire des appareils selon
	 *  Type et OS. Et de les classer avec le nombre d'appareil identiques.
	 * @param type Le Type de l'appareil.
	 * @param os L'OS de l'appareil.
	 * @return Un HashMap contenant les appareils et le nombre d'appareils identiques.
	 */
	public HashMap<Appareil,Integer> getAppareilsParEtatTypeOs(Type type,OS os) {
		HashMap<Appareil,Integer> liste = new HashMap<Appareil,Integer>();
		Set<Appareil> liste_totale = this.stock.keySet();
		
		for(Appareil app : liste_totale) {
			if (((app.getType() == type) || (type ==null)) 
					&& ((app.getOs() == os) || (os==null)))
				liste.put(app, this.get(app));
		}
		return liste;
	}
}
