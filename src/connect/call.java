package connect;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class SubmitPage
 */
public class call extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public call() {
		super();
		// TODO Auto-generated constructor stub
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
					StringBuffer jb = new StringBuffer();
					String line = null;
					String className = null;
					String methodName = null;
					String args = null;

					try {
						BufferedReader reader = request.getReader();
						while ((line = reader.readLine()) != null) {
							jb.append(line);
						}
					} catch (Exception e) {
						response.getWriter()
								.write(Messages.getString("connect.InvalidArgumentsException")); 
					}

					try {
						JsonObject jsonObj = new Gson().fromJson(jb.toString(),
								JsonObject.class);
						JsonElement j = jsonObj.get(Messages.getString("connect.class")); 
						className = j.getAsString();
						j = jsonObj.get(Messages.getString("connect.method")); 
						methodName = j.getAsString();
						j = jsonObj.get(Messages.getString("connect.arguments")); 
						args = j.getAsString();
					} catch (Exception e) {
						response.getWriter()
								.write(Messages.getString("connect.InvalidArgumentsException")); 
					}

					Server server = new Server();
					if (args != null) {
						args = args.replace("\\n", "");  
						args = args.replace("\\r", "");  
					}

					String returnString = null;
					try {
						returnString = server.call(className, methodName, args);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if (returnString != null) {
						returnString = returnString.trim();
						returnString = returnString.replace("\n", "<br>");  
					}
					// System.out.println("returnString::"+returnString);
					response.getWriter().write(returnString);
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
					StringBuffer jb = new StringBuffer();
					String line = null;
					String className = null;
					String methodName = null;
					String args = null;

					try {
						BufferedReader reader = request.getReader();
						while ((line = reader.readLine()) != null) {
							jb.append(line);
						}
					} catch (Exception e) {
						response.getWriter()
								.write(Messages.getString("connect.InvalidArgumentsException")); 
					}

					try {
						JsonObject jsonObj = new Gson().fromJson(jb.toString(),
								JsonObject.class);
						JsonElement j = jsonObj.get(Messages.getString("connect.class")); 
						className = j.getAsString();
						j = jsonObj.get(Messages.getString("connect.method")); 
						methodName = j.getAsString();
						j = jsonObj.get(Messages.getString("connect.arguments")); 
						args = j.getAsString();
					} catch (Exception e) {
						response.getWriter()
								.write(Messages.getString("connect.InvalidArgumentsException")); 
					}

					Server server = new Server();
					if (args != null) {
						args = args.replace("\\n", "");  
						args = args.replace("\\r", "");  
					}

					String returnString = null;
					try {
						returnString = server.call(className, methodName, args);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if (returnString != null) {
						returnString = returnString.trim();
						returnString = returnString.replace("\n", "<br>");  
					}
					 //System.out.println("returnString::"+returnString);
					response.getWriter().write(returnString);
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
