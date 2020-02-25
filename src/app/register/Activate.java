package app.register;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import common.db.Actions;

public class Activate {
	public boolean activateAccount(String code){
		boolean result = false;
		String DATABASE_TABLE_NAME = "users_registered";
		ArrayList<String> paramDatabaseTableFields = new ArrayList<String>();
		ArrayList<String> paramValues = new ArrayList<String>();
		paramDatabaseTableFields.add("status");
		paramValues.add("active");
		common.db.Actions obj = new Actions();
		try {
			CachedRowSet cr = obj.fetchDataFromDB("activation_account", null, "code = '"+code+"'");
			if(cr.next()){
				result = obj.updateExistingEntry(DATABASE_TABLE_NAME, paramDatabaseTableFields, paramValues, "email", cr.getString("email"));
			}
			
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
}
