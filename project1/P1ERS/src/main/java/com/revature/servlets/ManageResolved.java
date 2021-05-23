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
import com.revature.models.Reimbursement;

/**
 * Servlet implementation class ManageResolved
 */
public class ManageResolved extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerDao manDao = new ManagerDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageResolved() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside Manager Resolved Servlet");	
		List<Reimbursement> resolved = manDao.viewResolvedRequests();
		
		if(resolved.size()>0) {
		System.out.println("Successfully acquired resolved list");
		String resolvedList = mapper.writeValueAsString(resolved);
		response.setStatus(200);
		response.setHeader("ResolvedList", resolvedList);
	} else {
		response.sendError(401);
	}

}
}
