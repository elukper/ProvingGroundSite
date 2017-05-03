package hr.altima.model.service;

import java.util.List;

import hr.altima.model.AbstractDbPersistable;

public interface EntityService<T extends AbstractDbPersistable> {

	List<T> findAllEntities(Class<T> entityType);

	void saveAllEntities(List<T> entities);

	void saveEntity(T t);

	void updateEntity(T t);

}
