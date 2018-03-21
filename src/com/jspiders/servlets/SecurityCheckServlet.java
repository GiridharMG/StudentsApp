package com.jspiders.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.SecurityQuestion;

public class SecurityCheckServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int regno = Integer.parseInt(req.getParameter("regno"));
		String q1 = req.getParameter("q1");
		String a1 = req.getParameter("a1");
		String q2 = req.getParameter("q2");
		String a2 = req.getParameter("a2");
		
		SecurityQuestion question1 = new SecurityQuestion();
		question1.setQuestion(q1);
		question1.setAnswer(a1);
		
		SecurityQuestion question2 = new SecurityQuestion();
		question2.setQuestion(q2);
		question2.setAnswer(a2);

		List<SecurityQuestion> list = new ArrayList<SecurityQuestion>();
		list.add(question1);
		list.add(question2);
		
		StudentDAO dao = ServiceGenrator.genrateDAO();
		boolean check = dao.securityCheck(list, regno);
		
		if(check)
			req.getRequestDispatcher("./ResetPassword.jsp").forward(req, resp);
		else
			resp.sendRedirect("./loginpage");
	}
}
