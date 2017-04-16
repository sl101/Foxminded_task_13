<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.foxminded.task13.domain.Schedule"%>
<%@ page import="com.foxminded.task13.domain.SchedulePosition"%>
<%@ page import="java.util.List"%>
<%@ page import="com.foxminded.task13.dao.ScheduleException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Schedule schedule = new Schedule();
		List<SchedulePosition> scheduleArray = null;
		StringBuilder scheduleList = new StringBuilder();
		try {
			scheduleArray = schedule.getSchedule();
		} catch (ScheduleException e) {
			
			throw new ScheduleException("Can't get data for jsp", e);
		}
		scheduleList.append("<table width=50% border=0 cellspacing=0>");
		for (int i = 0; i < scheduleArray.size(); i++) {
			SchedulePosition position = scheduleArray.get(i);			
			scheduleList.append("<tr><td>"+ position.getWeekDay() + " </td>"
					+"<td> " + position.getTime() + "</td>"
					+"<td>"	+ position.getEvent() + "</td></tr>");
		}
		scheduleList.append("</tr></table>");
		%>
	
	<%=scheduleList %>
</body>
</html>