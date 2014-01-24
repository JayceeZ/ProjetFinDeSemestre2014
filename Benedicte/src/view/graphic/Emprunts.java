package view.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.StockProjectController;
import model.Emprunt;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Emprunts extends JPanel {
	StockProjectController controller;
	
	/**
	 * Cree un panneau qui permet de lister des Emprunts<br />
	 * un bouton Supprimer qui supprime (invalide) l'emprunt de la liste
	 */
	public Emprunts(StockProjectController c) {
		this.controller = c;
		//TODO Faire une liste déroulante, les details de l'emprunt sont affiches par une liste a cote
	}
	
	public void actualise(ArrayList<Emprunt> e) {
		// TODO Actualise la liste deroulante avec cette nouvelle liste
		
	}
	
	private void supprimerEmpruntSelectionne() {
		// TODO Supprime l'emprunt selectionne par la liste deroulante
		
	}

	private class ActionEmprunt implements ActionListener {
		private String action;

		public ActionEmprunt(String a, Emprunt e) {
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
