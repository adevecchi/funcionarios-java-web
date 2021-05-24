package dev.lab.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dev.lab.helpers.json.Json;

public class UnknownCommand extends Command {
	
	public void process() throws ServletException, IOException {
		boolean isXHR = request.getHeader("X-Requested-With") != null ? true : false;
		
		if (isXHR) {
			new Json(response).employee(false, "Page Not Found");
		}
		else {
			forward("not-found");
		}
	}

}
