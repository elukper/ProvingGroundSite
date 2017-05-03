package hr.altima.calculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import hr.altima.calculation.exceptions.DuplicateEntryException;
import hr.altima.enumeration.DaoAction;
import hr.altima.model.DbEntry;
import hr.altima.utils.CollectionsUtil;
import hr.altima.xmlparser.parseddata.Person;

public class RelationshipCalculator {

	private final List<String> info = new ArrayList<>();

	public List<String> getInfo() {
		return Collections.unmodifiableList(info);
	}


	public Map<String,String> resolveDatabaseInput(final List<Person> parsedData) throws DuplicateEntryException{

		final List<String> errors = new ArrayList<>();

		final Map<String,String> result = new HashMap<>();

		for(final Person person : parsedData) {
			if(result.containsKey(person.getEntry()) && result.get(person.getEntry())!=null){
				if(!result.get(person.getEntry()).equals(person.getParentName())){
					//Identical entry at two places is skipped, not an error
					errors.add("Duplicate entry detected: "+person.getEntry());
				}
				continue;
			}
			result.put(person.getEntry(), person.getParentName());
			if(person.getParentName()!=null) {
				result.put(person.getParentName(), null);
			}

		}

		if(!errors.isEmpty()) {
			throw new DuplicateEntryException(errors);
		}

		return result;

	}


	public Map<DaoAction,Set<DbEntry>> createDatabaseInput(final Map<String, DbEntry> currentEntries, final Map<String,String> entries) {

		info.clear();

		final Map<DaoAction,Set<DbEntry>> result = new HashMap<>();
		final Set<String> inDatabase = new HashSet<>();
		inDatabase.addAll(currentEntries.keySet());

		for(final Entry<String,String> entry : entries.entrySet()) {
			final String parent = entry.getValue();
			final String child = entry.getKey();

			final DbEntry childEntry = getNewOrExisting(currentEntries, child);
			final DbEntry parentEntry = getNewOrExisting(currentEntries, parent);

			childEntry.setParent(parentEntry);

			if(inDatabase.contains(child)){
				info.add("Overwriting "+child);
				CollectionsUtil.addToMapOfSets(result, DaoAction.UPDATE, childEntry);
			} else {
				CollectionsUtil.addToMapOfSets(result, DaoAction.PERSIST, childEntry);
			}

			if(!currentEntries.containsKey(child) && childEntry != null){
				currentEntries.put(child, childEntry);
			}

			if(parent != null){
				if(inDatabase.contains(parent)){
					CollectionsUtil.addToMapOfSets(result, DaoAction.UPDATE, parentEntry);
				} else {
					CollectionsUtil.addToMapOfSets(result, DaoAction.PERSIST, parentEntry);
				}
			}

			if(!currentEntries.containsKey(parent) && parentEntry!=null) {
				currentEntries.put(parent, parentEntry);
			}

		}

		return result;
	}

	private DbEntry getNewOrExisting(final Map<String,DbEntry> currentEntries, final String name) {

		if (name==null) {
			return null;
		}

		DbEntry result;

		if(currentEntries.containsKey(name)){
			result = currentEntries.get(name);
		} else {
			result = new DbEntry();
			result.setName(name);
		}

		return result;
	}

}
