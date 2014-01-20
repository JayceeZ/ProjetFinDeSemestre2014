package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.Calendar;
import java.util.HashMap;
//TODO
//comment qu'on fait le test d'une méthode privée? possible, ou pas?....
//si pas possible, comment tester "efficacement" la méthode accept?
public class GestionnaireTest {
	private Systeme systeme;
	private Stock stock;
	private Appareil appareil1;
	private Appareil appareil2;
	private Appareil appareil3;
	private Emprunteur emprunteur;
	private Emprunt emprunt_en_cours;

	@Before
	public void before() {
		Enseignement[] matieres = {Enseignement.SI,Enseignement.BAT};
		emprunteur = new Enseignant("Bush",10,1,1000,matieres);
		appareil1 = new Appareil("app1", Type.CAMERA, OS.ANDROID, 10000,matieres,12);
		appareil2 = new Appareil("app2", Type.CAMERA, OS.ANDROID, 10000,matieres,12);
		appareil3 = new Appareil("app3", Type.CAMERA, OS.ANDROID, 10000,matieres,12);

		HashMap stock_liste = new HashMap<Appareil,Integer>();
		stock_liste.put(appareil1, 3);
		stock_liste.put(appareil2,10);
		stock_liste.put(appareil3, 1);
		stock = new Stock(stock_liste);

		HashMap<Appareil,Integer> emprunt_liste = new HashMap<Appareil,Integer>();
		emprunt_liste.put(appareil1, 2);
		Calendar debut = Calendar.getInstance();
		debut.set(2013, 12,16);
		Calendar fin =  Calendar.getInstance();
		fin.set(2013,12,30);
		emprunt_en_cours = new Emprunt(emprunt_liste,debut,fin,emprunteur, 0);

		systeme = new Systeme(stock);
		systeme.setEmpruntEnCours(emprunt_en_cours);
		systeme.getEmpruntEnCours().addAppareil(appareil1, 1);
	}

	@Test
	public void testGestionnaire() {
		systeme = new Systeme();
		assertNotNull(systeme);
	}

	@Test
	public void testGestionnaireStock() {
		assertNotNull(systeme);
	}

	@Test
	public void testGetEmpruntEnCours() {
		assertNotNull(systeme.getEmpruntEnCours());
		assertEquals(systeme.getEmpruntEnCours(),emprunt_en_cours);
	}

	@Test
	public void testSetEmpruntEnCours() {
		HashMap<Appareil,Integer> emprunt_liste = new HashMap<Appareil,Integer>();
		Calendar debut = Calendar.getInstance();
		Calendar fin =  Calendar.getInstance();
		Emprunt new_emprunt_en_cours = new Emprunt(emprunt_liste,debut,fin,emprunteur, 0);
		new_emprunt_en_cours.addAppareil(appareil2, 1);
		systeme.setEmpruntEnCours(new_emprunt_en_cours);
		assertEquals(systeme.getEmpruntEnCours(),new_emprunt_en_cours);
	}

	@Test
	public void testVerifDonneesRentrees() {
		assertFalse(systeme.verifDonneesRentrees(emprunt_en_cours));
	}

	@Test
	public void testVerifDurees() {
		assertTrue(systeme.verifDurees(emprunt_en_cours));
	}

	@Test
	public void testVerifMatiere() {
		assertTrue(systeme.verifMatiere(emprunt_en_cours));
	}

	@Test
	public void testVerifEtudiant() {
		assertTrue(systeme.verifEtudiant(emprunt_en_cours.getDateDebut()));
	}

	@Test
	public void testEmprunt() {
		Stock stock1 = stock;
		systeme.emprunt(emprunt_en_cours);
		assertEquals(stock1,stock);
	}

	@Test
	public void testTestDateDebut() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestDateFin() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestAjoutAppareil() {
		fail("Not yet implemented");
	}

}
