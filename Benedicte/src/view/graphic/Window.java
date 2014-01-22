package view.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Emprunteur;
import model.Stock;
import controller.StockProjectController;

@SuppressWarnings("serial")
public class Window extends JFrame {
	// Reference vers le controleur
	private StockProjectController controller;
	private String mode;
	
	private JPanel panel;

	public Window() {
		this.setLayout(new BorderLayout());

		panel = new Connexion(this);
		this.add(panel, BorderLayout.CENTER);

		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void changePanel(Dimension d, JPanel p) {
		this.panel = p;
		panel.setPreferredSize(d);
		this.getContentPane().removeAll();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.validate();
		this.pack();
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void setController(StockProjectController c) {
		this.controller = c;
	}
	
	public StockProjectController getController() {
		return controller;
	}
	
	public void createSelectorPanel(String action, Object[] list) {
		panel = new Selector(action, list);
		changePanel(new Dimension(500,500), panel);
	}
	
	public void setMenu(Emprunteur e) {
		changePanel(new Dimension(300,300), new Menu(this, e));
	}
		 	
	/**
	* Menu de choix, se connecter ou s'enregistrer
	*/
	public void menuUtilisateur() {
		 this.setVisible(true);
	}
	
	/**
	* Menu de connexion
	 */
	public void menuConnexion() {
		//TODO
	}

	/**
	* Menu d'enregistrement
	*/
	public void menuRegistration() {
		//TODO
	}
	
	/**
	* Menu principal (actions disponibles en tant qu'utilisateur connecté)
	*/
	//TODO Doit dépendre du type d'utilisateur (gestionnaire ou emprunteur)
	public void menuPrincipal() {
		this.setMenu(controller.getEmprunteur());
	}
		 
	public void nouvelEmprunt(Stock s) {
		panel = new Selector("Choisir dans le stock", s.getStock().keySet().toArray());
		changePanel(new Dimension(400,400),panel);
	}

	public void affichageEmprunts() {
		//TODO
	}
}
