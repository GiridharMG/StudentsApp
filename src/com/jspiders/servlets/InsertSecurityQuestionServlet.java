package com.jspiders.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.SecurityQuestion;
import com.jspiders.dto.StudentBean;

public class InsertSecurityQuestionServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			String[] questions = req.getParameterValues("question");
			String[] answers = req.getParameterValues("answer");
			SecurityQuestion[] securityQuestions = new SecurityQuestion[questions.length];
			for (int i = 0; i < securityQuestions.length; i++) {
				securityQuestions[i] = new SecurityQuestion();
				securityQuestions[i].setQuestion_no(i);
				securityQuestions[i].setQuestion(questions[i]);
				securityQuestions[i].setAnswer(answers[i]);
			}
			StudentBean data = (StudentBean) session.getAttribute("data");
			StudentDAO dao = ServiceGenrator.genrateDAO();
			dao.insertSecurityQuestions(data, securityQuestions);
			resp.sendRedirect("./bodyPage?page=Home");
		}
	}
}
