package com.feeddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feeddit.model.DbEntry;
import com.feeddit.repository.EntityRepository;

@Service("entityService")
public class EntityServiceImplementation implements EntityService{

	@Autowired
	EntityRepository entityRepository;

	@Override
	@Transactional
	public void create(final DbEntry t) {
		entityRepository.save(t);
	}

	@Override
	@Transactional
	public void delete(final Long id) {
		entityRepository.delete(id);
	}

	@Override
	@Transactional
	public List<DbEntry> findAll() {
		return entityRepository.findAll();
	}

	@Override
	@Transactional
	public DbEntry findById(final Long id) {
		return entityRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(final DbEntry t) {
		entityRepository.save(t);

	}

	@Override
	@Transactional
	public void saveAll(final List<DbEntry> t) {
		entityRepository.save(t);
	}


}
