package hr.altima.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.altima.dao.DbEntry;
import hr.altima.dao.repository.DbEntryRepository;

@Service("dbEntryService")
public class DbEntryServiceImpl implements DbEntryService{

	@Autowired
	DbEntryRepository dbEntryRepository;

	@Override
	@Transactional
	public List<DbEntry> findAll() {
		return dbEntryRepository.findAll();
	}

	@Override
	@Transactional
	public void save(final List<DbEntry> data) {
		dbEntryRepository.save(data);
	}

	@Override
	@Transactional
	public void deleteAll() {
		dbEntryRepository.deleteAll();
	}

	@Override
	@Transactional
	public void save(final DbEntry data) {
		dbEntryRepository.save(data);
	}

}
