package app.register;

import java.security.MessageDigest;
import java.util.ArrayList;

import app.common.Email;
import common.db.Actions;

public class Add {
	public boolean newEntry(ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		boolean result = false;
		String DATABASE_TABLE_NAME = "users_registered";
		common.db.Actions obj = new Actions();
		try {
			result = obj.addNewEntry(DATABASE_TABLE_NAME, fieldNames, fieldValues);
		} catch (Exception e) {
			result = false;
		}
		if(result){
			ArrayList<String> toList = new ArrayList<String>();
			String subject = "4WayCabs - Activation Activation Mail";
			String encryptedCode = encrypt(fieldValues.get(2));
			try {
				ArrayList<String> fields = new ArrayList<String>();
				ArrayList<String> values = new ArrayList<String>();
				fields.add("email");
				fields.add("code");
				values.add(fieldValues.get(2));
				values.add(encryptedCode);
				obj.addNewEntry("activation_account", fields, values);
			} catch (Exception e) {
				result = false;
			}
			
			String body = "Dear User,\nWelcome to 4WayCabs. Thank you for registering with us.\nPlease click on below link to Activate your Account.\n http://www.4wayCabs.com/ActivateAccount/?account="+encryptedCode+"\nEnjoy hassle free rides with 4WayCabs. Have a Nice day\n\nThanks & Regards,\nTeam 4WayCabs.";
			app.common.Email email = new Email();
			toList.add(fieldValues.get(2));
			email.sendEmail(toList, subject, body);
		}
		return result;
	}
	public String encrypt(String paramData) 
	{
        StringBuffer sb = new StringBuffer();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(paramData.getBytes("UTF-8"));
            byte[] digestBytes = messageDigest.digest();


            String strhex = null;

            for (int i = 0; i < digestBytes.length; i++) {
                //Convert it to positive integer and then to Hex String

                strhex = Integer.toHexString(0xFF & digestBytes[i]);

                //Append "0" to the String to made it exactly 128 length (SHA-512 alogithm)
                if (strhex.length() < 2) 
                    sb.append("0");
                sb.append(strhex);
                }
            }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            }

        return new String(sb);
    }
	public boolean newGuest(ArrayList<String> fieldNames, ArrayList<String> fieldValues){
		boolean result = false;
		String DATABASE_TABLE_NAME = "users_guest";
		common.db.Actions obj = new Actions();
		try {
			result = obj.addNewEntry(DATABASE_TABLE_NAME, fieldNames, fieldValues);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}
