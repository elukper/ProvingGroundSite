package com.feeddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feeddit.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
