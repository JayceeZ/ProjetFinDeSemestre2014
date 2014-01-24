package view.graphic;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Cree un menu base sur un ensemble de chaines associes a des ActionListener
 * @author Isoard Jean-Christophe
 *
 */
@SuppressWarnings("serial")
public class Menu extends JPanel {	
	/**
	 * Construit un menu en ajoutant des boutons avec les chaines et les ActionListener<br />
	 * Contenus dans un Map<String,ActionListener>
	 * @param parent La fenetre parent, pour les dimensions
	 * @param boutonsActions Map de String et d'ActionListener pour les boutons
	 */
	public Menu(JFrame parent, Map<String,ActionListener> boutonsActions) {
		this.setLayout(new GridLayout(boutonsActions.size(),1));
		
		for(String bouton:boutonsActions.keySet()) {
			JButton newbutton = new JButton(bouton);
			newbutton.addActionListener(boutonsActions.get(bouton));
			this.add(newbutton);
		}
		
		Dimension preferredSize = new Dimension(200,boutonsActions.size()*50);
		parent.setSize(preferredSize);

		this.setVisible(true);
	}
}
