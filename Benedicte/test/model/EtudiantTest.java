package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
public class EtudiantTest {
	Etudiant etudiant ;
	
	@Before
	public void before() {
		Enseignement[] m = {Enseignement.SI};
		etudiant = new Etudiant("L'oncle ernest",10,1,1,m);		
	}

	@After
	public void after(){
		etudiant = null;
	}
	@Test
	public void testEtudiant() {
		etudiant = new Etudiant();
		assertNotNull(etudiant);
	}

	@Test
	public void testEtudiantStringIntIntIntEnseignementArray() {
		assertNotNull(etudiant);
	}

}
