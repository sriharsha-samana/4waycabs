package app.register;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import common.db.Actions;

public class Verify {
	public boolean verifyMobile(String strMobileNumber){
		boolean result = true;
		String DATABASE_TABLE_NAME = "users_registered";
		String strWhere = "mobile = '"+strMobileNumber+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {
			System.out.println("Exception 1 " + e);
			result = false;
		}

		try {
			if(cr.next()){
					result = false;
			}
		} catch (SQLException e) {
			System.out.println("Exception 2 " + e);
			result = false;
		}
		return result;
	}
	public boolean verifyEmail(String strEmail){
		boolean result = true;
		String DATABASE_TABLE_NAME = "users_registered";
		String strWhere = "email = '"+strEmail+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {
			result = false;
		}

		try {
			if(cr.next()){

					result = false;
			}
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}
	public boolean verifyId(String strIdNumber){
		boolean result = true;
		String DATABASE_TABLE_NAME = "users_registered";
		String strWhere = "id_number = '"+strIdNumber+"'";
		CachedRowSet cr = null;
		common.db.Actions obj = new Actions();
		try {
			cr = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, strWhere);
		} catch (Exception e) {
			result = false;
		}

		try {
			if(cr.next()){

					result = false;
			}
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}
}
