package com.jspiders.dto;

public class StudentBean {
	private int regno;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gFirstName;
	private String gMiddleName;
	private String gLastName;
	private String isAdmin;
	private String password;
	private String securityCheck;
	private int attempt;
	
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	public int getRegno() {
		return regno;
	}
	public void setRegno(int regno) {
		this.regno = regno;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getgFirstName() {
		return gFirstName;
	}
	public void setgFirstName(String gFirstName) {
		this.gFirstName = gFirstName;
	}
	public String getgMiddleName() {
		return gMiddleName;
	}
	public void setgMiddleName(String gMiddleName) {
		this.gMiddleName = gMiddleName;
	}
	public String getgLastName() {
		return gLastName;
	}
	public void setgLastName(String gLastName) {
		this.gLastName = gLastName;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecurityCheck() {
		return securityCheck;
	}
	public void setSecurityCheck(String securityCheck) {
		this.securityCheck = securityCheck;
	}
	
}
