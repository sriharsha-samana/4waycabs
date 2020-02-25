package app.discount;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import common.db.Actions;

public class Discount {
	public boolean verifyCoupon(String user, String code)
	{
		boolean result = false;
		common.db.Actions obj = new Actions();
		try {
			CachedRowSet cr = obj.fetchDataFromDB("coupons", null, "user = '"+user+"' AND code = '"+code+"'");
			if(!cr.next()){
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateUsedCoupons(ArrayList<String> fieldNames, ArrayList<String> fieldValues)
	{
		boolean result = false;
		common.db.Actions obj = new Actions();
		try {
			result = obj.addNewEntry("coupons", fieldNames, fieldValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public CachedRowSet getAllUsedCoupons(){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		String DATABASE_TABLE_NAME = "coupons";

		try {
			result = obj.fetchDataFromDB(DATABASE_TABLE_NAME, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

