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
	 * Retorune le stock actuel
	 * 
	 * @return le stock
	 */
	public Map<Appareil, Integer> getStock() {
		return stock;
	}

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
	 * Retire tous les appareils degrades du stock
	 * 
	 */
	public void retirerDegrades(){
		Set cles = this.stock.keySet();
		Iterator it = cles.iterator();
		while(it.hasNext()){
			Appareil appareil = (Appareil)it.next();
			if(appareil.getEtat()==Etat.DEGRADE)
				retirerAppareil(appareil);
		}
	}
}
