/*
 * ********************Actions.java******************* 
 * Module             : databaseActions.
 * Module Purpose     : To Perform all Database Related Actions.
 * Throws             : Success Message and Exceptions
 * Return             : 
 * Author             : Sri Harsha Samana
 * Revision           :
 * Created on         : 08/Oct/2015
 * ********************************************************
 */
/* ****************Logic************************
 *   Run the application.
 *   Performs the following :
 *     1. Performs all Database Related Actions with a specific method for each Action.
*/
package common.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
/**
 * 
 * @author Sri Harsha Samana
 *
 */
public class Actions {
	
	/**
	 * Adds a new entry into the Database Table
	 * @param paramDatabaseTableName Database Table Name
	 * @param paramDatabaseTableName Database Table Columns
	 * @param paramValues Values to be entered into the Columns
	 * @return a String stating whether the entry is added or not
	 * @throws Exception
	 */
	public boolean addNewEntry(String paramDatabaseTableName, ArrayList<String> DatabaseTableFields, ArrayList<String> paramValues) throws Exception
	{
			Connection con = Connect.getConnection();
		try
			{
			if(con == null)
				{
					return false;
				}
			
			String strinsertQuery = "INSERT INTO "+paramDatabaseTableName+" (";
			String strFields = "";
			String strValues = "";

			for(int i=0;i<DatabaseTableFields.size();i++)
				{
					String streachTableFieldName = DatabaseTableFields.get(i);
					if(i == (DatabaseTableFields.size()-1))
						{
							strFields = strFields + streachTableFieldName;
							strValues = strValues + "?";
						}
						else
							{
								strFields = strFields + streachTableFieldName + ",";
								strValues = strValues + "?" + ",";
							}      
				}
			
			strinsertQuery = strinsertQuery + strFields + ") VALUES ("+strValues+")";
			//System.out.println("strinsertQuery::"+strinsertQuery);
			java.sql.PreparedStatement stmt = con.prepareStatement(strinsertQuery);

			for(int i=0;i<DatabaseTableFields.size();i++)
			{
				String eachValue = paramValues.get(i);
				stmt.setString(i+1, eachValue);
			}
			//System.out.println("strinsertQuery::"+stmt.toString());
			stmt.executeUpdate();   
		}
		catch(Exception e)
			{
				System.out.println("Exception in AddNewEntry::"+e);
				return false;
			}
		
		finally
		{
			con.close();
		}
		
		return true;
	}
	/**
	 * Deletes an existing entry from the database
	 * @param paramDatabaseTableName Database Table Name
	 * @param paramPrimaryKey Primary Key
	 * @return a String stating whether the entry is deleted or not
	 * @throws Exception
	 */
	public boolean deleteExistingEntry(String paramDatabaseTableName, String paramPrimaryKey) throws Exception
	{
		Connection con = Connect.getConnection();
		try
		{
			if(con == null)
				{
					return false;
				}
			
			Statement statement = con.createStatement();
			String strdeleteQuery = "DELETE FROM "+paramDatabaseTableName+" WHERE "+paramDatabaseTableName+"_UID ="+paramPrimaryKey;
			//System.out.println("strdeleteQuery::"+strdeleteQuery);
			statement.executeUpdate(strdeleteQuery);   
		}
		catch(Exception e)
			{
				System.out.println("Exception in DeleteExistingEntry::"+e);
				return false;
			}
		finally
		{
			con.close();
		}
		
		return true;
	}
	/**
	 * Updates an existing entry in the Database Table
	 * @param paramDatabaseTableName Database Table Name
	 * @param paramDatabaseTableFields Columns of the Database Table
	 * @param paramValues Values into the Database Table Columns
	 * @param paramPrimaryKey Primary Key
	 * @return a String stating whether the entry is updated or not
	 * @throws Exception
	 */
	public boolean updateExistingEntry(String paramDatabaseTableName, ArrayList<String> paramDatabaseTableFields, ArrayList<String> paramValues,String paramPrimaryKey,String paramPrimaryKeyValue) throws Exception
	{
		Connection con = Connect.getConnection();
		try
		{

			if(con == null)
				{
					return false;
				}
			
			Statement statement = con.createStatement();
			String strupdateQuery = "UPDATE "+paramDatabaseTableName+" SET ";
			for(int i=0;i<paramDatabaseTableFields.size();i++)
				{
					strupdateQuery += paramDatabaseTableFields.get(i) + "=" + "'" + paramValues.get(i) + "'" + ",";
				}
			
			strupdateQuery = strupdateQuery.substring(0,strupdateQuery.length()-1);
			strupdateQuery += " WHERE "+ paramPrimaryKey + " = '"+paramPrimaryKeyValue+"'";
			//System.out.println("strupdateQuery::"+strupdateQuery);
			statement.executeUpdate(strupdateQuery);   
		}
		catch(Exception e)
			{
				System.out.println("Exception in UpdateExistingEntry::"+e);
				return false;
			}
			finally
			{
				con.close();
			}
		
		return true;
	}




/**
 * Fetches Data From Database Table 	
 * @param paramTableName
 * @param paramselectList
 * @param paramselectCondition
 * @return A Cached Row Set Containing the required data
 * @throws Exception
 */
	public CachedRowSet fetchDataFromDB(String paramTableName, ArrayList<String> paramselectList, String paramselectCondition) throws Exception
	{
		ResultSet resultSet = null;
		Connection con = Connect.getConnection();
		CachedRowSet returnSet = RowSetProvider.newFactory().createCachedRowSet();
		if(con == null)
		{
			return returnSet;
		}			
		Statement statement = con.createStatement();
		String strreqList = "*";
		String strwhereCondition = "";
	try
		{
		if(paramselectList != null)
		{
		if(!paramselectList.isEmpty())
			{
				strreqList = "";
				for(int i=0;i<paramselectList.size();i++)
					{
						if(i==paramselectList.size()-1)
							{
								strreqList = strreqList + paramselectList.get(i);
							}
							else
								{
									strreqList = strreqList + paramselectList.get(i)+",";
								}
					}	
			}
		}
		String strsearchQuery = "SELECT "+strreqList+" FROM "+paramTableName;   
		if(paramselectCondition != null && !paramselectCondition.trim().equalsIgnoreCase("NA") && !paramselectCondition.trim().equalsIgnoreCase("") && !paramselectCondition.trim().equalsIgnoreCase("*"))
		{
			paramselectCondition = paramselectCondition.replace("*","%");
			strwhereCondition = " WHERE " + paramselectCondition;
		}
		
			strsearchQuery += strwhereCondition;
			//System.out.println("strsearchQuery::"+strsearchQuery);
			resultSet = statement.executeQuery(strsearchQuery);
	
			returnSet.populate(resultSet);
		}
		catch(Exception e)
		{	
			System.out.println("Exception in fetchDataFromDB::"+e);
			return returnSet;
		}
		finally
		{
			con.close();
		}
		return returnSet;
	}
}