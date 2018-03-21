<!DOCTYPE html>
<%@page import="com.jspiders.dto.StudentBean"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%StudentBean data = (StudentBean)session.getAttribute("data"); %>
<body>
<table class="head" border="1" width="100%">
	<tr>
		<th><a href="./bodyPage?page=Home"  class="text">Home</a></th>
		<%if(data.getIsAdmin().equals("Y")) { %>
		<th><a href="./bodyPage?page=CreateProfile" class="text">CreateProfile</a></th>
		<%} %>
		<th><a href="./bodyPage?page=ChangePassword" class="text">Change Password</a></th>
		<th><a href="./bodyPage?page=SearchStudent" class="text">Search Student</a></th>
		<th><a href="./bodyPage?page=Logout">Logout</a></th>
	</tr>
</table>
<br><br>
</body>
</html>






