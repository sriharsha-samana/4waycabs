/**
 * @author Sriharsha Samana
 *
 */
package webapp.exchange;

import java.util.*;

public class Email {

	public static void main(String[] args)
	{
		//AUTO-GENERATED
	}
	public boolean sendFeedbackMailToAdmin(String fromName, String fromEmail , String fromMobile, String purpose, String body)
	{	
		String subject = "Request for \"" + purpose + "\" from " + "\"" + fromName + "\"";
		ArrayList<String> toList = new ArrayList<String>();
		toList.add("info@4waycabs.com");
		app.common.Email obj = new app.common.Email();
		boolean result = obj.sendEmail(toList, subject, body);
		
		if(result){
			toList.remove(0);
			toList.add(fromEmail);
			subject = "RE: Team 4WayCabs - Request Received";
			body = "Hello " + fromName +",\n";
			body += "Thanks for writing to 4WayCabs. We have received your request for \"" + purpose + "\".\nOur support team will contact you soon.\n\nThanks & Regards,\nTeam 4WayCabs.\n\n";
			result = obj.sendEmail(toList, subject, body);
		}
		
		return result;
	}
	public boolean sendNotificationToUser(ArrayList<String> toList, String action, ArrayList<String> data)
	{	
		String subject = "";
		String body = "";
		
		if(action.equalsIgnoreCase("new-booking")){
			subject = "4WayCabs : New Booking Request";
			body = "Dear User,\n\nThank you for choosing 4WayCabs.\nWe have received your order. \n Order details: \n Order Number : "+data.get(0)+"\nFrom : "+data.get(1)+"\nTo : "+data.get(2)+"\nVehicle Model : "+data.get(3)+"\n\nOur Support team will get back to you very soon. \n\nThanks & Regards,\nTeam 4WayCabs,\n\nEnjoy hassle free safe journey with us.";
		}
		else if(action.equalsIgnoreCase("driver-details")){
			subject = "4WayCabs : Driver & Vehicle Details";
			body = "Dear User,\n\nGreetings from 4WayCabs!\nThis is regarding your order '"+data.get(0)+"'. Please find driver and vehicle details below. \nDriver Name : "+data.get(1)+"\nDriver Mobile Number : "+data.get(2)+"\nVehicle Number : "+data.get(3)+"\n\nOur Support team will help you anytime in case of any difficulty. \n\nThanks & Regards,\nTeam 4WayCabs,\n\nEnjoy hassle free safe journey with us.";
		}
		else if(action.equalsIgnoreCase("order-completed")){
			subject = "4WayCabs : Order Completed";
			body = "Dear User,\n\nThank you for travelling with 4WayCabs. Hope you had a great journey with us. \n Order details: \n Order Number : "+data.get(0)+"\nTotal Distance : "+data.get(1)+" Kms\nTotal Duration : "+data.get(2)+" Hours\nTotal Fare : Rs. "+data.get(3)+"\n\nPlease do share your feedback about your experience with us. Have a great day! \n\nThanks & Regards,\nTeam 4WayCabs,\n\nEnjoy hassle free safe journey with us.";
		}
		
		app.common.Email obj = new app.common.Email();
		boolean result = obj.sendEmail(toList, subject, body);
		
		toList = new ArrayList<String>();
		toList.add("info@4waycabs.com");
		obj.sendEmail(toList, subject, body);
		
		return result;
	}
}
