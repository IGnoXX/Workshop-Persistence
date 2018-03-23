package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import dblayer.DBConnection;

public class TestDatabase {

	DBConnection dbCon = DBConnection.getInstance();
	
	@Test
	public void testDatabaseConnection() {
		assertNotNull("Connected - connection cannot be null", dbCon);
	}

}
