package hr.altima.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import hr.altima.model.AbstractDbPersistable;
import hr.altima.model.DbEntry;
import hr.altima.model.service.EntityService;

public class EntityServiceUtils {

	private EntityServiceUtils(){
		//private constructor
	}

	public static <T extends AbstractDbPersistable> EntityService<T> createEntityService(final ApplicationContext applicationContext) {

		return (EntityService<T>)applicationContext.getBean("entityService");

	}

	public static Map<String, DbEntry> mapDbEntryToName(final List<DbEntry> dbEntries) {
		final Map<String,DbEntry> result = new HashMap<>();

		for(final DbEntry dbEntry : dbEntries) {
			result.put(dbEntry.getName(), dbEntry);
		}

		return result;
	}

}
