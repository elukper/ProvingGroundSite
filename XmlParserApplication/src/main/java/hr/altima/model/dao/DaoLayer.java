package hr.altima.model.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import hr.altima.model.AbstractDbPersistable;

public interface DaoLayer<T extends AbstractDbPersistable> {

	List<T> findAllEntities(Class<T> entityType);

	@Transactional
	void saveAll(List<T> entities);

	void saveEntity(T t);

	void updateEntity(T T);

}
