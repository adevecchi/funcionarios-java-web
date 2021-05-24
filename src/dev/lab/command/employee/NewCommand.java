package dev.lab.command.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;

import dev.lab.command.Command;
import dev.lab.models.Employee;
import dev.lab.dao.EmployeeDao;
import dev.lab.helpers.ProcessFileupload;
import dev.lab.helpers.json.Json;

public class NewCommand extends Command {
	
	public void process() throws ServletException, IOException {
		HashMap<String, String> params = new HashMap<String, String>();
		
		try {
			ProcessFileupload.fileupload(params, request, context);
			
			EmployeeDao dao = EmployeeDao.getInstance();
			Employee employee = new Employee();
			employee.setFirstName(params.get("firstName"));
			employee.setLastName(params.get("lastName"));
			employee.setDepartment(params.get("department"));
			employee.setEmail(params.get("email"));
			employee.setImage(params.get("image"));
			
			dao.put(employee);
			
			new Json(response).employee(true, "Novo funcionário adicionado com sucesso!");
		}
		catch (SQLException e) {
			new Json(response).employee(false, "Ocorreu uma falha para salvar.");
		}
		catch (Exception e) {
			new Json(response).employee(false, "Ocorreu uma falha no upload do arquivo.");
		}
	}

}
