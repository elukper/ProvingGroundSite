package com.feeddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feeddit.model.AbstractEntry;

@Repository
public interface EntityRepository<T extends AbstractEntry> extends JpaRepository<T, Long>{


}
