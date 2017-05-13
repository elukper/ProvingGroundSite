package com.feeddit.service;

import java.util.List;

import com.feeddit.model.DbEntry;


public interface EntityService {

	public void create(DbEntry t);
	public void delete(Long id);
	public List<DbEntry> findAll();
	public DbEntry findById(Long id);
	public void save(DbEntry t);
	public void saveAll(List<DbEntry> t);

}
