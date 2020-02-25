package webapp.exchange;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import common.db.Actions;
import app.register.Activate;
import app.register.Add;
import app.register.Verify;

public class Register {
	public boolean verifyMobile(String strMobileNumber){
		boolean result = false;
		app.register.Verify obj = new Verify();
		result = obj.verifyMobile(strMobileNumber);
		return result;
	}
	public boolean verifyEmail(String strEmail){
		boolean result = false;
		app.register.Verify obj = new Verify();
		result = obj.verifyEmail(strEmail);
		return result;
	}
	public boolean verifyId(String strIdNumber){
		boolean result = false;
		app.register.Verify obj = new Verify();
		result = obj.verifyId(strIdNumber);
		return result;
	}
	public boolean registerNewUser(ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		boolean result = false;
		app.register.Add obj = new Add();
		result = obj.newEntry(fieldNames, fieldValues);
		return result;
	}
	public boolean activateAccount(String code){
		boolean result = false;
		app.register.Activate obj = new Activate();
		obj.activateAccount(code);
		return result;
	}
	public boolean registerGuest(ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		boolean result = false;
		app.register.Add obj = new Add();
		result = obj.newGuest(fieldNames, fieldValues);
		return result;
	}
	public CachedRowSet getDetails(String tableName,String primaryKey, String primaryKeyValue){
		common.db.Actions obj = new Actions();
		CachedRowSet cr = null;
		try {
			cr = obj.fetchDataFromDB(tableName, null, primaryKey + "= '"+primaryKeyValue+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cr;
	}
}
