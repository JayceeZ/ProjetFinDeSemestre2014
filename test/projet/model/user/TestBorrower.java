/**
 * 
 */
package projet.model.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projet.model.material.classic.operatingsystempowered.Phone;
import projet.model.os.OS;
import projet.model.reservation.Reservation;
import projet.model.state.State;
import projet.model.user.borrower.Borrower;
import projet.model.user.borrower.Student;

/**
 * @author Isoard Jean-Christophe
 * 
 */
public class TestBorrower {
	Borrower b;
	Reservation rtest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rtest = new Reservation(new Phone(OS.Android));
		b = new Student(null, null);
		b.addReservations(rtest);
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#Borrower(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testBorrower() {
		assertEquals(true, b instanceof Borrower);
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#getReservations()}.
	 */
	@Test
	public void testGetReservations() {
		assertEquals(true, b.getReservations().contains(rtest));
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#getReservations(int)}.
	 */
	@Test
	public void testGetReservationsInt() {
		assertEquals(rtest, b.getReservations(0));
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#setReservations(int, projet.model.reservation.Reservation)}
	 * .
	 */
	@Test
	public void testSetReservations() {
		b.setReservations(0, null);
		assertEquals(null, b.getReservations(0));
		b.setReservations(0, rtest);
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#addReservations(projet.model.reservation.Reservation)}
	 * .
	 */
	@Test
	public void testAddReservations() {
		b.addReservations(rtest);
		assertEquals(true, b.getReservations().contains(rtest));
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#subReservations(int)}.
	 */
	@Test
	public void testSubReservationsInt() {
		Object actual = b.getReservations(0);
		b.subReservations(0);
		assertEquals(false, b.getReservations().contains(actual));
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#subReservations(projet.model.reservation.Reservation)}
	 * .
	 */
	@Test
	public void testSubReservationsReservation() {
		b.subReservations(rtest);
		assertEquals(false, b.getReservations().contains(rtest));
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#getWaitingReservations()}.
	 */
	@Test
	public void testGetWaitingReservations() {
		assertEquals(true, b.getWaitingReservations().contains(rtest));
		rtest.setState(State.Accept);
		assertEquals(false, b.getWaitingReservations().contains(rtest));
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#isMyReservation(projet.model.reservation.Reservation)}
	 * .
	 */
	@Test
	public void testIsMyReservation() {
		assertEquals(true, b.isMyReservation(rtest));
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#getTimeLimitReserved()}.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetTimeLimitReserved() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		int v = b.getClass().getField("timeLimitReserved").getInt(b);
		assertEquals(v, b.getTimeLimitReserved());
	}

	/**
	 * Test method for
	 * {@link projet.model.user.borrower.Borrower#getTimeLimit()}.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetTimeLimit() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		int v = b.getClass().getField("timeLimit").getInt(b);
		assertEquals(v, b.getTimeLimit());
	}

}
