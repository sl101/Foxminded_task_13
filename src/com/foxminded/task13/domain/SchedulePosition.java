package com.foxminded.task13.domain;

public class SchedulePosition {
	private long id;
	private String weekDay;
	private String time;
	private String event;

	public SchedulePosition() {
	}

	public SchedulePosition(String weekDay, String time, String event) {
		this.weekDay = weekDay;
		this.time = time;
		this.event = event;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
