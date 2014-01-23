package view.graphic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import model.Emprunteur;
import model.Gestionnaire;
import model.Stock;
import controller.StockProjectController;
import view.graphic.Selector;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Userview extends JPanel {
	private Window parent;
	
	private StockProjectController controller;
	private Emprunteur emp;
	
	private JPanel menucards;
	private JPanel cards;

	private JLabel infosUser;
	
	private Selector empruntSelector;
	
	public Userview(Window parent, StockProjectController c) {
		this.parent = parent;
		this.controller = c;
		
		this.setLayout(new BorderLayout());
		infosUser = new JLabel();
		
		this.add(infosUser,BorderLayout.NORTH);
		menucards = new JPanel(new CardLayout());
		menucards.add(new Menu(parent, "Menu Emprunteur", menuEmprunteur()),"menuemprunteur");
		menucards.add(new Menu(parent, "Menu Gestionnaire", menuGestionnaire()),"menugestionnaire");
		cards = new JPanel(new CardLayout());
		empruntSelector = new Selector("Ajouter a l'emprunt");
		cards.add(new JLabel(),"empty");
		cards.add(empruntSelector,"emprunt");
		
		this.add(menucards, BorderLayout.SOUTH);
		this.add(cards, BorderLayout.CENTER);
		
		
	}
	
	private void changeMenu() {
		CardLayout cl = (CardLayout) menucards.getLayout();
		cl.next(menucards);
	}
	
	private void changeZone(String s) {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards,s);
	}
	
	public void updateInfosUser() {
		emp = controller.getEmprunteur();
		infosUser.setText("Connecté en tant que "+emp.getNom()+" ("+emp.getClass().toString()+")");
		if(emp instanceof Gestionnaire) {
			changeMenu();
		}
	}
	
	public Map<String, ActionListener> menuEmprunteur() {
		Map<String, ActionListener> m = new HashMap<String,ActionListener>();
		m.put("Emprunter", new ActionUtilisateur("emprunter"));
		m.put("Voir mes emprunts", new ActionUtilisateur("emprunts"));
		m.put("Changer des informations", new ActionUtilisateur("changedatas"));
		return m;
	}
	
	public Map<String, ActionListener> menuGestionnaire() {
		Map<String, ActionListener> m = new HashMap<String,ActionListener>();
		m.put("Gérer les emprunts", new ActionUtilisateur("emprunts"));
		m.put("Liste des emprunteurs", new ActionUtilisateur("userlist"));
		m.put("Gérer le stock", new ActionUtilisateur("stock"));
		m.put("Statistiques", new ActionUtilisateur("stats"));
		return m;
	}
	
	
	public void nouvelEmprunt(Stock s) {
		empruntSelector.remplirListeGauche(s.getStock().keySet().toArray());
	}

	public void affichageEmprunts() {
		//TODO
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
			switch(action) {
			case "switchmenus":
				changeMenu();
			}
		}
	}
}
