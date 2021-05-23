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

/**
 * Servlet implementation class ViewResolved
 */
public class ViewResolved extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDao empDao = new EmployeeDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewResolved() {
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
		
		System.out.println("Inside Resolved Servlet");
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer empId = Integer.parseInt(tokenArr[0]);	
		List<Reimbursement> pending = empDao.viewResolvedReimb(empId);
		
		if(pending.size()>0) {
		System.out.println("Successfully acquired resolved list");
		String resolvedList = mapper.writeValueAsString(pending);
		response.setStatus(200);
		response.setHeader("ResolvedList", resolvedList);
	} else {
		response.sendError(401);
	}

}
}
