package connect;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UploadFiles
 */
public class UploadFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFiles() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String folder = request.getParameter("folder");
		ServletContext servletContext = getServletContext();
		String contextPath = servletContext.getRealPath("/");
		contextPath = contextPath + "\\app\\files\\" + folder;
		File file = new File(contextPath);
		if (!file.exists()) {
			file.mkdir();
		}
		MultipartRequest m = new MultipartRequest(request, contextPath);
		m.getFileNames();
		response.sendRedirect("/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String folder = request.getParameter("folder");
		ServletContext servletContext = getServletContext();
		String contextPath = servletContext.getRealPath("/");
		contextPath = contextPath + "\\app\\files\\" + folder;
		File file = new File(contextPath);
		if (!file.exists()) {
			file.mkdir();
		}
		MultipartRequest m = new MultipartRequest(request, contextPath);
		m.getFileNames();
		response.sendRedirect(request.getContextPath());
	}

}
