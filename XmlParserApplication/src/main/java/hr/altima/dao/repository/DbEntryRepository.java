package hr.altima.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.altima.dao.DbEntry;

@Repository
public interface DbEntryRepository extends JpaRepository<DbEntry, Long>{

}
