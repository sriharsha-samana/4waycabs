package connect;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class StaticResourceFilter
 */
public class StaticResourceFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public StaticResourceFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String path = req.getRequestURI().substring(
				req.getContextPath().length());

		if (path.equals(Messages.getString("connect.Filter.root"))) { 
			((HttpServletResponse) response).sendRedirect(Messages.getString("connect.Filter.home")); 
			return;
		}
		if (path.contains(Messages.getString("connect.Filter.images")) || path.contains(Messages.getString("connect.Filter.css"))  
				|| path.contains(Messages.getString("connect.Filter.js")) || path.contains(Messages.getString("connect.Filter.json")) || path.contains(Messages.getString("connect.Filter.fonts"))  
				|| path.contains(Messages.getString("connect.Filter.js/")) || path.contains(Messages.getString("connect.Filter.json/")) || path.contains(Messages.getString("connect.Filter.css/"))  
				|| path.contains(Messages.getString("connect.Filter.images/")) || path.contains(Messages.getString("connect.Filter.fonts/"))) {  
			if (path.startsWith(Messages.getString("connect.Filter.images")) || path.startsWith(Messages.getString("connect.Filter.css"))  
					|| path.startsWith(Messages.getString("connect.Filter.js")) 
					|| path.startsWith(Messages.getString("connect.Filter.fonts"))
					|| path.startsWith(Messages.getString("connect.Filter.json"))) { 
				chain.doFilter(request, response);
			} else {
				if (path.contains(Messages.getString("connect.Filter.js/"))) { 
					path = path.substring(path.lastIndexOf(Messages.getString("connect.Filter.js/")), 
							path.length());
				} else if (path.contains(Messages.getString("connect.Filter.css/"))) { 
					path = path.substring(path.lastIndexOf(Messages.getString("connect.Filter.css/")), 
							path.length());
				} else if (path.contains(Messages.getString("connect.Filter.images/"))) { 
					path = path.substring(path.lastIndexOf(Messages.getString("connect.Filter.images/")), 
							path.length());
				} else if (path.contains(Messages.getString("connect.Filter.fonts/"))) { 
					path = path.substring(path.lastIndexOf(Messages.getString("connect.Filter.fonts/")), 
							path.length());
				}
				if (path.contains(Messages.getString("connect.Filter.json/"))) { 
					path = path.substring(path.lastIndexOf(Messages.getString("connect.Filter.json/")), 
							path.length());
				}

				request.getRequestDispatcher(Messages.getString("connect.Filter.app") + path).forward(request, 
						response);
			}
		} else {
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
