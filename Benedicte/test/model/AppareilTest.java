package model;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
public class AppareilTest {

	Appareil appareil1;
	Appareil appareil2;

	@Before
	public void before() {
		Enseignement[] matieres= {Enseignement.SI,Enseignement.ELEC,Enseignement.GE};
		Map<String,Object> configuration = new HashMap<String,Object>();
		configuration.put("Id",(Object)13);
		configuration.put("Nom", (Object)"a2");
		configuration.put("Type", (Object)Type.CAMERA);
		configuration.put("OS", (Object)OS.ANDROID);
		configuration.put("DureeEmpruntMax", (Object)10);
		configuration.put("Matieres", (Object) matieres);
		appareil1 = new Appareil("a1",Type.SMARTPHONE,OS.ANDROID,10,matieres,12);
		appareil2 = new Appareil(configuration);
	}

	@After
	public void after() {
		appareil1 = null;
	}
	@Test
	public void testAppareil() {
		appareil1 = new Appareil();
		assertNotNull(appareil1);
	}

	@Test
	public void testAppareilMapOfStringObject() {
		assertNotNull(appareil2);
	}

	@Test
	public void testAppareilStringTypeOSIntEnseignementArrayInt() {
		assertNotNull(appareil1);		
	}

	@Test
	public void testGetId() {
assertEquals(appareil2.getId(),13);
	}

	@Test
	public void testSetId() {
appareil2.setId(3);
assertEquals(appareil2.getId(),3);
	}

	@Test
	public void testGetNom() {
assertEquals(appareil2.getNom(),"a2");
	}

	@Test
	public void testSetNom() {
		appareil2.setNom("app2");
		assertEquals(appareil2.getNom(),"app2");
	}

	@Test
	public void testGetType() {
assertEquals(appareil2.getType(),Type.CAMERA);
	}

	@Test
	public void testSetType() {
appareil2.setType(Type.SMARTPHONE);
assertEquals(appareil2.getType(),Type.SMARTPHONE);
	}

	@Test
	public void testGetOs() {
		assertEquals(appareil2.getOs(),OS.ANDROID);
	}

	@Test
	public void testSetOs() {
		appareil2.setOs(OS.IOS);
		assertEquals(appareil2.getOs(),OS.IOS);
	}

	@Test
	public void testGetDureeEmpruntMax() {
assertEquals(appareil2.getDureeEmpruntMax(),10);
	}

	@Test
	public void testSetDureeEmpruntMax() {
		appareil2.setDureeEmpruntMax(1);
		assertEquals(appareil2.getDureeEmpruntMax(),1);
	}

	@Test
	public void testGetMatieresAssociee() {
		Enseignement[] matiere1 = {Enseignement.SI,Enseignement.ELEC,Enseignement.GE};
	assertEquals(appareil2.getMatieresAssociee(),matiere1);
	
	}

	@Test
	public void testSetMatieresAssociee() {
		Enseignement[] matiere1 = {Enseignement.SI,Enseignement.ELEC};
		appareil2.setMatieresAssociee(matiere1);
		assertEquals(appareil2.getMatieresAssociee(),matiere1);
	}

	@Test
	public void testToString() {
		assertEquals(appareil2.toString(),"13  | a2 | ");
	}

}
