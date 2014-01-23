package view.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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
	
	private JDatePicker datePickerStart;
	private JDatePicker datePickerEnd;
	private JPanel dates;
	private JLabel datesLabel;
	
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
		
		dates = new JPanel();
		dates.setLayout(new GridLayout(1,3));
		datesLabel = new JLabel("Entrez la date de debut, suivie de la date de fin :");
		datePickerStart = JDateComponentFactory.createJDatePicker(new UtilCalendarModel(new GregorianCalendar()));
		((Component) datePickerStart).setSize(new Dimension(100, ((Component) datePickerStart).getHeight()));
		datePickerEnd = JDateComponentFactory.createJDatePicker(new UtilCalendarModel(new GregorianCalendar()));
		((Component) datePickerEnd).setSize(new Dimension(100, ((Component) datePickerEnd).getHeight()));
		dates.add(datesLabel);
		dates.add((Component) datePickerStart);
		dates.add((Component) datePickerEnd);
		
		this.setLayout(new BorderLayout());
		this.add(dates, BorderLayout.NORTH);
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
	
	public void setDateText(String error) {
		datesLabel.setForeground(Color.RED);
		this.datesLabel.setText(error);
	}
	
	public Calendar getDateStart() {
		return (Calendar) datePickerStart.getModel().getValue();
	}
	
	public Calendar getDateEnd() {
		return (Calendar) datePickerEnd.getModel().getValue();
	}
	
	public Object[] getSelected() {
		return modelDroite.toArray();
	}
}
