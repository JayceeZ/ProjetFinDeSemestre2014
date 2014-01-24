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

/**
* Cette classe definie le panneau des emprunts
* @author Isoard Jean-Christophe
*
*/
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
                contenu.add(boutonSupprimer, BorderLayout.SOUTH);

                this.add(contenu);
        }

        /**
         * Change la liste des emprunts<br />
         * Remplace tout par le contenu de la liste emprunts
         *
         * @param emprunts
         * La liste des emprunts
         */
        public void actualise(List<Emprunt> emprunts) {
                modelComboBox.removeAllElements();
                for (Emprunt em : emprunts) {
                        modelComboBox.addElement(em);
                }
        }

        /**
         * Change le JLabel par une description de l'emprunt<br />
         *
         * @param item
         * L'emprunt à afficher
         */
        private void afficheEmprunt(Emprunt item) {
                // TODO affiche en détail toutes les infos d'un emprunt
                displayEmprunt.setText(item.toString());
        }

        /**
         * Appelle la methode de controleur pour supprimer l'emprunt selectionne
         */
        private void supprimerEmpruntSelectionne() {
                Emprunt e = (Emprunt) combobox.getSelectedItem();
                if (e != null) {
                        // TODO Methode à créer
                        controller.supprimerEmprunt(e);
                        modelComboBox.removeElement(e);
                }
        }

        /**
         * Classe a attribuer en listener pour la liste deroulante pour qu'a la
         * selection d'un emprunt celui ci soit affiche
         */
        private class ItemState implements ItemListener {
                @Override
                public void itemStateChanged(ItemEvent arg0) {
                        afficheEmprunt((Emprunt) arg0.getItem());
                }
        }

        /**
         * Classe a attribuer en listener au boutons ajouter des actions dans le switch
         */
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