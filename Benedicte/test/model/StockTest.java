package model;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
public class StockTest {

	private Stock stock;
	private Appareil appareil1;
	private Appareil appareil2;
	private HashMap stock_liste;
	@Before
	public void before() {
		Enseignement[] matieres = {Enseignement.MAM,Enseignement.GE};
		appareil1 = new Appareil("a1",Type.SMARTPHONE,OS.ANDROID,10,matieres,12);
		appareil2 = new Appareil("a2",Type.CAMERA,OS.IOS,10,matieres,3);
		stock_liste = new HashMap<Appareil,Integer>();
		stock_liste.put(appareil1, 3);
		stock_liste.put(appareil2,10);
		stock = new Stock(stock_liste);
	}

	@After
	public void after() {
		stock = null;
	}
	@Test
	public void testStock() {
		stock = new Stock();
		assertNotNull(stock);
	}

	@Test
	public void testStockMapOfAppareilInteger() {
		assertNotNull(stock);
	}

	@Test
	public void testGetStock() {
		assertEquals(stock.getStock(),stock_liste);
	}

	@Test
	public void testAjouterAppareil() {
		Enseignement[] matieres = {Enseignement.MAM,Enseignement.GE};
		Appareil appareil3 = new Appareil("a3",Type.CAMERA,OS.IOS,10,matieres,3);	
		stock.ajouterAppareil(appareil3, 4);
		assertEquals((int)stock.getStock().get(appareil3),4);
	}

	@Test
	public void testModifierStock() {
		stock.modifierStock(appareil2, 0);
		assertEquals((int)stock.getStock().get(appareil2),0);
	}

	@Test
	public void testRetirerAppareil() {
		stock.retirerAppareil(appareil1);
		assertNull(stock.getStock().get(appareil1));
	}

}
