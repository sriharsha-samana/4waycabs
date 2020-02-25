/**
 * @author Sriharsha Samana
 *
 */
package app.common;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import common.db.Config;

public class Email {

	public static void main(String[] args)
	{
		//AUTO-GENERATED
	}
	public boolean sendEmail(ArrayList<String> toList,String subject,String body)
	{	
		String from = "info@4waycabs.com";
		String host = Config.SMTPHOST;
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         for(int i=0;i<toList.size();i++){
	        	   message.addRecipient(Message.RecipientType.TO,
                           new InternetAddress(toList.get(i))); 
	         }
	      
	         message.setSubject(subject);
	         message.setText(body);
	         Transport.send(message);
	      }catch (MessagingException mex) {
	         System.out.println("Exception in sendMail :: "+mex);
	         return false;
	      }
		return true;
	   }
}
