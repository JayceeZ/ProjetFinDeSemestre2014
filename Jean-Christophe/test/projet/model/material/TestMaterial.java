package projet.model.material;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projet.model.material.classic.operatingsystempowered.*;
import projet.model.os.OS;

/**
 * Unitary tests for Material
 * 
 * @author Isoard Jean-Christophe
 * 
 */
public class TestMaterial {
	Material m;
	final OS COS = OS.IOS;
	final int TIMEMAX = 20;

	@Before
	public void setUp() {
		m = new Tablette(COS, TIMEMAX);
	}

	/**
	 * Test method for {@link projet.model.material.Material#Material(int)}.
	 */
	@Test
	public void testMaterial() {
		assertEquals(true, m instanceof Material);
	}

	/**
	 * Test method for {@link projet.model.material.Material#getTimeMax()}.
	 */
	@Test
	public void testGetTimeMax() {
		assertEquals(TIMEMAX, m.getTimeMax());
	}

	/**
	 * Test method for {@link projet.model.material.Material#getReserved()}.
	 */
	@Test
	public void testGetReserved() {
		assertEquals(false, m.getReserved());
	}

	/**
	 * Test method for {@link projet.model.material.Material#getNbBorrowed()}.
	 */
	@Test
	public void testGetNbBorrowed() {
		assertEquals(0, m.getNbBorrowed());
	}

	/**
	 * Test method for {@link projet.model.material.Material#getReference()}.
	 */
	@Test
	public void testGetReference() {
		assertEquals(null, m.getReference());
	}

	/**
	 * Test method for {@link projet.model.material.Material#setNbBorrowed(int)}
	 * .
	 */
	@Test
	public void testSetNbBorrowed() {
		m.setNbBorrowed(3);
		assertEquals(3, m.getNbBorrowed());
		m.setNbBorrowed(0);
	}

	/**
	 * Test method for
	 * {@link projet.model.material.Material#setReserved(boolean)}.
	 */
	@Test
	public void testSetReserved() {
		m.setReserved(true);
		assertEquals(true, m.getReserved());
		m.setReserved(false);
	}

	/**
	 * Test method for {@link projet.model.material.Material#setTimeMax(int)}.
	 */
	@Test
	public void testSetTimeMax() {
		m.setTimeMax(0);
		assertEquals(0, m.getTimeMax());
		m.setTimeMax(TIMEMAX);
	}

	/**
	 * Test method for
	 * {@link projet.model.material.Material#setReference(java.lang.String)}.
	 */
	@Test
	public void testSetReference() {
		m.setReference("test");
		assertEquals("test", m.getReference());
		m.setReference(null);
	}

	/**
	 * Test method for {@link projet.model.material.Material#incNbBorrow()}.
	 */
	@Test
	public void testIncNbBorrow() {
		int actual = m.getNbBorrowed();
		m.incNbBorrow();
		assertEquals(actual + 1, m.getNbBorrowed());
	}

}
