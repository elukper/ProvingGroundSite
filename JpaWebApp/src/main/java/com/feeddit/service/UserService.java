package com.feeddit.service;

import java.util.List;

import com.feeddit.model.User;

public interface UserService {

	public void create(User t);
	public void delete(Long id);
	public List<User> findAll();
	public User findById(Long id);
	public void save(User t);
	public void saveAll(List<User> t);

}
