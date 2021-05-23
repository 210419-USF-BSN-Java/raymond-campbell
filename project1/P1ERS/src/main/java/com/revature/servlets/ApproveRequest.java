package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.models.Reimbursement;

/**
 * Servlet implementation class ApproveRequest
 */
public class ApproveRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ManagerDao manDao = new ManagerDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveRequest() {
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

		System.out.println("Inside approve servlet");
		Integer requestId = Integer.parseInt(request.getParameter("requestId"));
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer authorId = Integer.parseInt(tokenArr[0]);
	
		boolean submitted = manDao.approveReimbRequest(authorId, requestId);
		
		if (submitted) {
			response.getWriter().println("Successful submission");
			response.setStatus(200);
		} else {
			response.sendError(401);
		}
	}    


}
