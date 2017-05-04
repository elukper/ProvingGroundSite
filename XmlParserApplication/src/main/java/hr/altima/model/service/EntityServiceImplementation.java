package hr.altima.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hr.altima.model.AbstractDbPersistable;
import hr.altima.model.dao.DaoLayer;

@Service("entityService")
@Qualifier("entityService")
@Transactional
public class EntityServiceImplementation<T extends AbstractDbPersistable> implements EntityService<T>{

	@Autowired
	DaoLayer<T> daoLayer;

	@Override
	public List<T> findAllEntities(final Class<T> entityType) {
		return daoLayer.findAllEntities(entityType);
	}

	@Override
	public void saveAllEntities(final List<T> entities, final Class<T> entityType) {
		daoLayer.saveAll(entities,entityType);
	}

	@Override
	public void saveEntity(final T t) {
		daoLayer.saveEntity(t);

	}

	@Override
	public void updateEntity(final T t) {
		daoLayer.updateEntity(t);
	}

}
