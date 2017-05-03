package hr.altima.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsUtil {

	private CollectionsUtil() {
		//static class constructor
	}

	public static <K, V> void addToMapOfLists(final Map<K,List<V>> mapOfLists, final K key, final V value) {
		if(mapOfLists == null) {
			return;
		}

		if(!mapOfLists.containsKey(key)) {
			mapOfLists.put(key, new ArrayList<>());
		}
		mapOfLists.get(key).add(value);

	}

	public static <K,V> void addToMapOfSets(final Map<K,Set<V>> map, final K key, final V value) {
		if(map == null) {
			return;
		}

		if(!map.containsKey(key)){
			map.put(key, new HashSet<>());
		}
		map.get(key).add(value);
	}

	public static <K1,K2,V> void addToMapOfMapsOfLists(final Map<K1,Map<K2,List<V>>> map, final K1 firstKey, final K2 secondKey, final V value) {
		map.computeIfAbsent(firstKey, key -> new HashMap<>()).computeIfAbsent(secondKey, key -> new ArrayList<>()).add(value);
	}

	public static <K1,K2,V> void addToMapOfMaps(final Map<K1,Map<K2,V>> map, final K1 firstKey, final K2 secondKey, final V value) {
		if(!map.containsKey(firstKey)) {
			map.put(firstKey, new HashMap<>());
		}

		map.get(firstKey).put(secondKey, value);

	}

}
