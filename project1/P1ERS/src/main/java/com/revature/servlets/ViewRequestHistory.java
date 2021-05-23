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
 * Servlet implementation class ViewRequestHistory
 */
public class ViewRequestHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ManagerDao manDao = new ManagerDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRequestHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside the GET pending servlet");
		
		Integer empId = Integer.parseInt(request.getHeader("employeeID"));	
		List<Reimbursement> history = manDao.viewReimbRequestById(empId);
		
		if(history.size()>0) {
		String pendingList = mapper.writeValueAsString(history);
		response.setStatus(200);
		response.setHeader("requestList", pendingList);
	} else {
		response.sendError(401);
	}

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside the Post pending servlet");
		
		Integer empId = Integer.parseInt(request.getParameter("employeeId"));	
		List<Reimbursement> history = manDao.viewReimbRequestById(empId);
		
		if(history.size()>0) {
		String pendingList = mapper.writeValueAsString(history);
		response.setStatus(200);
		response.setHeader("requestList", pendingList);
	} else {
		response.sendError(401);
	}

}
}

