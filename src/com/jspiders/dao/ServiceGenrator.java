package com.jspiders.dao;

public class ServiceGenrator {
	private ServiceGenrator() {}
	
	public static StudentDAO genrateDAO() {
		try {
			return (StudentDAO)Class.forName("com.jspiders.dao.JdbcImpl").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
