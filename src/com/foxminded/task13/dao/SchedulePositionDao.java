package com.foxminded.task13.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.foxminded.task13.domain.SchedulePosition;

public class SchedulePositionDao implements GenericDao<SchedulePosition, Long> {

	private static final Logger log = Logger.getLogger(SchedulePositionDao.class);
	private final String CREATE = "INSERT INTO Schedule (weekDay, time, event) VALUES (?, ?, ?) ON CONFLICT (weekDay, time, event) DO UPDATE SET weekDay = excluded.weekDay, time = excluded.time, event = excluded.event;";
	private final String GET_ALL = "SELECT * FROM Schedule ORDER BY id;";

	@Override
	public List<SchedulePosition> getAll() throws ScheduleException {
		List<SchedulePosition> schedule = new ArrayList<SchedulePosition>();
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
				Map<Integer, SchedulePosition> positionsSet = new TreeMap<Integer, SchedulePosition>();
				positionsSet.put((int) id, schedulePosition);
				for (Integer position : positionsSet.keySet()) {
					schedule.add(positionsSet.get(position));
				}
			}
		} catch (SQLException e) {
			log.error("Can't retrieve schedule from DB", e);
			throw new ScheduleException("Can't retrieve schedule from DB", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return schedule;
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
