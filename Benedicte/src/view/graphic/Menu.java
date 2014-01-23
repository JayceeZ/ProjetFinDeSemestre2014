package view.graphic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel {	
	public Menu(JFrame parent, String title, Map<String,ActionListener> boutonsActions) {
		parent.setTitle(title);
		this.setLayout(new FlowLayout());
		
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
