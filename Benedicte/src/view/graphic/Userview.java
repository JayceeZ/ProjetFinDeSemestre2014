package view.graphic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Emprunteur;
import model.Emprunt;
import model.Gestionnaire;
import model.Stock;
import model.Appareil;
import controller.StockProjectController;
import view.graphic.Selector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.*;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

@SuppressWarnings("serial")
public class Userview extends JPanel {
	private Window parent;

	private StockProjectController controller;
	private Emprunteur emp;

	private JPanel menucards;
	private JPanel cards;

	private JLabel infosUser;

	private Selector empruntSelector;
	private Emprunts zoneEmprunts;

	private boolean empruntEnCours;	

	/**
	 * Construit un panneau qui contient 3 elements :<br />
	 * - Un label avec l'utilisateur courant<br />
	 * - Une zone d'interactions<br />
	 * - Un menu
	 * 
	 * @param parent
	 *            La fenêtre qui contient ce panneau
	 * @param c
	 *            Le controleur du projet
	 */
	public Userview(Window parent, StockProjectController c) {
		this.parent = parent;
		this.controller = c;

		this.setLayout(new BorderLayout());

		// Le label de l'utilisateur courant
		infosUser = new JLabel();
		this.add(infosUser, BorderLayout.NORTH);

		// Les panneaux d'interraction
		cards = new JPanel(new CardLayout());
		zoneEmprunts = new Emprunts(controller); // Permet l'affichage et la modification d'une liste d'emprunts
		cards.add(zoneEmprunts, "useremprunts");
		empruntSelector = new Selector("Ajouter a l'emprunt");// Genere un selecteur double listes avec dates
		cards.add(empruntSelector, "emprunt");
		this.add(cards, BorderLayout.CENTER);

		// Le menu (qui peut changer)
		menucards = new JPanel(new CardLayout());
		menucards.add(new Menu(parent, "Menu Emprunteur", menuEmprunteur()),
				"menuemprunteur");
		menucards.add(
				new Menu(parent, "Menu Gestionnaire", menuGestionnaire()),
				"menugestionnaire");
		this.add(menucards, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(800, 600));
	}

	/**
	 * Permet de passer d'un menu à l'autre
	 */
	private void changeMenu(String m) {
		CardLayout cl = (CardLayout) menucards.getLayout();
		cl.show(menucards, m);
	}

	private void changeZone(String s) {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, s);
	}

	/**
	 * A appeler quand un changement d'utilisateur survient
	 */
	public void updateInfosUser() {
		emp = controller.getEmprunteur();
		infosUser.setText("Connecté en tant que " + emp.getNom() + " ("
				+ emp.getClass().toString() + ")");
		if (emp instanceof Gestionnaire) {
			changeMenu("menugestionnaire");
		}
	}

	/**
	 * Genere le contenu du menuEmprunteur
	 * 
	 * @return Une map a donner au constructeur de menu
	 */
	public Map<String, ActionListener> menuEmprunteur() {
		Map<String, ActionListener> m = new HashMap<String, ActionListener>();
		m.put("Emprunter", new ActionUtilisateur("emprunter"));
		m.put("Voir mes emprunts", new ActionUtilisateur("emprunts"));
		return m;
	}

	/**
	 * Genere le contenu du menuGestionnaire
	 * 
	 * @return Une map a donner au constructeur de menu
	 */
	public Map<String, ActionListener> menuGestionnaire() {
		Map<String, ActionListener> m = new HashMap<String, ActionListener>();
		m.put("Gérer les emprunts", new ActionUtilisateur("emprunts"));
		m.put("Liste des emprunteurs", new ActionUtilisateur("userlist"));
		m.put("Gérer le stock", new ActionUtilisateur("stock"));
		m.put("Statistiques", new ActionUtilisateur("stats"));
		return m;
	}

	/**
	 * Lance la procèdure de nouvel emprunt,<br />
	 * si un emprunt est deja en cours (booleen empruntEnCours), appelle
	 * validerEmprunt()
	 * 
	 * @param s
	 */
	public void nouvelEmprunt(Stock s) {
		if (empruntEnCours == false) {
			empruntSelector.remplirListeGauche(controller.getStockEmpruntable());
			empruntEnCours = true;
		} else {
			validerEmprunt();
		}
		changeZone("emprunt");
	}

	/**
	 * [PAS TERMINEE]<br />
	 * Essaie de valider un emprunt avec la configuration de empruntSelector<br />
	 * Si la creation de l'emprunt est valide, passe à validerDates()
	 */
	// TODO Verifier que la liste des objets n'est pas vide
	private void validerEmprunt() {
		ArrayList<Integer> i = new ArrayList();
		for (Object a : empruntSelector.getSelected()) {
			if (a instanceof Appareil) {
				i.add(((Appareil) a).getId());
			}
		}
		if (controller.creerEmprunt(i)) {
			validerDates();
		}
	}

	/**
	 * Recupere les dates dans empruntSelector<br />
	 * Essaie de les ajouter. Si le controller ne renvoie pas d'erreur,<br />
	 * on termine l'ajout d'un emprunt
	 */
	private void validerDates() {
		Calendar dateDebut = empruntSelector.getDateStart();
		if (!controller.ajouterDateDebutEmprunt(dateDebut)) {
			empruntSelector.setDateText("La date de début est incorrecte.");
		} else {
			Calendar dateFin = empruntSelector.getDateEnd();
			if (!controller.ajouterDateFinEmprunt(dateFin)) {
				empruntSelector.setDateText("La date de fin est incorrecte.");
			} else {
				System.out.println("Emprunt cree !");
				terminerProcedureEmprunt();
				affichageEmprunts("user");
			}
		}
	}

	/**
	 * Vide les listes du selecteur<br />
	 * Termine la procedure dans le controller<br />
	 * empruntEnCours devient false
	 */
	private void terminerProcedureEmprunt() {
		controller.ajouterEmpruntFinal();
		empruntSelector.reinitialise();
		empruntEnCours = false;
	}

	/**
	 * Donne la liste d'emprunts designee par who (user ou all) a la zoneemprunts
	 * @param who
	 */
	public void affichageEmprunts(String who) {
		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		switch(who) {
		case "user":
			//TODO Quand la méthode recuperer liste emprunts sera crée dans controller
			break;
		case "all":
			//TODO Idem
			emprunts = controller.getDatabase().getEmprunts();
			break;
		}
		zoneEmprunts.actualise(emprunts);
		changeZone("emprunts");
	}

	/**
	 * Action possibles
	 */
	private class ActionUtilisateur implements ActionListener {
		private String action;

		public ActionUtilisateur(String a) {
			this.action = a;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(action);
			switch (action) {
			case "emprunter":
				nouvelEmprunt(controller.getStock());
				break;
			case "useremprunts":
				affichageEmprunts("user");
				break;
			case "emprunts":
				affichageEmprunts("all");
				break;
			default:
				break;
			}
		}
	}
}
