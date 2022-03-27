package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.model.CourseModel;

@WebServlet(urlPatterns = {
		"/",
		"/courses",
		"/course-edit",
		"/course-save"
})
public class CourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CourseModel model;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var page = switch(req.getServletPath()) {
		case "/course-edit" -> "/course-edit.jsp";
		default -> {
			// Load Course and set result to request scope
			yield "/index.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
