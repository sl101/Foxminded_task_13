package com.foxminded.task13.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.foxminded.task13.dao.ScheduleException;
import com.foxminded.task13.dao.SchedulePositionDao;

public class Schedule {
	
	List<SchedulePosition> events = new ArrayList();
	
	public Schedule(){
	
	}


	public void addSchedulePosition(SchedulePosition events) throws ScheduleException{
		SchedulePositionDao dao = new SchedulePositionDao();
		dao.create(events);
		this.events.add(events);
	}
	
	public List<SchedulePosition> getSchedule() throws ScheduleException {
		SchedulePositionDao dao = new SchedulePositionDao();
		events = dao.getAll();
		return events;
	}

	public void setSchedule(List<SchedulePosition> events) {
		this.events = events;
	}
	
	
}
