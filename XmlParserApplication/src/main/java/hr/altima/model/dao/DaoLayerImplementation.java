package hr.altima.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository("daoLayer")
public class DaoLayerImplementation<T> extends AbstractDao<T> implements DaoLayer<T>{

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAllEntities(final Class<T> entityType) {
		final Criteria criteria = getSession().createCriteria(entityType);
		return criteria.list();
	}

	@Override
	public void saveEntity(final T t) {
		persist(t);


	}

	@Override
	public void updateEntity(final T T) {
		getSession().update(T);
	}









}
