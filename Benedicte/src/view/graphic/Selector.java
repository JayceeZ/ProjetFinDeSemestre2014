package view.graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class Selector extends JPanel {
	private JPanel boutons;
	private JButton boutonVersDroite;
	private JButton boutonVersGauche;
	
	private DefaultListModel<Object> modelGauche;
	private DefaultListModel<Object> modelDroite;
	
	private JPanel listes;
	private JList<Object> listeGauche;
	private JList<Object> listeDroite;
	
	private JScrollPane ScrollPaneGauche;
	private JScrollPane ScrollPaneDroite;
	
	public Selector(String actionListeGauche) {
		modelGauche = new DefaultListModel<Object>();
		modelDroite = new DefaultListModel<Object>();
		
		listeGauche = new JList<Object>(modelGauche);
		listeDroite = new JList<Object>(modelDroite);
		
		listeGauche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeDroite.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ScrollPaneGauche = new JScrollPane(listeGauche);
		ScrollPaneDroite = new JScrollPane(listeDroite);
			
		listes = new JPanel();		
		listes.setLayout(new GridLayout(1,2));
		listes.add(ScrollPaneGauche);
		listes.add(ScrollPaneDroite);
		
		boutons = new JPanel();
		boutons.setLayout(new GridLayout(1,2));
		boutonVersDroite = new JButton(actionListeGauche+" >>");
		boutonVersDroite.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  boutonVersDroite_actionPerformed(e);
		      }
		    });
		boutonVersGauche = new JButton("<< Retirer cet objet");
		boutonVersGauche.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  boutonVersGauche_actionPerformed(e);
		      }
		    });
		boutons.add(boutonVersDroite);
		boutons.add(boutonVersGauche);
		
		this.setLayout(new BorderLayout());
		this.add(listes, BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void boutonVersDroite_actionPerformed(ActionEvent e) {
		modelDroite.addElement(listeGauche.getSelectedValue());  // change the MODEL
		modelGauche.removeElement(listeGauche.getSelectedValue());  // change the MODEL
		listeGauche.setSelectedIndex(0);       // Highlight first item in JList
	}
	 
	public void boutonVersGauche_actionPerformed(ActionEvent e) {
		modelGauche.addElement(listeDroite.getSelectedValue());  // change the MODEL
		modelDroite.removeElement(listeDroite.getSelectedValue()); // change the MODEL
		listeDroite.setSelectedIndex(0);       // Highlight first item in JList
	}
	
	public void remplirListeGauche(Object[] liste) {
		for(int i = 0; i<liste.length; i++) {
			modelGauche.addElement(liste[i]);
		}
	}
}
