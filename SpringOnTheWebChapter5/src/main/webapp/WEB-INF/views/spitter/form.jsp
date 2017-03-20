<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
	<h1>Register</h1>
	<!-- Here we have the <form> tag, which is part of the HTML code -->
	<!-- What's important to notice is the method="POST". This means that when this form is filled and sent, it will be sent as an HTTP POST message -->
	<!-- Therefore, we need a method to handle this in the SpitterController.java -->
	<form method="POST" >
		First Name: <input type="text" name="firstName" /><br/>
		Last Name: <input type="text" name="lastName" /><br/>
		Username: <input type="text" name="username" /><br/>
		Password: <input type="password" name="password" /><br/>
	<input type="submit" value="Register" />
</form>

</body>
</html>