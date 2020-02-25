package common.db;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServlet;

public final class Config extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5580865842425462008L;
	//PUBLIC VARIABLES
	public static String PROVIDER;
	public static String DATABASEDRIVER;
	public static String DATABASEHOST;
	public static String DATABASEPORT;
	public static String DATABASEURL;
	public static String USERNAME;
	public static String PASSWORD;
	public static String DATABASENAME;
	public static String SMTPHOST;

	//PRIVATE VARIABLES
	private static Properties properties = null;

	/** 
	 * Private Constructor to prevent object creation
	 */
	public Config()
	{ 
	}

	
	/*
	 * Override to protect it from garbage collection
	 */
	/*
	@Override
	protected void finalize() throws Throwable 
	{
	}
	*/
	
	
	/** 
	 * Static block to set Database configuration
	 *  from DBConfiguration file. This method calls only once, at time of services startup.
	 *  Initializes static variables which holds configuration related data.
	 */
	static
	{
		if(properties==null)
		{ // if properties==null
			
			try
			{ // Try 1
				
				properties = new Properties();
				String appDirectory = System.getProperty("catalina.base"); 	
				//appDirectory += "/domains/4waycabs.com/ROOT/app/database/DBConfig.properties";
				appDirectory = "D:/Personal//Reference/4waycabs/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/4waycabs/app/database/DBConfig.properties";
				properties.load(new FileInputStream(appDirectory));
			} // ends Try 1
			catch (FileNotFoundException ex)
			{ // Catch 1
				System.out.println("Exception:"+ex.getMessage());
				
			} // ends Catch 1
			catch (IOException ex)
			{ // Catch 1
				
				System.out.println("Exception:"+ex.getMessage());
				
			} // ends Catch 1
				
			try 
			{ // Try 2
				
				PROVIDER = properties.getProperty("DBPROVIDER");
				DATABASEDRIVER = properties.getProperty("DBDRIVER");
				DATABASEHOST = properties.getProperty("DBHOST");
				DATABASEPORT = properties.getProperty("DBPORT");
				USERNAME = properties.getProperty("DBUSERNAME"); 
				PASSWORD = properties.getProperty("DBPASSWORD");
				DATABASENAME = properties.getProperty("DBName");	
				SMTPHOST = properties.getProperty("SMTPHOST");
			} // ends Try 2
			catch (Exception e) 
			{ // Catch 2
				System.out.println("Exception:"+e.getMessage());
				
			} // ends Catch 2
				
		}  // ends if properties==null
		else
		{	// else properties not null
			System.out.println("DB CONFIG FILE ALREADY LOADED.");
			
		}	// ends else properties not null
		
	} // ends getDBConfig


	public static String getPROVIDER() 
	{
		return PROVIDER;
	}


	public static String getDATABASEDRIVER() 
	{
		return DATABASEDRIVER;
	}


	public static String getDATABASEHOST() 
	{
		return DATABASEHOST;
	}


	public static String getDATABASEPORT() 
	{
		return DATABASEPORT;
	}


	public static String getDATABASEURL() 
	{
		return DATABASEURL;
	}


	public static String getUSERNAME() 
	{
		return USERNAME;
	}


	public static String getPASSWORD() 
	{
		return PASSWORD;
	}
	
	public static String getDATABASENAME() 
	{
		return DATABASENAME;
	}
	
	public static String getSMTPHOST() 
	{
		return SMTPHOST;
	}


	public static Properties getProperties() 
	{
		return properties;
	}
	public static void main(String[] args) {
		
	}
}