package hr.altima.model.service;

import java.util.List;

public interface EntityService<T> {

	List<T> findAllEntities(Class<T> entityType);

	void saveAllEntities(List<T> entities);

	void saveEntity(T t);

	void updateEntity(T t);

}
