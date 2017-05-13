package com.feeddit.service;

import java.util.List;

import com.feeddit.model.Article;

public interface ArticleService {

	public void create(Article t);
	public void delete(Long id);
	public List<Article> findAll();
	public Article findById(Long id);
	public void save(Article t);
	public void saveAll(List<Article> t);

}
