package dev.lab.command.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;

import dev.lab.command.Command;
import dev.lab.models.Employee;
import dev.lab.dao.EmployeeDao;
import dev.lab.helpers.json.Json;
import dev.lab.helpers.ProcessFileupload;

public class UpdateCommand extends Command {
	
	public void process() throws ServletException, IOException {
		boolean notFile = false;
		HashMap<String, String> params = new HashMap<String, String>();
		
		try {
			notFile = ProcessFileupload.fileupload(params, request, context);
			
			EmployeeDao dao = EmployeeDao.getInstance();
			Employee employee = new Employee();
			employee.setId(Integer.parseInt(params.get("id")));
			employee.setFirstName(params.get("firstName"));
			employee.setLastName(params.get("lastName"));
			employee.setDepartment(params.get("department"));
			employee.setEmail(params.get("email"));
			if (!notFile) {
				employee.setImage(params.get("image"));
			}
			else {
				employee.setImage(params.get("old_image"));
			}
			
			dao.update(employee);
			
			new Json(response).employee(true, "Funcionário atualizado com sucesso!");
		}
		catch (SQLException e) {
			new Json(response).employee(false, "Ocorreu uma falha na atualização.");
		}
		catch (Exception e) {
			new Json(response).employee(false, "Ocorreu uma falha no upload do arquivo.");
		}
	}

}
