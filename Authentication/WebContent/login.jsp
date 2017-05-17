<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log IN</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<form action="LoginServlet" method="post">
  <div class="form-group">
    <label for="id">Enter ID:</label>
    <input type="text" name="enterID" id="id"/>
  </div>
  <div class="form-group">
    <label for="Name">Name:</label>
    <input type="text" name="yourName" id="Name"/> 
  </div>
  <input type="submit" class="btn btn-primary" name="submit" value="Login">
</form>
<a href="register.jsp">Register</a>

</body>
</html>