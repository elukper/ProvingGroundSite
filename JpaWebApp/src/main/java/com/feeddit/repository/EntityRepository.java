package com.feeddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feeddit.model.DbEntry;

public interface EntityRepository extends JpaRepository<DbEntry, Long>{


}
