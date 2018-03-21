package com.jspiders.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchEngineServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String search = req.getParameter("search");
		
		String url = "";
		
		if(search.equals("google")) {
			url = "https://www.google.co.in/search"
					+ "?q="+key;
		} else if(search.equals("bing")) {
			url = "https://www.bing.com/search"
					+ "?q="+key;
		} else {
			url = "https://www.grainger.com/"
					+ "search?searchQuery="+key;
		}
		resp.sendRedirect(url);
	}
}










