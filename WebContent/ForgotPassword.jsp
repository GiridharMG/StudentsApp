<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
	<legend>Forgot Password</legend>
	<form action="./forgot">
		<table>
			<tr>
				<td>Regno:</td>
				<td><input type="number" name="regno" required="required">
			</tr>
			<tr>
				<td><input type="submit" value="Reset Password">
			</tr>
		</table>	
	</form>
	</fieldset>
</body>
</html>