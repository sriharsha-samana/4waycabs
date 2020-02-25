package connect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
public class JSONDeleteFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JSONDeleteFromFile() {
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
						  String counter = null;
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
							  counter = jo.get(0).getAsJsonObject().get("count").getAsString();
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
							

							int count = 0;
							try{
								count = Integer.parseInt(counter);
							}catch(Exception e){
								response.getWriter().write(Messages.getString("connect.Exception") + e); 
							}
							
							JsonArray array = jsonObject.get("items").getAsJsonArray();
							try{
								array.remove(count);
							}
							catch(IndexOutOfBoundsException e){
								response.getWriter().write(Messages.getString("connect.Exception") + e); 
							}
							if(jsonObject != null){
								returnString = jsonObject.toString();
							}
							
							File file = new File(filePath);
							if (!file.exists()) {
								file.createNewFile();
							}

							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(returnString);
							bw.close();
							
						} catch (FileNotFoundException e) {
							response.getWriter().write(Messages.getString("connect.Exception") + e); 
						} catch (IOException ioe) {
							response.getWriter().write(Messages.getString("connect.Exception") + ioe); 
						}

						if (returnString == null) {
							response.getWriter().write(Messages.getString("connect.null")); 
						} else {
							response.getWriter().write("true");
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
						  String counter = null;
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
							  counter = jo.get(0).getAsJsonObject().get("count").getAsString();
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

							int count = 0;
							try{
								count = Integer.parseInt(counter);
							}catch(Exception e){
								response.getWriter().write(Messages.getString("connect.Exception") + e); 
							}
							
							JsonArray array = jsonObject.get("items").getAsJsonArray();
							try{
								array.remove(count);
							}
							catch(IndexOutOfBoundsException e){
								response.getWriter().write(Messages.getString("connect.Exception") + e); 
							}
							if(jsonObject != null){
								returnString = jsonObject.toString();
							}
							
							File file = new File(filePath);
							if (!file.exists()) {
								file.createNewFile();
							}

							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(returnString);
							bw.close();
							
						} catch (FileNotFoundException e) {
							response.getWriter().write(Messages.getString("connect.Exception") + e); 
						} catch (IOException ioe) {
							response.getWriter().write(Messages.getString("connect.Exception") + ioe); 
						}

						if (returnString == null) {
							response.getWriter().write(Messages.getString("connect.null")); 
						} else {
							response.getWriter().write("true");
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
