/**
 * @author Sriharsha Samana
 *
 */
package webapp.exchange;

import java.util.ArrayList;

public class DatabaseTables {
	public ArrayList<String> fetch(){
		ArrayList<String> list = null;
		list = new ArrayList<String>();
		list.add("users_registered");
		list.add("users_guest");
		list.add("bookings");
		list.add("coupons");
		return list;
	}
}
