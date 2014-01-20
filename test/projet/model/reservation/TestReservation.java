/**
 * 
 */
package projet.model.reservation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import projet.model.date.Date;
import projet.model.material.Material;
import projet.model.material.classic.Camera;
import projet.model.material.classic.operatingsystempowered.Phone;
import projet.model.material.classic.operatingsystempowered.Tablette;
import projet.model.os.OS;
import projet.model.state.State;

/**
 * @author Isoard Jean-Christophe
 * 
 */
public class TestReservation {
	Reservation r;
	ArrayList<Material> alm;
	Phone tphone;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		alm = new ArrayList<Material>();
		tphone = new Phone(OS.IOS);
		alm.add(new Camera());
		alm.add(tphone);
		alm.add(new Tablette(OS.Android));
		r = new Reservation(alm);
	}

	/**
	 * Test method for {@link projet.model.reservation.Reservation#finalize()}.
	 */
	@Test
	public void testFinalize() {
		r.finalize();
		assertEquals(false, r.getMaterial(2).getReserved());
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#reservedMaterial()}.
	 */
	@Test
	public void testSetReservedMaterials() {
		r.setReservedMaterials();
		assertEquals(true, r.getMaterial(2).getReserved());
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#setMaterials(java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testSetMaterials() {
		r.setMaterials(null);
		assertEquals(null, r.getMaterials());
		r.setMaterials(alm);
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#getMaterials()}.
	 */
	@Test
	public void testGetMaterials() {
		assertEquals(alm, r.getMaterials());
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#setMaterial(projet.model.material.Material, int)}
	 * .
	 */
	@Test
	public void testSetMaterial() {
		Material o = new Camera();
		r.setMaterial(2, o);
		assertEquals(o, r.getMaterial(2));
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#getMaterial(int)}.
	 */
	@Test
	public void testGetMaterialInt() {
		assertEquals(tphone, r.getMaterial(1));
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#setDateStart(projet.model.date.Date)}
	 * .
	 */
	@Test
	public void testSetDateStart() {
		Date d = new Date();
		r.setDateStart(d);
		assertEquals(d, r.getDateStart());
		r.setDateStart(null);
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#setState(projet.model.state.State)}
	 * .
	 */
	@Test
	public void testSetState() {
		r.setState(State.Accept);
		assertEquals(State.Accept, r.getState());
	}

	/**
	 * Test method for
	 * {@link projet.model.reservation.Reservation#setDateEnd(projet.model.date.Date)}
	 * .
	 */
	@Test
	public void testSetDateEnd() {
		Date actuel = r.getDateEnd();
		r.setDateEnd(null);
		assertEquals(null, r.getDateEnd());
		r.setDateEnd(actuel);
	}

	/**
	 * Test method for {@link projet.model.reservation.Reservation#isWaiting()}.
	 */
	@Test
	public void testIsWaiting() {
		assertEquals(State.Wait, r.getState());
	}
}
