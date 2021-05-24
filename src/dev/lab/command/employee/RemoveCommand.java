package dev.lab.command.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import dev.lab.command.Command;
import dev.lab.dao.EmployeeDao;
import dev.lab.helpers.json.Json;


public class RemoveCommand extends Command {

	public void process() throws ServletException, IOException {
		try {
			EmployeeDao dao = EmployeeDao.getInstance();
			
			dao.remove(Integer.parseInt(request.getParameter("id")));
			
			new Json(response).employee(true, "Funcionário removido com sucesso!");
		}
		catch (SQLException e) {
			new Json(response).employee(false, "Ocorreu uma falha para excluir.");
		}
	}
	
}
