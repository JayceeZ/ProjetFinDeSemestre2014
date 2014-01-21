package view.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.StockProjectController;

public class Window extends JFrame {
	// Reference vers le controleur
	private StockProjectController controller;

	public Window(Dimension d) {
		this.setLayout(new BorderLayout());

		//this.add(new Selector("Testing ...", l), BorderLayout.CENTER);

		this.setSize(d);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void setController(StockProjectController c) {
		this.controller = c;
	}
}
