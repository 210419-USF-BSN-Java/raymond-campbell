package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;

/**
 * Servlet implementation class DenyRequest
 */
public class DenyRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerDao manDao = new ManagerDaoImpl();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DenyRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Inside deny servlet");
		Integer requestId = Integer.parseInt(request.getParameter("requestId"));
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer authorId = Integer.parseInt(tokenArr[0]);
	
		boolean submitted = manDao.denyReimbRequest(authorId, requestId);
		
		if (submitted) {
			response.getWriter().println("Successful submission");
			response.setStatus(200);
		} else {
			response.sendError(401);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Inside deny servlet");
		Integer requestId = Integer.parseInt(request.getParameter("requestId"));
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer authorId = Integer.parseInt(tokenArr[0]);
	
		boolean submitted = manDao.denyReimbRequest(authorId, requestId);
		
		if (submitted) {
			response.getWriter().println("Successful submission");
			response.setStatus(200);
		} else {
			response.sendError(401);
		}
	} 

}
