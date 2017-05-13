package com.feeddit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feeddit.model.User;
import com.feeddit.repository.UserRepository;

@Service("userService")
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public void create(final User t) {
		userRepository.save(t);
	}

	@Override
	@Transactional
	public void delete(final Long id) {
		userRepository.delete(id);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User findById(final Long id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(final User t) {
		userRepository.save(t);

	}

	@Override
	@Transactional
	public void saveAll(final List<User> t) {
		userRepository.save(t);
	}


}
