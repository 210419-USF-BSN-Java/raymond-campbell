package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.User;

/**
 * Servlet implementation class EmployeeLogin
 */

public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLogin() {
        super();

    }
    
    EmployeeDao empDao = new EmployeeDaoImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

			User u = empDao.authenticateUser(username, password);

			if (u.getUserId() > 0) {
				String token = u.getUserId() + ":" + u.getRoleId();
				response.setStatus(200);
				response.setHeader("Authorization", token);
			} else {
				response.sendError(401);
			}
		}

		

	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		// check to see if there is an auth header
		if (authToken != null) {
			String[] tokenArr = authToken.split(":");
			// if the token is formatted the way we expect, we can take the id from it and
			// query for that user
			if (tokenArr.length == 2) {
				String idStr = tokenArr[0];
				String userRoleStr = tokenArr[1];

				// check to see if there is a valid user and if that user is the appropriate
				// role in their token
				User u = empDao.getUserById(Integer.parseInt(idStr));
				if (u != null && u.getRoleId() == (Integer.parseInt(userRoleStr))) {
					return true;
				}

			}
		}
		return false;
	}

}

