package connect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class SubmitPage
 */
public class JSONConvertFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JSONConvertFromFile() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); 
		response.setHeader("Cache-Control", "no-cache");  
		HttpSession session = request.getSession();
		if (session == null) {
			response.getWriter().write(
					Messages.getString("connect.SessionExpiredException")); 
		} else {
			if (request.getHeader("X-Requested-With") != null) { 
				if (request.getHeader("X-Requested-With").equalsIgnoreCase( 
						"XMLHttpRequest")) { 
					try {
						String filePath = null; 
						  StringBuffer jb = new StringBuffer();
						  String line = null;
						  try {
						    BufferedReader reader = request.getReader();
						    while ((line = reader.readLine()) != null)
						      jb.append(line);
						  } catch (Exception e) {}
						  try {
							  JsonParser jsonParser = new JsonParser();
							  JsonArray jo = (JsonArray)jsonParser.parse(jb.toString());
							  filePath = jo.get(0).getAsJsonObject().get("file").getAsString();
						  } catch (Exception e) {System.out.println("Exception::"+e);}
						ServletContext servletContext = getServletContext();
						String contextPath = servletContext.getRealPath("/");
						filePath = contextPath + "\\app\\json\\" + filePath;
						String returnString = null;
						JsonObject jsonObject = new JsonObject();

						try {
							
							JsonParser parser = new JsonParser();
							JsonElement jsonElement;

							jsonElement = parser.parse(new FileReader(filePath));
							

							jsonObject = jsonElement.getAsJsonObject();
							
						} catch (FileNotFoundException e) {
							response.getWriter().write(Messages.getString("connect.Exception") + e); 
						} catch (@SuppressWarnings("hiding") IOException ioe) {
							response.getWriter().write(Messages.getString("connect.Exception") + ioe); 
						}
						if(jsonObject != null){
							returnString = jsonObject.toString();
						}
						if (returnString == null) {
							response.getWriter().write(Messages.getString("connect.null")); 
						} else {
							response.getWriter().write(returnString);
						}
						
					} catch (Exception e) {
						response.getWriter().write(Messages.getString("connect.Exception") + e); 
					}
				} else {
					response.getWriter().write(
							Messages.getString("connect.SessionExpiredException")); 
				}
			} else {
				response.getWriter().write(
						Messages.getString("connect.SessionExpiredException")); 
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); 
		response.setHeader("Cache-Control", "no-cache");  
		HttpSession session = request.getSession();
		if (session == null) {
			response.getWriter().write(
					Messages.getString("connect.SessionExpiredException")); 
		} else {
			if (request.getHeader("X-Requested-With") != null) { 
				if (request.getHeader("X-Requested-With").equalsIgnoreCase( 
						"XMLHttpRequest")) { 
					try {
						  String filePath = null; 
						  StringBuffer jb = new StringBuffer();
						  String line = null;
						  try {
						    BufferedReader reader = request.getReader();
						    while ((line = reader.readLine()) != null)
						      jb.append(line);
						  } catch (Exception e) {}
						  try {
							  JsonParser jsonParser = new JsonParser();
							  JsonArray jo = (JsonArray)jsonParser.parse(jb.toString());
							  filePath = jo.get(0).getAsJsonObject().get("file").getAsString();
						  } catch (Exception e) {System.out.println("Exception::"+e);}
						 
						ServletContext servletContext = getServletContext();
						String contextPath = servletContext.getRealPath("/");
						filePath = contextPath + "\\app\\json\\" + filePath;
						String returnString = null;
						JsonObject jsonObject = new JsonObject();
						
						try {
							
							JsonParser parser = new JsonParser();
							JsonElement jsonElement;

							jsonElement = parser.parse(new FileReader(filePath));
							jsonObject = jsonElement.getAsJsonObject();
							
						} catch (FileNotFoundException e) {
							response.getWriter().write(Messages.getString("connect.Exception") + e); 
						} catch (@SuppressWarnings("hiding") IOException ioe) {
							response.getWriter().write(Messages.getString("connect.Exception") + ioe); 
						}
						if(jsonObject != null){
							returnString = jsonObject.toString();
							//System.out.println("returnString::"+returnString);
						}
						if (returnString == null) {
							response.getWriter().write(Messages.getString("connect.null")); 
						} else {
							response.getWriter().write(returnString);
						}
					} catch (Exception e) {
						response.getWriter().write(Messages.getString("connect.Exception") + e); 
					}
				} else {
					response.getWriter().write(
							Messages.getString("connect.SessionExpiredException")); 
				}
			} else {
				response.getWriter().write(
						Messages.getString("connect.SessionExpiredException")); 
			}
		}
	}
}
