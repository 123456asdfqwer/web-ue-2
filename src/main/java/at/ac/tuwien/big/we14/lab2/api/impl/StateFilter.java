package at.ac.tuwien.big.we14.lab2.api.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StateFilter implements Filter {

    
    public StateFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse myResponse = (HttpServletResponse) response;
		HttpServletRequest myRequest = (HttpServletRequest) request;
		String uri = myRequest.getRequestURI();
		String pageName = uri.substring(uri.lastIndexOf("/")+1);
		pageName = pageName.substring(0, pageName.lastIndexOf('.'));
		
		HttpSession session = myRequest.getSession(false);
		
		if ( session != null && session.getAttribute("state") != null ) {
			if (!session.getAttribute("state").equals(pageName)) {
				myResponse.sendRedirect(session.getAttribute("state") + ".jsp");
				return;
			}
		}
		else {
			myResponse.sendRedirect("start.jsp"); 
			return;
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
