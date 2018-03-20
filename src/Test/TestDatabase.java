package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import dblayer.DbConnection;

public class TestDatabase {

	DbConnection dbCon = DbConnection.getInstance();
	
	@Test
	public void testDatabaseConnection() {
		assertNotNull("Connected - connection cannot be null", dbCon);
	}

}
