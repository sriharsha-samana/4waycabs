package webapp.exchange;

import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

public class Booking {
	public String generateBookingNumber(){
		String result = "false";
		app.booking.Booking obj = new app.booking.Booking();
		result = obj.generateBookingNumber();
		return result;
	}
	public boolean registerNewBooking(ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		boolean result = false;
		app.booking.Booking obj = new app.booking.Booking();
		result = obj.registerNewBooking(fieldNames, fieldValues);
		return result;
	}
	public CachedRowSet fetchBookingWithNumber(String strBookingNumber){
		app.booking.Booking obj = new app.booking.Booking();
		CachedRowSet result = obj.fetchBookingWithNumber(strBookingNumber);
		return result;
	}
	public CachedRowSet fetchBookingsWithStatus(String strStatus){
		app.booking.Booking obj = new app.booking.Booking();
		CachedRowSet result = obj.fetchBookingsWithStatus(strStatus);
		return result;
	}
	public CachedRowSet fetchBookingsBeforeDate(String strBookingDate){
		app.booking.Booking obj = new app.booking.Booking();
		CachedRowSet result = obj.fetchBookingsBeforeDate(strBookingDate);
		return result;
	}
	public CachedRowSet fetchAllBookings(){
		app.booking.Booking obj = new app.booking.Booking();
		CachedRowSet result = obj.fetchAllBookings();
		return result;
	}
	public boolean updateBooking(String strBookingNumber, ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		app.booking.Booking obj = new app.booking.Booking();
		boolean result = obj.updateBooking(strBookingNumber, fieldNames, fieldValues);
		return result;
	}
	public CachedRowSet getUserBookings(String strUserEmail){
		app.booking.Booking obj = new app.booking.Booking();
		CachedRowSet result = obj.getUserBookings(strUserEmail);
		return result;
	}
	
}
