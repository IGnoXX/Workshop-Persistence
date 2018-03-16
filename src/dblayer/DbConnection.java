package dblayer;

import java.sql.*;

public class DbConnection
{   //Constants used to get access to the database
	//SQL Server
	//private static final String  driver = "jdbc:sqlserver://kraka.ucn.dk:1433";
	//private static final String  driver = "jdbc:sqlserver://localhost:1433";
	//private static final String  driver = "jdbc:sqlserver://127.0.0.1:1433";
	//NO152020\SQLEXPRESS
   //private static final String  databaseName = ";databaseName=dmaa0914_2Sem_8";
    //SQL Server
   //private static String  userName = ";user=dmaa0914_2Sem_8";
   //private static String  userName = "Trusted_Connection=yes";
   //private static String  userName = ";user=company";
    //private static String password = ";password=IsAllowed";
	//String url = driver + databaseName + userName + password;
	//String url = "jdbc:sqlserver://localhost:1433;user=sa;password=**********;databaseName=Company";
	//String url = "jdbc:sqlserver://localhost:1433;user=CompanyAdmin;password=CompanyAdmin;databaseName=Company";
	//String url = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;databaseName=Company";
   
    private DatabaseMetaData dma;
    private static Connection con;
    private static DbConnection  instance = null;

    private DbConnection()
    {
    	String url = "jdbc:sqlserver://kraka.ucn.dk:1433;user=dmaj0917_1067624;password=Password1!;databaseName=dmaj0917_1067624";
    	
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            dma = con.getMetaData();
            System.out.println("Connection to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Database product name " + dma.getDatabaseProductName());
        }
        catch(Exception e){

            System.out.println("Problems with the connection to the database");
            System.out.println(e.getMessage());
            System.out.println(url);
        }
    }
	   
    public static void closeConnection()
    {
       	try{
            con.close();
            System.out.println("The connection is closed");
        }
         catch (Exception e){
            System.out.println("Error trying to close the database " +  e.getMessage());
         }
    }
    
    public  Connection getDBcon()
    {
       return con;
    }
    
    public static DbConnection getInstance()
    {
        if (instance == null)
        {
          instance = new DbConnection();
        }
        return instance;
    }
    public static void startTransaction()
    { try{
        con.setAutoCommit(false);
        }
      catch(Exception e){
        System.out.println("fejl start transaction");
        System.out.println(e.getMessage());
      }
    }
    public static void commitTransaction()
    { try{
        con.setAutoCommit(true);
        }
      catch(Exception e){
        System.out.println("fejl commit transaction");
        System.out.println(e.getMessage());
      }
    }
    public static void rollbackTransaction()
    { try{
        con.rollback();
        con.setAutoCommit(true);
        }
      catch(Exception e){
        System.out.println("fejl rollback transaction");
        System.out.println(e.getMessage());
      }
    }
}
