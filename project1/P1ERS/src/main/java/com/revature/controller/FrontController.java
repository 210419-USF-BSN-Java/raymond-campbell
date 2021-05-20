package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
@WebServlet("/home")
public class FrontController extends DefaultServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RequestHelper requestHelper = new RequestHelper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/static/") || path.equals("/") || path.equals("/index.html")) {
			super.doGet(request, response);
		} else {
			requestHelper.processGet(request, response);
		}
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHelper.processPost(request, response);
	}

}
