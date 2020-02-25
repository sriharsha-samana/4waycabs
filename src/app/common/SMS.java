package app.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SMS
{
  String response;
  
  public static void main(String[] args) {}
  
  public boolean sendsms(String mobilenum, String message)
  {
    String authkey = "8342AD9Yb9hX54558cf26f";
    
    String mobile = mobilenum;
    
    String senderId = "WEBSMS";
    
    String finalmessage = message;
    
    String route = "1";
    
    URLConnection myURLConnection = null;
    URL myURL = null;
    BufferedReader reader = null;
    
    @SuppressWarnings("deprecation")
	String encoded_message = URLEncoder.encode(finalmessage);
    

    String mainUrl = "http://sms.ssdindia.com/sendhttp.php?";
    

    StringBuilder sbPostData = new StringBuilder(mainUrl);
    sbPostData.append("authkey=" + authkey);
    sbPostData.append("&mobiles=" + mobile);
    sbPostData.append("&message=" + encoded_message);
    sbPostData.append("&route=" + route);
    sbPostData.append("&sender=" + senderId);
    

    mainUrl = sbPostData.toString();
    try
    {
      myURL = new URL(mainUrl);
      myURLConnection = myURL.openConnection();
      myURLConnection.connect();
      reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
      while ((this.response = reader.readLine()) != null) {
        //System.out.println(this.response);
      }
      reader.close();
      return true;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
}
