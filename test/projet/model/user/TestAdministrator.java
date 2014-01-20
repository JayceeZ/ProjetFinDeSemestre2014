/**
 * 
 */
package projet.model.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projet.model.material.classic.Camera;
import projet.model.reservation.Reservation;
import projet.model.user.administrator.Administrator;

/**
 * @author user
 * 
 */
public class TestAdministrator {
	Administrator a;
	Reservation rtest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rtest = new Reservation(new Camera());
		a = new Administrator(null, null);
	}

	/**
	 * Test method for
	 * {@link projet.model.user.administrator.Administrator#Administrator(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAdministrator() {
		assertEquals(true, a instanceof Administrator);
	}

	/**
	 * Test method for
	 * {@link projet.model.user.administrator.Administrator#valide(projet.model.reservation.Reservation)}
	 * .
	 */
	@Test
	public void testValide() {
		a.valide(rtest);
		assertEquals(false, rtest.isWaiting());
	}

	/**
	 * Test method for
	 * {@link projet.model.user.administrator.Administrator#delete(projet.model.reservation.Reservation)}
	 * .
	 */
	@Test
	public void testDelete() {
		a.delete(rtest);
	}

}
