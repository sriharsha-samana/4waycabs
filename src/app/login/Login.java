package app.login;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import app.common.Email;
import app.register.Add;
import common.db.Actions;

public class Login {
	public boolean verifyUser(String strUsername, String strPassword){
		boolean result = false;
		String DATABASE_TABLE_NAME = "users_registered";
		String strWhere = "email = '"+strUsername+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {
			result = false;
		}

		try {
			if(cr.next()){
				if(cr.getString("password").equals(strPassword) && cr.getString("status").equals("active")){
					result = true;
				}
				else{
					result = false;
				}
			}
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}
	
	public String getFullName(String strEmail){
		String result = strEmail;
		String DATABASE_TABLE_NAME = "users_registered";
		String strWhere = "email = '"+strEmail+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {
			result = strEmail;
		}

		try {
			if(cr.next()){
				result = cr.getString("username");
			}
		} catch (SQLException e) {
			result = strEmail;
		}
		return result;
	}
	
	public String getMobile(String strEmail){
		String result = "NA";
		String DATABASE_TABLE_NAME = "users_registered";
		String strWhere = "email = '"+strEmail+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {}

		try {
			if(cr.next()){
				result = cr.getString("mobile");
			}
		} catch (SQLException e) {}
		
		return result;
	}
	public String getDOB(String strEmail){
		String result = "NA";
		String DATABASE_TABLE_NAME = "users_registered";
		String strWhere = "email = '"+strEmail+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {}

		try {
			if(cr.next()){
				result = cr.getString("dob");
			}
		} catch (SQLException e) {}
		
		return result;
	}
	public String getEmail(String code){
		String result = "false";
		String DATABASE_TABLE_NAME = "reset_password";
		String strWhere = "code = '"+code+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {}

		try {
			if(cr.next()){
				result = cr.getString("email");
			}
		} catch (SQLException e) {}
		
		return result;
	}
	
	public boolean checkPass(String code){
		boolean result = false;
		String DATABASE_TABLE_NAME = "reset_password";
		String strWhere = "code = '"+code+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {}

		try {
			if(cr.next()){
				result = true;
			}
		} catch (SQLException e) {System.out.println("Exception :: "+e);}
		
		return result;
	}
	
	public boolean forgotPassword(String strEmail,String strMobile){
		boolean result = false;
		app.common.Email obj = new Email();
		ArrayList<String> toList = new ArrayList<String>();
		String subject = "4WayCabs - Reset Password Link";
		String body = "Dear User,\n\nPlease use below link to reset your profile password.\n\n";
		app.register.Add obj1 = new Add();
		String encryptedCode = obj1.encrypt(strEmail);
		common.db.Actions obj2 = new Actions();
		try {
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<String> values = new ArrayList<String>();
			fields.add("email");
			fields.add("code");
			values.add(strEmail);
			values.add(encryptedCode);
			obj2.addNewEntry("reset_password", fields, values);
		} catch (Exception e) {
			result = false;
		}
		
		body +="http://www.4waycabs.com/Login/ResetPassword/?pass="+encryptedCode;
		if(strEmail != null && !"".equals(strEmail) && !"null".equals(strEmail)) {
			strEmail = strEmail.trim();
			toList.add(strEmail);
			result = obj.sendEmail(toList, subject, body);
		}
		else if(strMobile != null && !"".equals(strMobile) && !"null".equals(strMobile)){
			strMobile = strMobile.trim();
			CachedRowSet cr;
			try {
				cr = obj2.fetchDataFromDB("users_registered", null, "mobile='"+strMobile+"'");
				if(cr.next()){
					toList.add(cr.getString("email"));
				}
				result = obj.sendEmail(toList, subject, body);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public boolean updateUser(String strEmail, ArrayList<String> fieldNames,ArrayList<String> fieldValues){
		common.db.Actions obj = new Actions();
		boolean result = false;
		String DATABASE_TABLE_NAME = "users_registered";

		try {
			result = obj.updateExistingEntry(DATABASE_TABLE_NAME, fieldNames, fieldValues, "email", strEmail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public CachedRowSet getAllRegisteredUsers(){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		String DATABASE_TABLE_NAME = "users_registered";

		try {
			result = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public CachedRowSet getAllGuests(){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		String DATABASE_TABLE_NAME = "users_guest";

		try {
			result = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
