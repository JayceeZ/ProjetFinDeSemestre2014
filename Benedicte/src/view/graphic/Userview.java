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

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Userview extends JPanel {
	private Window parent;
	
	private StockProjectController controller;
	private Emprunteur emp;
	
	private JPanel cards;

	private JLabel infosUser;
	
	public Userview(Window parent, StockProjectController c) {
		this.parent = parent;
		this.controller = c;
		
		this.setLayout(new BorderLayout());
		infosUser = new JLabel();
		
		this.add(infosUser,BorderLayout.PAGE_START);
		cards = new JPanel(new CardLayout());
		cards.add(new Menu(parent, "Menu Emprunteur", menuEmprunteur()),"menuemprunteur");
		cards.add(new Menu(parent, "Menu Gestionnaire", menuGestionnaire()),"menugestionnaire");
		
		this.add(cards);
	}
	
	private void changeMenu() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.next(cards);
	}
	
	public void updateInfosUser() {
		emp = controller.getEmprunteur();
		infosUser.setText("Connecté en tant que "+emp.getNom()+" ("+emp.getClass().toString()+")");
		if(emp instanceof Gestionnaire) {
			changeMenu();
		}
		parent.pack();
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
		createSelectorPanel("Choisir dans le stock", s.getStock().keySet().toArray());
	}

	public void affichageEmprunts() {
		//TODO
	}
	
	//TODO Pas terminé
	/**
	 * Permet de creer un panel ou on choisi des items dans une liste
	 * @param action Un mot qui decrit ce que l'on choisi
	 * @param list La liste des choix possibles
	 */
	public void createSelectorPanel(String action, Object[] list) {
		new Selector(action, list);
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
