package model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;


public class Systeme {
    
	private Stock stock;
	private Database database;
	private Emprunt empruntEnCours;

	public Systeme() {
	    
	}

	public Systeme(Stock stock) {
		this.stock = stock;
		this.database = new Database();
	}

	public Systeme(Stock stock2, Database db) {
	    this.stock = stock2;
	    this.database = db;
    }

    /**
	 * @return the enCours
	 */
	public Emprunt getEmpruntEnCours() {
		return empruntEnCours;
	}

	/**
	 * @param enCours
	 *            the enCours to set
	 */
	public void setEmpruntEnCours(Emprunt enCours) {
		this.empruntEnCours = enCours;
	}

		public boolean accept(Emprunt e) {
		// vï¿½rification : donnï¿½es rentrï¿½es correctes
		if(!(verifDonneesRentrees(e)))
			return false;

		// verification : les appareils demandï¿½s sont disponibles dans le stock
		for (Appareil a : e.getEmprunte().keySet()) {
			if(!(testAjoutAppareil(a,stock.getStock().get(a))))
				return false;
		}

		// verification : duree d'emprunt demandee <= duree_max de chaque objet
		// de l'emprunt
		if(!(verifDurees(e)))
			return false;

		// une matiere de l'emprunteur = une matiere de l'appareil
		if ( !(verifMatiere(e)))
			return false;

		// si c'est un etudiant et qu'il a deja un objet emprunte alors pas
		// possible
		// de plus, si reservation plus d'une semaine en avance alors non
		if (!(verifEtudiant(e.getDateDebut())))
			return false;


		return true;
	}

		private boolean verifDonneesRentrees(Emprunt e){
			int distance_now_debut = e.getDateDebut().get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(
					Calendar.DAY_OF_YEAR);
			int duree_emprunt = e.getDateFin().get(Calendar.DAY_OF_YEAR)
					- e.getDateDebut().get(Calendar.DAY_OF_YEAR);

			if (e.getEmprunteur() == null || e == null || duree_emprunt <= 0
					|| distance_now_debut <= 0)
				return false;
			return true;
		}

		private boolean verifDurees(Emprunt e) {
			int duree_emprunt = e.getDateFin().get(Calendar.DAY_OF_YEAR)
					- e.getDateDebut().get(Calendar.DAY_OF_YEAR);
			for (Appareil a : e.getEmprunte().keySet()) {
				if (a.getDureeEmpruntMax() < duree_emprunt)
					return false;
			}
			return true;
		}

		private boolean verifMatiere(Emprunt e) {
			for (Enseignement enseignement1 : e.getEmprunteur().getMatieres()) {
				for (Appareil a : e.getEmprunte().keySet()) {
					for (Enseignement enseignement2 : a.getMatieresAssociee()) {
						if (enseignement1 != enseignement2)
							return false;
					}
				}
			}
			return true;
		}

		private boolean verifEtudiant(Calendar dateDebut) {
			int distance_now_debut = dateDebut.get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(
					Calendar.DAY_OF_YEAR);
					
			if (empruntEnCours.getEmprunteur() instanceof Etudiant) {
				if ((Math.abs(distance_now_debut) > 7))
					return false;
			}
			return true;
		}

	public void emprunt(Emprunt e) {
		if (accept(e)) {
			this.database.getEmprunts().add(e);
			for (Appareil a : e.getEmprunte().keySet()) {
				int j = this.stock.getStock().get(a);
				int i = e.getEmprunte().get(a);
				this.stock.modifierStock(a, j - i);
			}
		}
	}

	/**
	 * Test si la date de dï¿½but de l'emprunt est supï¿½rieur ï¿½ la date actuelle et
	 * pas supï¿½rieur ï¿½ une semaine par rapport ï¿½ aujourd'hui dans le cas d'un
	 * ï¿½tudiant
	 * 
	 * @param dateDebut
	 * @return
	 */
	public boolean testDateDebut(Calendar dateDebut) {
	    Calendar now = Calendar.getInstance();
	    now.set(Calendar.HOUR_OF_DAY,0);
	    now.set(Calendar.MINUTE,0);
	    now.set(Calendar.SECOND,0);
	    now.set(Calendar.MILLISECOND,0);
	    
	    // Verification que l'etudiant ne tente pas de reserver plus d'une semaine a l'avance
	    boolean verif = verifEtudiant(dateDebut);
	    if(!verif) return false;
	    
		int distance_now_debut = dateDebut.get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		distance_now_debut = dateDebut.compareTo(now);
		if(distance_now_debut<0)
			return false;
		return true;
	}

	/**
	 * Test si la date de fin est supï¿½rieure ï¿½ la date de dï¿½but de l'emprunt
	 * 
	 * @param dateFin
	 * @return
	 */
	public boolean testDateFin(Calendar dateFin) {
		int duree = dateFin.compareTo(getEmpruntEnCours().getDateDebut());
		if(duree < 0)
			return false;
		return true;
	}

	/**
	 * Test si le nombre d'appareils voulu par l'emprunteur est valide en
	 * testant si il y en a assez dans le stock en fonction de la date des
	 * autres emprunts. Si c'est valide l'appareil et le nombre empruntï¿½ est
	 * ajoutÃ© ï¿½ l'emprunt courant
	 * 
	 * @param a
	 * @param nb
	 * @return
	 */
	public boolean testAjoutAppareil(Appareil a, int nb) {
		int somme = nb;
		
        for (Appareil app : stock.getStock().keySet()) {
            if(app.equals(a)){
                a = app;
            }
        }
		int maximum = stock.get(a);
		
		if(maximum == 0)
			return false;
		
		int avant = -1;
		int apres = 1;
		for(Emprunt emprunt : (ArrayList<Emprunt>)database.getEmprunts()){
            avant = empruntEnCours.getDateDebut().compareTo(emprunt.getDateFin());
		    apres = empruntEnCours.getDateFin().compareTo(emprunt.getDateDebut());
		    if(avant < 0 || apres > 0){
    			for(Appareil appareil : emprunt.getEmprunte().keySet()){
    				if(appareil.equals(a))
    					somme += emprunt.getEmprunte().get(appareil);
    			}
		    }
		    else{
		        return false;
		    }
		}	
		if(maximum - somme < 0)
			return false;
		return true;
	}
}
