package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class EnseignantTest {
	Enseignant enseignant;

	@Before
	public void before() {
		Enseignement[] matieres = {Enseignement.SI,Enseignement.ELEC};
		enseignant = new Enseignant("Freddy",10,1,3,matieres);
	}

	@After
	public void after() {
		enseignant = null;
	}
	@Test
	public void testEnseignant() {
		enseignant = new Enseignant();
		assertNotNull(enseignant);
	}

	@Test
	public void testEnseignantStringIntIntIntEnseignementArray() {
		assertNotNull(enseignant);
	}

	@Test
	public void testGetNom() {
		assertEquals(enseignant.getNom(),"Freddy");
	}

	@Test
	public void testSetNom() {
		enseignant.setNom("Hannibal");
		assertEquals(enseignant.getNom(),"Hannibal");
		
	}

	@Test
	public void testGetDureeMaxEmprunt() {
	assertEquals(enseignant.getDureeMaxEmprunt(),10);	
	}

	@Test
	public void testSetDureeMaxEmprunt() {
		enseignant.setDureeMaxEmprunt(1);
		assertEquals(enseignant.getDureeMaxEmprunt(),1);
	}

	@Test
	public void testGetId() {
		assertEquals(enseignant.getId(),1);
	}

	@Test
	public void testSetId() {
		enseignant.setId(2);
		assertEquals(enseignant.getId(),2);
	}

	@Test
	public void testGetNbMaxMateriel() {
		assertEquals(enseignant.getNbMaxMateriel(),3);
	}

	@Test
	public void testSetNbMaxMateriel() {
		enseignant.setNbMaxMateriel(4);
		assertEquals(enseignant.getNbMaxMateriel(),4);
	}

	@Test
	public void testGetMatieres() {
		Enseignement[] matiere = {Enseignement.SI,Enseignement.ELEC};
		assertEquals(matiere,enseignant.getMatieres());
	}

	@Test
	public void testSetMatieres() {
		Enseignement[] matiere = {Enseignement.SI};
		enseignant.setMatieres(matiere);
		assertEquals(matiere,enseignant.getMatieres());
	}

}
