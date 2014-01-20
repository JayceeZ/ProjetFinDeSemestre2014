package projet.model.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projet.model.user.borrower.Student;

public class TestUser {
	User u;
	String FIRSTNAME = "Test_fn";
	String LASTNAME = "Test_ln";

	@Before
	public void setUp() throws Exception {
		u = new Student(FIRSTNAME, LASTNAME);
	}

	@Test
	public void testUser() {
		assertEquals(true, u instanceof User);
	}

	@Test
	public void testGetId() {
		assertEquals(null, u.getId());
	}

	@Test
	public void testGetFirstname() {
		assertEquals(FIRSTNAME, u.getFirstname());
	}

	@Test
	public void testGetLastname() {
		assertEquals(LASTNAME, u.getLastname());
	}

	@Test
	public void testSetFirstname() {
		u.setFirstname("test");
		assertEquals("test", u.getFirstname());
		u.setFirstname(FIRSTNAME);
	}

	@Test
	public void testSetId() {
		String actual = u.getId();
		u.setId("test");
		assertEquals("test", u.getId());
		u.setId(actual);
	}

	@Test
	public void testSetLastname() {
		u.setLastname("test");
		assertEquals("test", u.getLastname());
		u.setLastname(LASTNAME);
	}

}
