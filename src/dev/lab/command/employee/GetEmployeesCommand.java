package dev.lab.command.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import dev.lab.command.Command;
import dev.lab.dao.EmployeeDao;
import dev.lab.models.Employee;

import dev.lab.helpers.json.Json;

public class GetEmployeesCommand extends Command {

	public void process() throws ServletException, IOException {
		try {
			EmployeeDao dao = EmployeeDao.getInstance();
			
			String search = request.getParameter("search[value]");
			int draw = Integer.parseInt(request.getParameter("draw"));
			int start = Integer.parseInt(request.getParameter("start"));
			int length = Integer.parseInt(request.getParameter("length"));
			
			int recordsTotal = dao.getRecordsTotal(search);
			
			List<Employee> employees = dao.getEmployees(search, length, start);
			
			new Json(response).employee(draw, recordsTotal, employees);
		}
		catch (SQLException e) {
			new Json(response).employee(false, "Não foi possível retornar lista dos funcionários.");
		}
	}
	
}
