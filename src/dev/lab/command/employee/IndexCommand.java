package dev.lab.command.employee;

import java.io.IOException;

import javax.servlet.ServletException;

import dev.lab.command.Command;

public class IndexCommand extends Command {
	
	public void process() throws ServletException, IOException {
			forward("funcionario/index");
	}

}
