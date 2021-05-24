package dev.lab.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.lab.models.Employee;

public class EmployeeDao {
	
	private static EmployeeDao instance = null;
	
	private static final String driver = "org.postgresql.Driver";
	private static final String url = "jdbc:postgresql://localhost/dvq_employee";
	private static final String user = "admin";
	private static final String pass = "GataXux@1";
	
	private Connection conn = null;
	private PreparedStatement pstmtGet;
	private PreparedStatement pstmtPut;
	private PreparedStatement pstmtRem;
	private PreparedStatement pstmtUpd;
	private PreparedStatement pstmtAll;
	private PreparedStatement pstmtTotal;
	
	private EmployeeDao() throws SQLException {
		String get = "SELECT * FROM employees WHERE id = ?";
		String put = "INSERT INTO employees VALUES(nextval('employees_seq'),?,?,?,?,?)";
		String rem = "DELETE FROM employees WHERE id = ?";
		String upd = "UPDATE employees SET fname=?, lname=?, department=?, email=?, image=? WHERE id = ?";
		String all = "SELECT * FROM employees WHERE CAST(id AS VARCHAR) ILIKE ? OR fname ILIKE ? OR lname ILIKE ? OR department ILIKE ? OR email ILIKE ? ORDER BY id LIMIT ? OFFSET ?";
		String total = "SELECT COUNT(0) FROM employees WHERE CAST(id AS VARCHAR) ILIKE ? OR fname ILIKE ? OR lname ILIKE ? OR department ILIKE ? OR email ILIKE ?";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmtGet = conn.prepareStatement(get);
			pstmtPut = conn.prepareStatement(put);
			pstmtRem = conn.prepareStatement(rem);
			pstmtUpd = conn.prepareStatement(upd);
			pstmtAll = conn.prepareStatement(all);
			pstmtTotal = conn.prepareStatement(total);
		}
		catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public static EmployeeDao getInstance() throws SQLException {
		if (instance == null)
			instance = new EmployeeDao();
		return instance;
	}
	
	private Employee makeEmployee(ResultSet rs) throws SQLException {
		try {
			Employee emp = new Employee();
			
			emp.setId(rs.getInt("id"));
			emp.setFirstName(rs.getString("fname"));
			emp.setLastName(rs.getString("lname"));
			emp.setDepartment(rs.getString("department"));
			emp.setEmail(rs.getString("email"));
			emp.setImage(rs.getString("image"));
			
			return emp;
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public int getRecordsTotal(String search) throws SQLException {
		try {
			ResultSet rs;
			synchronized (pstmtTotal) {
				pstmtTotal.clearParameters();
				pstmtTotal.setString(1, "%"+search+"%");
				pstmtTotal.setString(2, "%"+search+"%");
				pstmtTotal.setString(3, "%"+search+"%");
				pstmtTotal.setString(4, "%"+search+"%");
				pstmtTotal.setString(5, "%"+search+"%");
				rs = pstmtTotal.executeQuery();
			}
			int total = 0;
			if (rs.next())
				total = rs.getInt(1);
			return total;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}
	}
	
	public Employee getEmployee(int id) throws SQLException {
		try {
			ResultSet rs;
			synchronized (pstmtGet) {
				pstmtGet.setInt(1, id);
				rs = pstmtGet.executeQuery();
			}
			if (rs.next())
				return makeEmployee(rs);
			return null;
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public List<Employee> getEmployees(String search, int records, int offset) throws SQLException {
		try {
			ResultSet rs;
			List<Employee> emps = new ArrayList<Employee>();
			synchronized (pstmtAll) {
				pstmtAll.clearParameters();
				pstmtAll.setString(1, "%"+search+"%");
				pstmtAll.setString(2, "%"+search+"%");
				pstmtAll.setString(3, "%"+search+"%");
				pstmtAll.setString(4, "%"+search+"%");
				pstmtAll.setString(5, "%"+search+"%");
				pstmtAll.setInt(6, records);
				pstmtAll.setInt(7, offset);
				rs = pstmtAll.executeQuery();
			}
			while (rs.next()) {
				emps.add(makeEmployee(rs));
			}
			return emps;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}
	}
	
	public boolean update(Employee emp) throws SQLException {
		int rowsAffected;
		try {
			synchronized (pstmtUpd) {
				pstmtUpd.clearParameters();
				pstmtUpd.setString(1, emp.getFirstName());
				pstmtUpd.setString(2, emp.getLastName());
				pstmtUpd.setString(3, emp.getDepartment());
				pstmtUpd.setString(4, emp.getEmail());
				pstmtUpd.setString(5, emp.getImage());
				pstmtUpd.setInt(6, emp.getId());
				
				rowsAffected = pstmtUpd.executeUpdate();
			}
			return rowsAffected > 0;
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public boolean put(Employee emp) throws SQLException {
		int rowAffected;
		try {
			synchronized (pstmtPut) {
				pstmtPut.clearParameters();
				pstmtPut.setString(1, emp.getFirstName());
				pstmtPut.setString(2, emp.getLastName());
				pstmtPut.setString(3, emp.getDepartment());
				pstmtPut.setString(4, emp.getEmail());
				pstmtPut.setString(5, emp.getImage());
				rowAffected = pstmtPut.executeUpdate();
			}
			return rowAffected > 0;
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public boolean remove(int id) throws SQLException {
		int rowAffected;
		try {
			synchronized (pstmtRem) {
				pstmtRem.clearParameters();
				pstmtRem.setInt(1, id);
				
				rowAffected = pstmtRem.executeUpdate();
			}
			return rowAffected > 0;
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public void destroy() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {}
		}
	}

}
