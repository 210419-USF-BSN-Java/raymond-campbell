package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.models.User;

/**
 * Servlet implementation class ViewAllEmployees
 */
public class ViewAllEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ManagerDao manDao = new ManagerDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllEmployees() {
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
		System.out.println("Inside view all employees servlet");
		
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer empId = Integer.parseInt(tokenArr[0]);	
		List<User> employeeList = manDao.viewAllEmployees();
		
		if(employeeList.size()>0) {
		String EmployeeList = mapper.writeValueAsString(employeeList);
		response.setStatus(200);
		response.setHeader("EmployeeList", EmployeeList);
	} else {
		response.sendError(401);
	}

}

}
