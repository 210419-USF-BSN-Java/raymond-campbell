package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.User;

public class EmpAuthDelegate {
	
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = employeeDao.authenticateUser(username, password);
		
		if (u != null) {
			String token = u.getUserId() + ":" + u.getRoleId();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
		
	}
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		if(authToken != null) {
			String[] tokenArr = authToken.split(":");
			if (tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String userRoleStr =  tokenArr[1];
				User u = employeeDao.getUserById(Integer.parseInt(idStr));
				if(u != null && u.getRoleId() == Integer.parseInt(userRoleStr)) {
					return true;
				}
			}
		}
				
		return false;
	}

}
