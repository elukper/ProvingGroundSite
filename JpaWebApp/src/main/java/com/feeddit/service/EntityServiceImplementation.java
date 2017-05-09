package com.feeddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feeddit.model.AbstractEntry;
import com.feeddit.repository.EntityRepository;

@Service("entityService")
public class EntityServiceImplementation<T extends AbstractEntry> implements EntityService<T> {

	@Autowired
	EntityRepository<T> entityRepository;

	@Override
	@Transactional
	public void create(final T t) {
		entityRepository.save(t);
	}

	@Override
	@Transactional
	public void delete(final Long id) {
		entityRepository.delete(id);
	}

	@Override
	@Transactional
	public List<T> findAll() {
		return entityRepository.findAll();
	}

	@Override
	@Transactional
	public T findById(final Long id) {
		return entityRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(final T t) {
		entityRepository.save(t);

	}

	@Override
	@Transactional
	public void saveAll(final List<T> t) {
		entityRepository.save(t);
	}


}
