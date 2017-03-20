<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!-- Imports Spring's general tab library -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
	<head>
		<title>Spittr</title>
		<link rel="stylesheet"
		type="text/css"
		href="<c:url value="/resources/style.css" />" >
	</head>
	<body>
	<!-- We'll start with the s:message tab -->
	<!-- Notice that the previous Welcome to Spittler text has been replaced with an s:message tab and a code parameter -->
	<!-- The s:message will render the text here from an available bean that implements the MessageSource interface -->
	<!-- Go to SpringWebConfig -->
		<h1><s:message code="spittr.welcome" /></</h1>
		<a href="<c:url value="/spittles" />">Spittles</a> |
		<a href="<c:url value="/spitter/register" />">Register</a>
		
		<c:forEach items="${spittleList}" var="spittle" >
			<li id="spittle_<c:out value="spittle.id"/>">
			<div class="spittleMessage">
			<c:out value="${spittle.message}" />
			</div>
			<div>
			<span class="spittleTime"><c:out value="${spittle.time}" /></span>
			<span class="spittleLocation">
			(<c:out value="${spittle.latitude}" />,
			<c:out value="${spittle.longitude}" />)</span>
			</div>
			</li>
		</c:forEach>
		
	</body>
</html>