package view.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
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
		
		erreur = new JLabel("Entrez vos identifiants");
		texts.add(erreur);
		
		this.setLayout(new FlowLayout());
		this.add(erreur);
		this.add(texts);
		this.add(valide);
		this.setPreferredSize(new Dimension(240,150));
	}
	
	private void valider_formulaire() {
		String nomUtilisateur = "";
		String motDePasse = "";
		try {
			nomUtilisateur = login.getText();
			motDePasse = String.valueOf(psw.getPassword());
		} catch(Exception e) {
			System.err.println(e);
		} finally {
		
			if(nomUtilisateur == null) {
				connexionError("Veuillez remplir les champs");
			} else {
				if(motDePasse == null) {
					motDePasse = "";
				}
				if(!parent.connect(nomUtilisateur,motDePasse)) {
 					connexionError("Identifiants incorrects");
 				}

			}
		}
	}

	private void connexionError(String string) {
		erreur.setForeground(Color.red);
		erreur.setText(string);
	}
}
