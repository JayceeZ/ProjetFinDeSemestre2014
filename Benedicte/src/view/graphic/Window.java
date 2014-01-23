package view.graphic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Emprunteur;
import model.Stock;
import controller.StockProjectController;

@SuppressWarnings("serial")
public class Window extends JFrame {
	// Reference vers le controleur
	private StockProjectController controller;
	
	private JPanel cards;
	private Userview userview;

	public Window(StockProjectController c) {
		this.controller = c;
		this.setLayout(new BorderLayout());
		
		cards = new JPanel(new CardLayout());
		cards.add(new Menu(this,"Menu Principal",menuUtilisateur()), "menuutilisateur");
		cards.add(new Connexion(this), "connect");
		userview = new Userview(this,c);
		cards.add(userview, "userview");
		
		this.setTitle("Application");
		this.add(cards,BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Permet de changer le controller
	 * @param c Le nouveau controller
	 */
	public void setController(StockProjectController c) {
		this.controller = c;
	}
	
	public StockProjectController getController() {
		return controller;
	}
	
	/**
	 * Conserve la meme fenetre pour le nouveau panel
	 * @param panel La reference pour ce panel
	 */
	public void changePanel(String panel) {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards,panel);
	}
	
	/**
	* Menu utilisateur (actions disponibles au demarrage de l'application)
	*/
	public Map<String, ActionListener> menuUtilisateur() {
		Map<String, ActionListener> m = new HashMap<String,ActionListener>();
		m.put("Se connecter", new ActionPanel(this,"connect"));
		m.put("S'inscrire", new ActionPanel(this,"register"));
		return m;
	}

	public boolean connect(String nomUtilisateur, String motDePasse) {
		if(controller.connect(nomUtilisateur, motDePasse)) {
			System.out.println("Connexion réussie");
			userview.updateInfosUser();
			changePanel("userview");
		}
		return false;
	}

	
	/**
	 * Action possibles
	 */
	private class ActionPanel implements ActionListener {
		private Window window;
		private String panel;
		
		public ActionPanel(Window parent, String p) {
			this.window = parent;
			this.panel = p;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			window.changePanel(panel);
		}
	}
}
