package com.feeddit.service;

import java.util.List;

import com.feeddit.model.AbstractEntry;


public interface EntityService<T extends AbstractEntry> {

	public void create(T t);
	public void delete(Long id);
	public List<T> findAll();
	public T findById(Long id);
	public void save(T t);
	public void saveAll(List<T> t);

}
