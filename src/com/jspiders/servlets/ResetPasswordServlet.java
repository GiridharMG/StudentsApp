package com.jspiders.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;

public class ResetPasswordServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int regno = Integer.parseInt(req.getParameter("regno"));
		String password = req.getParameter("password");
		String conform = req.getParameter("conformpassword");
		
		if(password.equals(conform)) {
			StudentDAO dao = ServiceGenrator.genrateDAO();
			dao.resetPassword(regno, password);
		}
		req.getServletContext().setAttribute("msg", "Password updated Plz login with new password");
		resp.sendRedirect("./loginpage");
	}
}
