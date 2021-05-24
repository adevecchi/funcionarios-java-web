package dev.lab.helpers.json;

import java.util.List;

import dev.lab.models.Employee;

public class EmployeeJsonDatatable {
	
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<Employee> data;
	
	public int getDraw() {
		return draw;
	}
	
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public int getRecordsTotal() {
		return recordsTotal;
	}
	
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	public List<Employee> getData() {
		return data;
	}
	
	public void setData(List<Employee> data) {
		this.data = data;
	}

}
