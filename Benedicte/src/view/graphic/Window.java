package view.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Appareil;
import model.Stock;
import controller.StockProjectController;

@SuppressWarnings("serial")
public class Window extends JFrame {
	// Reference vers le controleur
	private StockProjectController controller;
	private String mode;
	
	private JPanel panel;
	private Stock stock;

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

	public void setMenu(String string) {
		this.mode = string;
		changePanel(new Dimension(300,300), new Menu(this, mode));
	}
	
	public void menuUtilisateur() {
		this.setVisible(true);
	}
	
	public void affichageEmprunts() {
		//TODO
	}
	
	public void printStock(Stock s) {
		this.stock = s;
	}
	public void nouvelEmprunt() {
		panel = new Selector("Choisir dans le stock",stock.getStock().keySet().toArray());
		changePanel(new Dimension(400,400),panel);
	}
}
