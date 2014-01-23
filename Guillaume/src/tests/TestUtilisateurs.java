package tests;

import utilisateur.Utilisateurs;

public class TestUtilisateurs {

	public static void main(String[] args) 
	{
		Utilisateurs test = new Utilisateurs();
		System.out.println(test);
		
		test.ajouterEmprunteur("test", "test");
		System.out.println(test);
		test.ajouterEmprunteur("test","test");
		System.out.println(test);
		
		System.out.println(test.verifierIdentifiants("Admin", "Admin"));
		
		test.afficherEmprunteurs();
	}

}
