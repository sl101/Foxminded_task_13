package com.foxminded.task13.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.task13.domain.SchedulePosition;

public class SchedulePositionDao implements GenericDao<SchedulePosition, Long>{
	
	private static final Logger log = Logger.getLogger(SchedulePositionDao.class);
	private final String CREATE = "INSERT INTO Schedule (weekDay, time, event) VALUES (?, ?, ?) ON CONFLICT (weekDay, time, event) DO UPDATE SET weekDay = excluded.weekDay, time = excluded.time, event = excluded.event;";
	private final String GET_ALL = "SELECT * FROM Schedule ORDER BY id;";
	private final String GET_BY_ID = "SELECT * FROM Schedule WHERE id = ?;";
	private final String UPDATE = "UPDATE Schedule SET weekDate = ?, time = ?, event = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Schedule WHERE id = ?;";

	@Override
	public Set<SchedulePosition> getAll() throws ScheduleException {
		Set<SchedulePosition> schedule = new HashSet<SchedulePosition>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String weekDay = resultSet.getString("weekDay");
				String time = resultSet.getString("time");
				String event = resultSet.getString("event");
				SchedulePosition schedulePosition = new SchedulePosition(weekDay, time, event);
				long id = resultSet.getLong("id");
				schedulePosition.setId(id);
				schedule.add(schedulePosition);
			}
		} catch (SQLException e) {
			log.error("Problem with getting data", e);
			throw new ScheduleException("Problem with getting data", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
//		Iterator<SchedulePosition> iterator = schedule.iterator();
//		while(iterator.hasNext()){
//			SchedulePosition position = iterator.next();
//			System.out.println(position.getId()+" "+position.getWeekDay()+" "+position.getTime()+" "+position.getEvent());
//		}
		return schedule;
	}

	@Override
	public SchedulePosition getById(Long id) throws ScheduleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchedulePosition update(SchedulePosition entity) throws ScheduleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SchedulePosition id) throws ScheduleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(SchedulePosition schedulePosition) throws ScheduleException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, schedulePosition.getWeekDay());
			statement.setString(2, schedulePosition.getTime());
			statement.setString(3, schedulePosition.getEvent());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			schedulePosition.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			log.error("Can't save schedule position", e);
			throw new ScheduleException("Can't save schedule position", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		
	}

}
