/**
 * 
 */
package projet.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import projet.model.date.Date;
import projet.model.material.Material;
import projet.model.material.classic.Camera;
import projet.model.material.classic.Headphone;
import projet.model.material.classic.operatingsystempowered.Phone;
import projet.model.material.classic.operatingsystempowered.Tablette;
import projet.model.os.OS;
import projet.model.reservation.Reservation;
import projet.model.user.User;
import projet.model.user.borrower.Student;
import projet.model.user.borrower.Teacher;

/**
 * @author user
 * 
 */
public class TestSimulator {
	Simulator s;
	ReferenceGenerator rg;
	ArrayList<User> u;
	ArrayList<Material> m;

	@Before
	public void setUp() {
		rg = new ReferenceGenerator();
		u = new ArrayList<User>();
		u.add(new Student("Student", "Alone"));
		u.add(new Teacher("Teacher", "Alone"));
		u.get(1).setId("test");
		m = new ArrayList<Material>();
		m.add(new Camera());
		m.add(new Phone(OS.Android));
		m.add(new Tablette(OS.IOS));
		m.add(new Headphone());

		s = new Simulator(rg, u, m);
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#Simulator(projet.controller.ReferenceGenerator, java.util.ArrayList, java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testSimulator() {
		assertEquals(true, s instanceof Simulator);
	}

	/**
	 * Test method for {@link projet.controller.Simulator#getUsers()}.
	 */
	@Test
	public void testGetUsers() {
		assertEquals(u, s.getUsers());
	}

	/**
	 * Test method for {@link projet.controller.Simulator#getMaterials()}.
	 */
	@Test
	public void testGetMaterials() {
		assertEquals(m, s.getMaterials());
	}

	/**
	 * Test method for {@link projet.controller.Simulator#getUsers(int)}.
	 */
	@Test
	public void testGetUsersInt() {
		assertEquals(u.get(0), s.getUser(0));
	}

	/**
	 * Test method for {@link projet.controller.Simulator#getMaterials(int)}.
	 */
	@Test
	public void testGetMaterialsInt() {
		assertEquals(m.get(0), s.getMaterials(0));
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#makeReservation(projet.model.user.borrower.Borrower, java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testMakeReservationBorrowerArrayListOfMaterial() {
		s.makeReservation((Student) u.get(0), m);
		assertEquals(true, m.get(0).getReserved());
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#makeReservation(projet.model.user.borrower.Borrower, java.util.ArrayList, int)}
	 * .
	 */
	@Test
	public void testMakeReservationBorrowerArrayListOfMaterialInt() {
		s.makeReservation((Student) u.get(0), m, 4);
		assertEquals(new Date(4), ((Student) u.get(0)).getReservations(0)
				.getDateStart());
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#allIsBorrowing(java.util.ArrayList)}.
	 */
	@Test
	public void testAllIsBorrowing() {
		assertEquals(true, s.allIsBorrowing(m));
	}

	/**
	 * Test method for {@link projet.controller.Simulator#createUseUser(int)}.
	 */
	@Test
	public void testCreateUseUser() {
		System.out
				.println("Please answer \"test\" to the following questions:");
		assertEquals(true, s.createUseUser(0));
		assertEquals("test", s.getUser(2).getFirstname());
		assertEquals("test", s.getUser(2).getLastname());
	}

	/**
	 * Test method for {@link projet.controller.Simulator#getNotReservedItem()}.
	 */
	@Test
	public void testGetNotReservedItem() {
		assertEquals(m, s.getNotReservedItem());
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#getWaitingReservations()}.
	 */
	@Test
	public void testGetWaitingReservations() {
		assertEquals(new ArrayList(), s.getWaitingReservations());
	}

	/**
	 * Test method for {@link projet.controller.Simulator#getAllReservations()}.
	 */
	@Test
	public void testGetAllReservations() {
		assertEquals(new ArrayList(), s.getAllReservations());
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#getListChooseIntoList(java.lang.String, java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testGetListChooseIntoList() {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("Test !");
		strings.add("Don't test !");
		System.out.println("Answer 1 then 0 for good testing.");
		assertEquals(strings.get(0),
				s.getListChooseIntoList("^^^^ PLEASE ^^^^", strings).get(0));
	}

	/**
	 * Test method for {@link projet.controller.Simulator#getOnlyBorrower()}.
	 */
	@Test
	public void testGetOnlyBorrower() {
		assertEquals(u, s.getOnlyBorrower());
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#whoHasThisReserv(projet.model.reservation.Reservation)}
	 * .
	 */
	@Test
	public void testWhoHasThisReserv() {
		assertEquals(null, s.whoHasThisReserv(new Reservation(m)));
	}

	/**
	 * Test method for
	 * {@link projet.controller.Simulator#getUserById(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetUserById() {
		assertEquals(u.get(1), s.getUserById("test"));
	}

	/**
	 * Test method for {@link projet.controller.Simulator#changeUser()}.
	 */
	@Test
	public void testChangeUser() {
		System.out.println("Please enter \"test\":");
		assertEquals(false, s.changeUser());
	}

	/**
	 * Test method for {@link projet.controller.Simulator#Simulate()}.
	 */
	@After
	public void testSimulate() {
		System.out.println("Have fun !");
		s.Simulate();
	}

}
