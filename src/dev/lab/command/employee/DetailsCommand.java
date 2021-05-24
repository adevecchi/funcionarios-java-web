package dev.lab.command.employee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import dev.lab.command.Command;
import dev.lab.dao.EmployeeDao;
import dev.lab.models.Employee;
import dev.lab.helpers.json.Json;

public class DetailsCommand extends Command{

	public void process() throws ServletException, IOException {
		try {
			EmployeeDao dao = EmployeeDao.getInstance();
			Employee employee = dao.getEmployee(Integer.parseInt(request.getParameter("id")));
			
			new Json(response).employee(true, employee);
		}
		catch (SQLException e) {
			new Json(response).employee(false, "Não foi possível retornar Detalhes do funcionário");
		}
	}
	
}
