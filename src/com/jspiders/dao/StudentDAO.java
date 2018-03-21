package com.jspiders.dao;

import java.util.List;

import com.jspiders.dto.SecurityQuestion;
import com.jspiders.dto.StudentBean;

public interface StudentDAO {
	public boolean checkAttempt(int regno);
	
	public StudentBean athenticate(int regno, String password);
	
	public boolean createProfile(StudentBean data);
	
	public StudentBean getStudentDetails(int regno);
	
	public List<StudentBean> getAllStudentDetails();

	public void deleteStudent(int regno);
	
	public boolean changePassword(int regno, String oldPassword, String newPassword);
	
	public void updateStudent(StudentBean data);
	
	public List<SecurityQuestion> securityQuection(int regno);
	
	public void insertSecurityQuestions(StudentBean data, SecurityQuestion[] questions);
	
	public void resetAttempt(int regno);
	
	public boolean securityCheck(List<SecurityQuestion>list, int regno);
	
	public void resetPassword(int regno, String password);
}
