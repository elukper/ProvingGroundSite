package hr.altima.model.dao;

import java.util.List;

import hr.altima.model.DbPersistable;

public interface DaoLayer<T> {

	List<T> findAllEntities(Class<T> entityType);

	void saveAll(List<T> entities);

	void updateAll( List<DbPersistable> entities,  Class<T> type);

	void saveEntity(T t);

	void updateEntity(T T);

}
