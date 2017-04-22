<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show schedule</title>
</head>

<body>

	<h3>Day schedule</h3>
	<table width=50% border=0 cellspacing=0>

		<c:forEach var="item" items="${schedule}">
			<tr>
				<td>${item.weekDay}</td>
				<td>${item.time}</td>
				<td>${item.event}</td>
			</tr>


		</c:forEach>
	</table>
</body>
</html>