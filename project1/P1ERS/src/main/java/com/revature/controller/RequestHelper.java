package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.EmpAuthDelegate;
import com.revature.delegates.UserDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	private EmpAuthDelegate authDelegate = new EmpAuthDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	private UserDelegate userDelegate = new UserDelegate();
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.startsWith("/api/")) {
			if(!authDelegate.isAuthorized(request)) {
				response.sendError(401);
				return;
			}
		
		
		String record = path.substring(5);
		if(record.startsWith("users")) {
			userDelegate.getUsers(request, response);
		} else {
			response.sendError(404, "Request Record(s) Not Found");
		}
		
		}else {
		viewDelegate.resolveView(request, response);
	}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/login":
			authDelegate.authenticate(request, response);
			break;
		default:
			response.sendError(405);
		}
	}
}
	

