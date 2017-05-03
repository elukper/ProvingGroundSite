package hr.altima.model.dao;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hr.altima.model.AbstractDbPersistable;

public class AbstractDao<T extends AbstractDbPersistable> {

	@Autowired
	SessionFactory sessionFactory;

	public void delete(final T entity) {
		getSession().delete(entity);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(final T entity) {
		getSession().persist(entity);
	}

	public void saveAll(final List<T> entities) {
		getSession().setFlushMode(FlushMode.MANUAL);
		for(final T t: entities) {
			getSession().persist(t);
		}
		getSession().flush();
		getSession().setFlushMode(FlushMode.AUTO);
	}






}