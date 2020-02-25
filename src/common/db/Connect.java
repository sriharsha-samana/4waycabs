package common.db;

import java.util.HashMap;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect 
{
	
	//STATIC VARIABLES
		static Connection con = null;
	
	public static Connection getConnection()  
	{	// getConnection()
		
		try
		{
			if(con==null || con.isClosed())
			{ // IF CON IS NULL
				
				if(Config.PROVIDER.equalsIgnoreCase("MS-SQL") || 
						Config.PROVIDER.equalsIgnoreCase("MSSQL") )
				{ // IF MSSQL
					
					String CONNECTIONURL = "jdbc:" + "sqlserver://" 
											+ Config.DATABASEHOST
											+ ":" + Config.DATABASEPORT
											+ ";DatabaseName=" + Config.DATABASENAME;
							
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					// Class.forName not required as long as the sqljdbc4.jar is present in ClassPath.
					
					con = DriverManager.getConnection(CONNECTIONURL, 
	          				Config.USERNAME,
	          				Config.PASSWORD);
					
				} // ENDS IF MSSQL
				else if(Config.PROVIDER.equalsIgnoreCase("MY-SQL") || 
						Config.PROVIDER.equalsIgnoreCase("MYSQL") )
					{	// ELSE IF MYSQL
					
					String CONNECTIONURL = "jdbc:" + "mysql" + "://"
											+ Config.DATABASEHOST 
											+ ":" + Config.DATABASEPORT + "/" 
											+ Config.DATABASENAME;
					
					Class.forName(Config.DATABASEDRIVER);
					
					con = DriverManager.getConnection(CONNECTIONURL, 
							Config.USERNAME,
							Config.PASSWORD);
					
				} // ENDS ELSE IF MYSQL
		
				if(!checkConnection(con))
				{
					
					throw new Exception("Failed to set database connection object :: ");
					
				} // ENDS If checkConnection
				
			} // ENDS IF CONNECTION IS NULL
			
		} 
		catch(ClassNotFoundException e)
		{
			System.out.println("Database connection Class/Jar missing :: "+e);
			return null;
			
		}
		catch(SQLException e)
		{
			
			System.out.println("SQLException while making Database connection :: " + e.getMessage());
			return null;
		}
		catch(Exception e)
		{
			System.out.println("Exception :: " + e.getMessage());
			
		}
		// Ends Main try-catch Block
		
		return con;
		
	} // ends getConnection()
	

	/**
	 * Checks if database connection object is initialized or not.
	 * @param con2
	 * @return boolean
	 */
	public static boolean checkConnection(Connection con2) 
	{ // checkConnection()
		
		if(con2!=null)
		{
			
			return true;
			
		}
		else
		{
			
			return false; 
			
		}
		
	} // ENDS checkConnection()
	
	/**
	 * Gets Current connected Database details
	 * @param con
	 * @return
	 */
	public static HashMap<String, String> getDatabaseDetails(Connection con) 
	{

		HashMap<String, String> databaseMetaDataHMap = new HashMap<String, String>();
		
		try
		{
			
			if(checkConnection(con))
			{
				
				DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
				databaseMetaDataHMap.put("DriverName", dm.getDriverName());
				databaseMetaDataHMap.put("DriverVersion", dm.getDriverVersion());
				databaseMetaDataHMap.put("ProductName", dm.getDatabaseProductName());
				databaseMetaDataHMap.put("ProductVersion", dm.getDatabaseProductVersion());
				databaseMetaDataHMap.put("MaxConnection", String.valueOf(dm.getMaxConnections()));
				
			}
			
			return databaseMetaDataHMap;
			
		}
		catch(SQLException e)
		{
			System.out.println("Exception :: "+e.getMessage());
			return null;
		}
		
	}
	
} // ENDS DatabaseConnection Class