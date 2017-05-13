package com.feeddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.feeddit.model.Article;
import com.feeddit.repository.ArticleRepository;

public class ArticleServiceImplementation implements ArticleService{

	@Autowired
	ArticleRepository userRepository;

	@Override
	@Transactional
	public void create(final Article t) {
		userRepository.save(t);
	}

	@Override
	@Transactional
	public void delete(final Long id) {
		userRepository.delete(id);
	}

	@Override
	@Transactional
	public List<Article> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public Article findById(final Long id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(final Article t) {
		userRepository.save(t);

	}

	@Override
	@Transactional
	public void saveAll(final List<Article> t) {
		userRepository.save(t);
	}


}
