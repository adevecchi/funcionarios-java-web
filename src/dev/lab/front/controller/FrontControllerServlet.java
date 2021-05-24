package dev.lab.front.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.lab.command.Command;
import dev.lab.command.UnknownCommand;

public class FrontControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> mapPath;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		mapPath = new HashMap<String, String>();
		mapPath.put("home", "home");
		mapPath.put("funcionarios", "employee");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Command command = getCommand(request);
		command.init(getServletContext(), request, response);
		command.process();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private Command getCommand(HttpServletRequest request) throws ServletException, IOException {
		String path = request.getServletPath();
		String command = request.getParameter("cmd") != null ? request.getParameter("cmd") : "Index";
		
		Pattern pattern = Pattern.compile("^/(.*?)/?$");
		Matcher matcher = pattern.matcher(path);
		
		path =  (matcher.matches() && matcher.group(1).length() > 0) ? matcher.group(1) : "home";
		
		path = mapPath.containsKey(path.toLowerCase()) ? mapPath.get(path.toLowerCase()) : "invalid";
		
		try {
			Class<?> type = Class.forName(String.format("dev.lab.command.%s.%sCommand", path, command));
			
			return (Command) type.asSubclass(Command.class).newInstance();
		}
		catch (Exception e) {
			return new UnknownCommand();
		}
	}

}
