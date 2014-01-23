package model;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
public class EmpruntTest {

	private Emprunt emprunt;
	private Appareil appareil1;
	private Appareil appareil2;
	private Calendar debut;
	private Calendar fin;
	private HashMap emprunt_liste;
	private Emprunteur emprunteur;

	@Before
	public void before(){
		Enseignement[] matieres = {Enseignement.MAM,Enseignement.GE};
		appareil1 = new Appareil("a1",Type.SMARTPHONE,OS.ANDROID,10,matieres,12);
		appareil2 = new Appareil("a2",Type.CAMERA,OS.IOS,10,matieres,3);
		emprunt_liste = new HashMap<Appareil,Integer>();
		debut = Calendar.getInstance();
		debut.set(2013, 12, 25);
		fin = Calendar.getInstance();
		fin.set(2013,12,30);
		Enseignement[] enseignement = {Enseignement.SI,Enseignement.ELEC};
		emprunteur = new Enseignant("Kennedy",1,1,1,enseignement);

		emprunt = new Emprunt(emprunt_liste,debut,fin,emprunteur, 0);
		emprunt.addAppareil(appareil2, 10);
	}

	@After
	public void after() {
		emprunt=null;
		appareil1=null;
		appareil2=null;
		debut=null;
		fin=null;
		emprunteur = null;
	}

	@Test
	public void testEmprunt() {
		emprunt = new Emprunt();
		assertNotNull(emprunt);
	}

	@Test
	public void testEmpruntHashMapOfAppareilIntegerCalendarCalendarEmprunteur() {
		assertNotNull(emprunt);
	}

	@Test
	public void testGetDateDebut() {
		assertEquals(emprunt.getDateDebut(),debut);
	}

	@Test
	public void testSetDateDebut() {
		Calendar new_debut = Calendar.getInstance();
		new_debut.set(2013, 12, 1);
		emprunt.setDateDebut(new_debut);
		assertEquals(emprunt.getDateDebut(),new_debut);
	}

	@Test
	public void testGetDateFin() {
		assertEquals(emprunt.getDateFin(),fin);
	}

	@Test
	public void testSetDateFin() {
		Calendar new_fin = Calendar.getInstance();
		new_fin.set(2013, 12, 1);
		emprunt.setDateFin(new_fin);
		assertEquals(emprunt.getDateFin(),new_fin);
	}

	@Test
	public void testGetEmprunteur() {
		assertEquals(emprunt.getEmprunteur(),emprunteur);
	}

	@Test
	public void testSetEmprunteur() {
		Enseignant enseignant = new Enseignant();
		emprunt.setEmprunteur(enseignant);
		assertEquals(emprunt.getEmprunteur(),enseignant);
	}

	@Test
	public void testGetEmprunte() {
		assertNotNull(emprunt.getEmprunte());
		assertEquals(emprunt.getEmprunte(),emprunt_liste);
	}

	@Test
	public void testSetEmprunte() {
		HashMap<Appareil,Integer> new_emprunt_liste = new HashMap<Appareil,Integer>();
		emprunt.setEmprunte(new_emprunt_liste);
		assertEquals(emprunt.getEmprunte(),new_emprunt_liste);
	}

	@Test
	public void testAddAppareil() {
		emprunt.addAppareil(appareil1,2);
		assertEquals((int)emprunt.getEmprunte().get(appareil1),2);
	}

	@Test
	public void testModifierEmprunte() {
		emprunt.modifierEmprunte(appareil2, 1);
		assertEquals((int)emprunt.getEmprunte().get(appareil2),1);
	}

	@Test
	public void testRetirerAppareil() {
		emprunt.retirerAppareil(appareil2);
		assertNull(emprunt.getEmprunte().get(appareil2));
	}
}
