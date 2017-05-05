package hr.altima.dao.service;

import java.util.List;

import hr.altima.dao.DbEntry;

public interface DbEntryService {

	List<DbEntry> findAll();

	void save(List<DbEntry> data);

	void deleteAll();

	void save(DbEntry data);

}
