package Gestion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Personnel.Administrator;
import Personnel.Personnel;

public class DatabaseTest {

	private Database database;

	@Before
	public void setUp() throws Exception {
		this.database = new Database();
	}

	@After
	public void tearDown() throws Exception {
		this.database = null;
	}

	@Test
	public void isAuthorizedTest() {
		assertTrue(database.isAuthorized("root@root.com", "root"));
		assertFalse(database.isAuthorized("root@root.com", "faux_mot_de_passe"));
	}

	@Test
	public void addTest() {
		Personnel personnel = new Administrator(" ", " ", " ", " ", 7);
		assertTrue(this.database.addUser(personnel));
	}
}
