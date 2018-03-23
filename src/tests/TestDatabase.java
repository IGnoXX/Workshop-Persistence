package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import dblayer.DBConnection;

public class TestDatabase {

	private DBConnection con;
	
	@BeforeClass
	public void getInstanceOfDBConnection() {
		con = DBConnection.getInstance();
	}
	
	@Test
	public void testDatabaseConnection() {
		assertNotNull("Connected - connection cannot be null", con);
	}

}
