package view.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.StockProjectController;

@SuppressWarnings("serial")
public class Window extends JFrame {
	// Reference vers le controleur
	private StockProjectController controller;
	private String mode;
	
	private JPanel panel;

	public Window() {
		this.setLayout(new BorderLayout());

		this.add(new Connexion(this), BorderLayout.CENTER);

		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void changePanel(JPanel panel) {
		this.removeAll();
		this.panel = panel;
		this.add(panel, BorderLayout.CENTER);
		this.revalidate();
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
		changePanel(panel);
	}

	public void setMode(String string) {
		this.mode = string;
	}
}
