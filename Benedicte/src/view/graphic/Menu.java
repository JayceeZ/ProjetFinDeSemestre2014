package view.graphic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Emprunteur;
import model.Gestionnaire;
import controller.Commande;

public class Menu extends JPanel {
	Window parent;
	
	JButton gestion;
	JButton emprunter;
	JButton liste;
	JButton changerDeCompte;
	JButton quitter;
	
	public Menu(Window parent, Emprunteur e) {
		this.parent = parent;
		parent.setTitle("Menu");
		this.setLayout(new GridLayout(5,1));
		
		emprunter = new JButton("Emprunter");
		emprunter.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  bouton_action(e);
		      }
		    });
		liste = new JButton("Afficher mes emprunts");
		liste.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  bouton_action(e);
		      }
		    });
		
		if(e instanceof Gestionnaire) {
			gestion = new JButton("Gestion des données");
			gestion.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  bouton_action(e);
			      }
			    });
			this.add(gestion);
		}
		
		changerDeCompte = new JButton("Changer de compte");
		changerDeCompte.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  bouton_action(e);
		      }
		    });
		quitter = new JButton("Quitter");
		quitter.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  bouton_action(e);
		      }
		    });
		
		this.add(emprunter);
		this.add(liste);
		this.add(changerDeCompte);
		this.add(quitter);
	}

	//TODO 4 actions boutons
	private void bouton_action(ActionEvent e) {
		if(e.getSource() == gestion) {
			//TODO
		} else if(e.getSource() == emprunter) {
			parent.getController().traitementCommande(Commande.EMPRUNT);
		} else if (e.getSource() == liste) {
			//TODO
		} else if (e.getSource() == changerDeCompte) {
			//TODO
		} else {
			parent.getController().traitementCommande(Commande.QUITTER);
		}
	}
}
