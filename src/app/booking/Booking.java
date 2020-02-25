package app.booking;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import common.db.Actions;
import app.common.KeyGenerator;

public class Booking {
	@SuppressWarnings("resource")
	public String generateBookingNumber(){
		common.db.Actions obj = new Actions();
		String result = "false";
		result = KeyGenerator.randomAlphaNumeric(7);
		try {
			CachedRowSet resultSet = obj.fetchDataFromDB("bookings", null, "booking_number = '"+result+"'");
			while(resultSet.next()){
				result = KeyGenerator.randomAlphaNumeric(7);
				resultSet = obj.fetchDataFromDB("bookings", null, "booking_number = '"+result+"'");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean registerNewBooking(ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		common.db.Actions obj = new Actions();
		boolean result = false;
		try {
			result = obj.addNewEntry("bookings", fieldNames, fieldValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public CachedRowSet fetchBookingWithNumber(String strBookingNumber){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		try {
			result = obj.fetchDataFromDB("bookings", null, "booking_number = '"+strBookingNumber+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public CachedRowSet fetchBookingsWithStatus(String strStatus){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		try {
			result = obj.fetchDataFromDB("bookings", null, "status = '"+strStatus+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public CachedRowSet fetchBookingsBeforeDate(String strBookingDate){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		try {
			result = obj.fetchDataFromDB("bookings", null, "booking_date <= '"+strBookingDate+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public CachedRowSet fetchAllBookings(){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		try {
			result = obj.fetchDataFromDB("bookings", null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean updateBooking(String strBookingNumber, ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		common.db.Actions obj = new Actions();
		boolean result = false;
		try {
			result = obj.updateExistingEntry("bookings", fieldNames, fieldValues, "booking_number", strBookingNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public CachedRowSet getUserBookings(String strUserEmail){
		common.db.Actions obj = new Actions();
		CachedRowSet result = null;
		try {
			result = obj.fetchDataFromDB("bookings", null, "email = '"+strUserEmail+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
}
