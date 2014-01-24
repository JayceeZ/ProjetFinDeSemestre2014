package view.graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import controller.StockProjectController;
import model.Emprunt;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Emprunts extends JPanel {
	StockProjectController controller;
	
	private DefaultComboBoxModel<Emprunt> modelComboBox;
	private JComboBox<Emprunt> combobox;
	private JLabel displayEmprunt;
	
	/**
	 * Cree un panneau qui permet de lister des Emprunts<br />
	 * un bouton Supprimer qui supprime (invalide) l'emprunt de la liste
	 */
	public Emprunts(StockProjectController c) {
		this.controller = c;
		modelComboBox = new DefaultComboBoxModel<Emprunt>();
		combobox = new JComboBox<Emprunt>(modelComboBox);
		combobox.addItemListener(new ItemState());
		displayEmprunt = new JLabel();
		
		this.setLayout(new FlowLayout());
		JPanel contenu = new JPanel(new BorderLayout());
		contenu.add(combobox, BorderLayout.NORTH);
		contenu.add(displayEmprunt, BorderLayout.CENTER);
		
		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.addActionListener(new ActionEmprunt("supprimer"));
		contenu.add(boutonSupprimer,BorderLayout.SOUTH);
		
		this.add(contenu);
	}
	
	public void actualise(List<Emprunt> emprunts) {
		modelComboBox.removeAllElements();
		for(Emprunt em:emprunts) {
			modelComboBox.addElement(em);
		}
	}
	
	private void afficheEmprunt(Emprunt item) {
		//TODO affiche en détail toutes les infos d'un emprunt
		displayEmprunt.setText(item.toString());
	}
	
	private void supprimerEmpruntSelectionne() {
		Emprunt e = (Emprunt) combobox.getSelectedItem();
		if(e != null) {
			//TODO Methode à créer
			controller.supprimerEmprunt(e);
			modelComboBox.removeElement(e);
		}
	}

	private class ItemState implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			afficheEmprunt((Emprunt) arg0.getItem());
		}
	}
	
	private class ActionEmprunt implements ActionListener {
		private String action;

		public ActionEmprunt(String a) {
			this.action = a;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (action) {
			case "supprimer":
				supprimerEmpruntSelectionne();
				break;
			default:
				break;
			}
		}
	}
	
}
