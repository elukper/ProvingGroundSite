<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

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
	<sf:form method="POST" commandName="spitter">
		First Name: <sf:input path="firstName" /><sf:errors path="firstName" cssClass="error" /><br/>
		Last Name: <sf:input path="lastName" /><sf:errors path="lastName" cssClass="error" /><br/>
		Username: <sf:input path="username" /><sf:errors path="username" cssClass="error" /><br/>
		Password: <sf:password path="password" /><sf:errors path="password" cssClass="error" /><br/>
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>