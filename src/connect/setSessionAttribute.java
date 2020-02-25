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
public class setSessionAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setSessionAttribute() {
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
					try {
						String attributeName = request.getParameter(Messages.getString("connect.name")); 
						String attributeValue = request.getParameter(Messages.getString("connect.value")); 
						session.setAttribute(attributeName, attributeValue);
						response.getWriter().write(Messages.getString("connect.true")); 
					} catch (Exception e) {
						response.getWriter().write(Messages.getString("connect.false")); 
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
						String attributeName = request.getParameter(Messages.getString("connect.name")); 
						String attributeValue = request.getParameter(Messages.getString("connect.value")); 
						session.setAttribute(attributeName, attributeValue);
						response.getWriter().write(Messages.getString("connect.true")); 
					} catch (Exception e) {
						response.getWriter().write(Messages.getString("connect.false")); 
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
