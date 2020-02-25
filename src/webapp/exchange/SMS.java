package webapp.exchange;

import java.util.ArrayList;

public class SMS {

	public boolean sendNotificationToUser(String mobileNumber, String action, ArrayList<String> data)
	{	
		String message = "";
		
		if(action.equalsIgnoreCase("new-booking")){
			message = "4WayCabs! Dear User,your request "+data.get(0)+" for vehicle "+data.get(3)+" is received.Our Suuport team will call you very soon.";
		}
		else if(action.equalsIgnoreCase("driver-details")){
			message = "4WayCabs! Dear User, Driver details for your request "+data.get(0)+", Driver Name:"+data.get(1)+",Driver Mobile:"+data.get(2)+",Vehicle Number:"+data.get(3);
		}
		else if(action.equalsIgnoreCase("order-completed")){
			message = "4WayCabs! Dear User,your request "+data.get(0)+" is completed. Total fare is Rs. "+data.get(3)+".Thank you choosing us.Hope you had great experience,Have a grat day.";
		}
		
		app.common.SMS obj = new app.common.SMS();
		boolean result = obj.sendsms(mobileNumber, message);	
		return result;
	}
}
