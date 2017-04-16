package com.foxminded.task13.dao;

import java.util.List;

public interface GenericDao<T,E>{
	
	public List<T> getAll() throws ScheduleException;

	public T getById(E id) throws ScheduleException;

	public T update(T entity) throws ScheduleException;

	public void delete(T id) throws ScheduleException;

	public void create(T entity) throws ScheduleException;

}
