package se.coredev.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "log-filter")
public class LogFilter implements Filter {

	// filter1 --> filter2 --> filter3 --> MessgeServlet
	//
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log(request);
		chain.doFilter(request, response);
		
	}

	private void log(ServletRequest request){
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String logMessage = new StringBuilder().append("\nRequest URL:")
											   .append(httpRequest.getRequestURL())
											   .append("\nMethod:").append(httpRequest.getMethod())
											   .toString();
		
		System.out.println(logMessage);
	}
	
	@Override
	public void destroy() {}

}
