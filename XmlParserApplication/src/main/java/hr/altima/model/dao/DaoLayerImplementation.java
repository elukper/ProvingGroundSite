package hr.altima.model.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

import hr.altima.model.AbstractDbPersistable;
import hr.altima.model.DbEntry;

@Repository("daoLayer")
public class DaoLayerImplementation<T extends AbstractDbPersistable> extends AbstractDao<T> implements DaoLayer<T>{

	private static final Map<Class<? extends AbstractDbPersistable>, Predicate<AbstractDbPersistable>> IS_STATEFUL = ImmutableMap.<Class<? extends AbstractDbPersistable>, Predicate<AbstractDbPersistable>>
	builder().put(DbEntry.class,object -> ((DbEntry) object).getParent()!=null).build();

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

	@Override
	public void saveAll(final List<T> entities, final Class<T> entityType) {
		final FlushMode initialFlushMode = getSession().getFlushMode();

		for(final T t : entities) {
			final T currentEntry = (T)getSession().get(entityType, t.getIdentity());
			if(currentEntry != null) {
				currentEntry.setParent(t.getParent());
			} else {
				getSession().persist(t);
			}
		}

		getSession().flush();
		getSession().setFlushMode(initialFlushMode);

	}


}
