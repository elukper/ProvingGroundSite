package hr.altima.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hr.altima.dao.DbEntry;

public class EntityServiceUtils {

	private EntityServiceUtils(){
		//private constructor
	}

	public static Map<String, DbEntry> mapDbEntryToName(final List<DbEntry> dbEntries) {
		final Map<String,DbEntry> result = new HashMap<>();

		for(final DbEntry dbEntry : dbEntries) {
			result.put(dbEntry.getName(), dbEntry);
		}

		return result;
	}



	public static Map<String,List<String>> resolveParentChildRelations(final Map<String, DbEntry> currentEntries, final Map<String,String> newDbEntries) {
		final Map<String, List<String>> result = new HashMap<>();

		for(final Entry<String,DbEntry> entry : currentEntries.entrySet()) {
			if(entry.getValue().getParent()!=null) {
				addToMapOfLists(result, entry.getValue().getParent().getName(), entry.getKey());
			}
		}

		for(final Entry<String,String> entry : newDbEntries.entrySet()) {
			if(entry.getValue()!=null) {
				addToMapOfLists(result, entry.getValue(),entry.getKey());
			}
		}

		return result;
	}

	public static <K, V> void addToMapOfLists(final Map<K,List<V>> map, final K key, final V value) {

		if(!map.containsKey(key)) {
			map.put(key, new ArrayList<>());
		}

		map.get(key).add(value);

	}

}
