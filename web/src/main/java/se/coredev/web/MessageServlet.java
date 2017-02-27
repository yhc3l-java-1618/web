package se.coredev.web;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://120.0.0.1:8080/web/messages 				- all messages (GET)


@WebServlet("/messages/*")
public final class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = -2769303530341933608L;
	private static final Map<Integer, Message> messages = Collections.synchronizedMap(new HashMap<>());
	private static final AtomicInteger ids = new AtomicInteger(1000);

	// http://120.0.0.1:8080/web/messages - add new message (POST)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String body = request.getReader().lines().collect(Collectors.joining());
		Message message = new Message(ids.incrementAndGet(), body);
		messages.put(message.getId(), message);
		
		String location = request.getRequestURL().append("/").append(message.getId()).toString();
		response.addHeader("Location", location);
		response.setStatus(SC_CREATED);
	}
	
	// http://120.0.0.1:8080/web/messages/1001	 		- message with id (GET)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] parts = request.getPathInfo().split("/");
		
		if(parts.length > 1) {
			Message message = messages.get(Integer.parseInt(parts[1]));
			if(message == null) { 
				response.setStatus(SC_NOT_FOUND);
			} else {
				response.getWriter().println(message.getContent());
			}
		} else {
			response.setStatus(SC_BAD_REQUEST);
		}
	}
	
	

}
