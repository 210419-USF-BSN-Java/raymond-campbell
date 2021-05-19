package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.User;

public class UserDelegate {
	
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		if(requestPath.length()== "/api/users".length()) {
			List<User> users =  employeeDao.viewAllEmployees();
			try (PrintWriter pw = response.getWriter();) {
				pw.write(new ObjectMapper().writeValueAsString(users));
			}
		} else {
			String idStr = request.getServletPath().substring(11);
			System.out.println(idStr);
			User u = employeeDao.getUserById(Integer.parseInt(idStr));
			if (u == null) {
				response.sendError(404, "No user with given ID");
			} else {
				try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(u));
				}
			}
		}
	}
}
