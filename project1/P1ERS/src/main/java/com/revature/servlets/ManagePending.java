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
 * Servlet implementation class ManagePending
 */
public class ManagePending extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ManagerDao manDao = new ManagerDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagePending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside manage pending servlet");
		
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer empId = Integer.parseInt(tokenArr[0]);	
		List<Reimbursement> managePending = manDao.viewAllReimbRequests();
		
		if(managePending.size()>0) {
		String managePendingList = mapper.writeValueAsString(managePending);
		response.setStatus(200);
		response.setHeader("ManagePendingList", managePendingList);
	} else {
		response.sendError(401);
	}

}
}
