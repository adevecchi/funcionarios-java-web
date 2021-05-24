package dev.lab.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		this.context = context;
		this.request = request;
		this.response = response;
	}
	
	public abstract void process() throws ServletException, IOException;
	
	protected void forward(String target) throws ServletException, IOException {
		target = String.format("/WEB-INF/jsp/%s.jsp", target);
		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
	
}
