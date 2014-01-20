package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
public class DatabaseTest {
	Database database;

	@Before
	public void before() {
		database = new Database();
	}
	@After
	public void after(){
		database = null;
	}
	
	@Test
	public void testDatabase() {
	assertNotNull(database);
	}
}
