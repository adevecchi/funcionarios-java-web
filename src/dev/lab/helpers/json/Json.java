package dev.lab.helpers.json;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dev.lab.models.Employee;

public class Json {
	
	private Gson gson;
	private PrintWriter out;
	private HttpServletResponse response;
	
	public Json(HttpServletResponse response) {
		this.response = response;
		this.response.setContentType("application/json");
		
		gson = new Gson();
		
		try {
			out = this.response.getWriter();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void employee(boolean status, Employee employee) {
		EmployeeJson json = new EmployeeJson();
		
		json.setStatus(status);
		json.setEmployee(employee);
		
		sendResponse(gson.toJson(json));
	}
	
	public void employee(boolean status, String message) {
		EmployeeJson json = new EmployeeJson();
		
		json.setStatus(status);
		json.setMessage(message);
		
		sendResponse(gson.toJson(json));
	}
	
	public void employee(int draw, int recordsTotal, List<Employee> data) {
		EmployeeJsonDatatable json = new EmployeeJsonDatatable();
		
		json.setDraw(draw);
		json.setRecordsTotal(recordsTotal);
		json.setRecordsFiltered(recordsTotal);
		json.setData(data);
		
		sendResponse(gson.toJson(json));
	}
	
	private void sendResponse(String response) {
		out.print(response);
		out.flush();
	}
	
}
