package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.User;

/**
 * Servlet implementation class ViewAccount
 */
public class ViewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDao empDao = new EmployeeDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside sub servlet");
		
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer empId = Integer.parseInt(tokenArr[0]);	
		User account = empDao.getUserById(empId);
		
		if(account != null) {
		String userAccount = mapper.writeValueAsString(account);
		response.setStatus(200);
		response.setHeader("UserAccount", userAccount);
	} else {
		response.sendError(401);
	}

}
}

