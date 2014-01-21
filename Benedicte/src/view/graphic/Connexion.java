package view.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Connexion extends JPanel {
	Window parent;
	
	JLabel erreur;
	
	JPanel texts;
	JLabel username;
	JLabel password;
	JTextField login;
	JPasswordField psw;
	
	JButton valide;
	
	public Connexion(Window parent) {
		this.parent = parent;
		parent.setTitle("Veuillez vous authentifier");
		
		username = new JLabel("Nom d'utilisateur: ");
		password = new JLabel("Mot de passe: ");
		
		login = new JTextField();
		psw = new JPasswordField();
		
		valide = new JButton("Connexion");
		valide.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  valider_formulaire();
		      }
		    });
		
		texts = new JPanel();
		texts.setLayout(new GridLayout(2,2));
		texts.add(username);
		texts.add(login);
		texts.add(password);
		texts.add(psw);
		
		this.setLayout(new BorderLayout());
		this.add(texts,BorderLayout.CENTER);
		this.add(valide,BorderLayout.EAST);
		
		this.setVisible(true);
	}
	
	private void valider_formulaire() {
		String nomUtilisateur = "";
		String motDePasse = "";
		try {
			nomUtilisateur = this.login.getText();
			motDePasse = this.psw.getPassword().toString();
		} finally {
		
			if(nomUtilisateur == null) {
				connexionError("Veuillez remplir les champs");
			} else {
				if(motDePasse == null) {
					motDePasse = "";
				}
				switch(parent.getController().connect(nomUtilisateur,motDePasse)) {
				case 1:
					parent.setMenu("emprunteur");
					break;
				case 2:
					parent.setMenu("gestionnaire");
					break;
				default:
					connexionError("Identifiants incorrects");
				}
			}
		}
	}

	private void connexionError(String string) {
		if(erreur != null) {
			erreur.setText(string);
		} else {	
			erreur = new JLabel(string);
			erreur.setForeground(Color.red);
			this.add(erreur, BorderLayout.NORTH);
		}
		parent.revalidate();
		parent.pack();
	}
}
