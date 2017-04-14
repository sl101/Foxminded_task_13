package com.foxminded.task13.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.task13.dao.ScheduleException;
import com.foxminded.task13.domain.Schedule;
import com.foxminded.task13.domain.SchedulePosition;

public class ScheduleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Schedule schedule = new Schedule();
		
//		SchedulePosition schedulePosition1 = new SchedulePosition("monday", "06:30", "wakeUp");
//		try {
//			schedule.addSchedulePosition(schedulePosition1);
//		} catch (ScheduleException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		SchedulePosition schedulePosition2 = new SchedulePosition("monday", "07:00", "breakfast");
//		try {
//			schedule.addSchedulePosition(schedulePosition2);
//		} catch (ScheduleException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		StringBuilder scheduleList = new StringBuilder();
		try {
			List<SchedulePosition> scheduleArray = schedule.getSchedule();
			
			for (int i = 0; i < scheduleArray.size(); i++) {
				SchedulePosition position = scheduleArray.get(i);
				scheduleList.append(position.getWeekDay() + "\t");
				scheduleList.append(position.getTime() + "\t");
				scheduleList.append(position.getEvent() + "<br>");
			}
		} catch (ScheduleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PrintWriter out = response.getWriter();
		out.println(scheduleList);
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
