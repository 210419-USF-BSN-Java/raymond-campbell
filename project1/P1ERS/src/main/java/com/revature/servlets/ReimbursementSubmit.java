package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Reimbursement;

/**
 * Servlet implementation class ReimbursementSubmit
 */
public class ReimbursementSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDao empDao = new EmployeeDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		System.out.println("Inside sub servlet");

		String description = request.getParameter("description");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		System.out.println(description + amount + type);
//		Reimbursement draft = new Reimbursement(0,amount, description, 0, 0, 0, type);
//		boolean submitted = empDao.submitReimbRequest(draft);
//		
//		if (submitted) {
//			response.getWriter().println("Successful submission");
//			response.setStatus(200);
//		} else {
//			response.sendError(401);
//		}
	}    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Inside sub servlet");

		String description = request.getParameter("description");
		Integer amount = Integer.parseInt(request.getParameter("amount"));
		Integer type = Integer.parseInt(request.getParameter("type"));
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer authorId = Integer.parseInt(tokenArr[0]);
		
		
		System.out.println(description + amount + type + authorId);
		Reimbursement draft = new Reimbursement(0,amount, description, authorId, 0, 0, type);
		boolean submitted = empDao.submitReimbRequest(draft);
		
		if (submitted) {
			response.getWriter().println("Successful submission");
			response.setStatus(200);
		} else {
			response.sendError(401);
		}
	}    

}
