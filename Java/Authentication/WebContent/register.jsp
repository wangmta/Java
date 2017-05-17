<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
		Emp ID: <input type="text" name="empID" /> <br><br>
		Name: <input type="text" name="empName" /> <br><br>
		Salary: <input type="text" name="salary" /> <br><br>
		CNO: <input type="text" name="CNO" /> <br><br>
		<input type="submit" value="Register" name="submit" />
	</form>
	<br><br>
	<a href="login.jsp">Already have an account? Log In Here</a><br><br>
	<a href="index.jsp">Home</a>
</body>
</html>