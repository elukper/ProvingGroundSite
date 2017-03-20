<!-- This file is used to provide comments for the form.jsp and to avoid errors -->

<!-- This declares that we will be using Spring formsbinding tag library for JSP -->
<!-- The 'prefix' parameter is variable, we could use e.g. 'form' -->
<!-- Check page 170 of Spring into Action 4 for a list of all 14 form-binding JSP tags -->
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
	<!-- Here, we apply the <sf: form> tag instead of the common HTML <form> tag -->
	<!-- The idea of the JSP form-tags is to direct the input data into a specific object, instead of leaving it to default, like in the Chapter5 examples -->
	<!-- We use the commandName to define the name of the object we wish to fill with the provided parameters. In this case, it's the Spitter object (notice it's spelled with a lowercase here -->
	<!-- Next, we need to modify the SpitterController method that handles the GET request with the /register value -->
	<!-- Go to SpitterController -->
	<sf:form method="POST" commandName="spitter">
	<!-- Next, the <input> tags are replaced with <sf: input> tags -->
	<!-- This renders the HTML <input> tag with the type=text, just like it was in Chapter5 -->
	<!-- But additionally, it also uses the path variable to bind it to the Spitter's variable of the same name -->
		<!-- Furthermore, in the SpitterController we inject an empty Spitter into the model. But were we to inject a Spitter with values set, then these values would be displayed here -->
		<!-- Now go to @RequestMapping(value="/register", method=RequestMethod.POST) in the SpitterController -->
		<!-- Also note that we can render errors from the BindingResult by using the sf:errors. The message will be automatically created for each annotation type in @Spitter-->
		<!-- Alternatively, you can customize error messages to be displayed here. Check page 175 of the Spring into Action, but it's not described properly and wont work-->
		First Name: <sf:input path="firstName" /><sf:errors path="firstName" cssClass="error" /><br/>
		Last Name: <sf:input path="lastName" /><sf:errors path="lastName" cssClass="error" /><br/>
		Username: <sf:input path="username" /><sf:errors path="username" cssClass="error" /><br/>
		Password: <sf:password path="password" /><sf:errors path="password" cssClass="error" /><br/>
		<!-- Same as for sf:input, this renders an HTML <input> tag with type set to <password> -->
		Password: <sf:password path="password" /><br/>
		<input type="submit" value="Register" />
	</sf:form>
	<!-- After all is done, go to spittles.jsp to see how Spring's General tab library works -->
</body>
</html>