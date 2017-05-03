package hr.altima.model.dao;

import java.util.List;

public interface DaoLayer<T> {

	List<T> findAllEntities(Class<T> entityType);

	void saveAll(List<T> entities);

	void saveEntity(T t);

	void updateEntity(T T);

}
