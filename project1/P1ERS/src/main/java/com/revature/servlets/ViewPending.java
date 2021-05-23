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
 * Servlet implementation class viewPending
 */
public class ViewPending extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmployeeDao empDao = new EmployeeDaoImpl();
	ObjectMapper mapper = new ObjectMapper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside pending servlet");
		
		String token = request.getParameter("empId");
		String[] tokenArr = token.split(":");
		Integer empId = Integer.parseInt(tokenArr[0]);	
		List<Reimbursement> pending = empDao.viewPendingReimb(empId);
		
		if(pending.size()>0) {
		String pendingList = mapper.writeValueAsString(pending);
		response.setStatus(200);
		response.setHeader("PendingList", pendingList);
	} else {
		response.sendError(401);
	}

}
}
