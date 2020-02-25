package connect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SubmitPage
 */
public class logoutSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public logoutSession() {
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
					boolean result = false;
					try {
						session.removeAttribute(Messages.getString("connect.user")); 
						response.setHeader("Cache-control", "no-store");  
						response.setHeader("Pragma", "no-cache");  
						response.setDateHeader("Expire", 0); 
						session.invalidate();
						result = true;
						response.getWriter().write(Boolean.toString(result));
					} catch (Exception e) {
						response.getWriter().write(Boolean.toString(result));
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
					boolean result = false;
					try {
						session.removeAttribute("User"); 
						response.setHeader("Cache-control", "no-store");  
						response.setHeader("Pragma", "no-cache");  
						response.setDateHeader("Expire", 0); 
						session.invalidate();
						result = true;
						response.getWriter().write(Boolean.toString(result));
					} catch (Exception e) {
						response.getWriter().write(Boolean.toString(result));
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
