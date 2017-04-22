package com.foxminded.task13.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.foxminded.task13.dao.ConnectionFactory;
import com.foxminded.task13.dao.ScheduleException;
import com.foxminded.task13.domain.Schedule;
import com.foxminded.task13.domain.SchedulePosition;

public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleServlet.class);

	public ScheduleServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Schedule schedule = new Schedule();
		List<SchedulePosition> events = null;
		try {
			events = schedule.getSchedule();
		} catch (ScheduleException e) {
			log.error("Can't retrieve schedule", e);
			throw new ServletException("Can't retrieve schedule");
		}

		request.setAttribute("schedule", events);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/showSchedule.jsp");
		dispatcher.forward(request, response);

	}

}
