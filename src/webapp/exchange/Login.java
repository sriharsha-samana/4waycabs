package webapp.exchange;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

public class Login {
	public boolean verifyUser(String strUsername, String strPassword){
		boolean result = false;
		app.login.Login obj = new app.login.Login();
		result = obj.verifyUser(strUsername, strPassword);
		return result;
	}
	public String getFullName(String strEmail){
		String result = strEmail;
		app.login.Login obj = new app.login.Login();
		result = obj.getFullName(strEmail);
		return result;
	}
	public String getMobile(String strEmail){
		String result = strEmail;
		app.login.Login obj = new app.login.Login();
		result = obj.getMobile(strEmail);
		return result;
	}
	public String getDOB(String strEmail){
		String result = strEmail;
		app.login.Login obj = new app.login.Login();
		result = obj.getDOB(strEmail);
		return result;
	}
	public String getEmail(String code){
		String result = "false";
		app.login.Login obj = new app.login.Login();
		result = obj.getEmail(code);
		return result;
	}
	public boolean forgotPassword(String strEmail, String strMobile){
		boolean result = false;
		app.login.Login obj = new app.login.Login();
		result = obj.forgotPassword(strEmail, strMobile);
		return result;
	}
	public boolean checkPass(String code){
		boolean result = false;
		app.login.Login obj = new app.login.Login();
		result = obj.checkPass(code);
		return result;
	}
	public boolean updateUser(String strEmail, ArrayList<String> fieldNames,ArrayList<String> fieldValues){
		boolean result = false;
		app.login.Login obj = new app.login.Login();
		result = obj.updateUser(strEmail, fieldNames, fieldValues);
		return result;
	}
	public CachedRowSet getAllRegisteredUsers(){
		app.login.Login obj = new app.login.Login();
		CachedRowSet result = obj.getAllRegisteredUsers();
		return result;
	}
	public CachedRowSet getAllGuests(){
		app.login.Login obj = new app.login.Login();
		CachedRowSet result = obj.getAllGuests();
		return result;
	}
}
