package com.foxminded.task13.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.task13.dao.ScheduleException;
import com.foxminded.task13.domain.Schedule;
import com.foxminded.task13.domain.SchedulePosition;

public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScheduleServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Schedule schedule = new Schedule();
		List<SchedulePosition> scheduleArray = null;
		StringBuilder scheduleList = new StringBuilder();
		try {
			scheduleArray = schedule.getSchedule();
		} catch (ScheduleException e) {
			e.printStackTrace();
		}
		scheduleList.append("<table width=50% border=0 cellspacing=0>");
		for (int i = 0; i < scheduleArray.size(); i++) {
			SchedulePosition position = scheduleArray.get(i);
			scheduleList.append("<tr><td>" + position.getWeekDay() + " </td>" + "<td> " + position.getTime() + "</td>"
					+ "<td>" + position.getEvent() + "</td></tr>");
		}
		scheduleList.append("</tr></table>");

		PrintWriter out = response.getWriter();
		out.write(scheduleList.toString());
		out.flush();
		out.close();

	}

}
