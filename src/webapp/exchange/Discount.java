package webapp.exchange;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

public class Discount {
	public boolean verifyCoupon(String user, String code)
	{
		boolean result = false;
		app.discount.Discount obj = new app.discount.Discount();
		result = obj.verifyCoupon(user, code);
		return result;
	}
	
	public boolean updateUsedCoupons(ArrayList<String> fieldNames, ArrayList<String> fieldValues)
	{
		boolean result = false;
		app.discount.Discount obj = new app.discount.Discount();
		result = obj.updateUsedCoupons(fieldNames, fieldValues);
		return result;
	}
	public CachedRowSet getAllUsedCoupons(){
		app.discount.Discount obj = new app.discount.Discount();
		CachedRowSet result = obj.getAllUsedCoupons();
		return result;
	}
}
