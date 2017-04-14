package com.foxminded.task13.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.foxminded.task13.dao.ScheduleException;
import com.foxminded.task13.dao.SchedulePositionDao;

public class Schedule {
	
	List<SchedulePosition> scheduleList = new ArrayList();
	
	public Schedule(){
	
	}


	public void addSchedulePosition(SchedulePosition schedulePosition) throws ScheduleException{
		SchedulePositionDao dao = new SchedulePositionDao();
		dao.create(schedulePosition);
		scheduleList.add(schedulePosition);
	}
	
	public List<SchedulePosition> getSchedule() throws ScheduleException {
		SchedulePositionDao dao = new SchedulePositionDao();
		scheduleList = new ArrayList<>(dao.getAll());
		return scheduleList;
	}

	public void setSchedule(List<SchedulePosition> schedule) {
		this.scheduleList = schedule;
	}
	
	
}
