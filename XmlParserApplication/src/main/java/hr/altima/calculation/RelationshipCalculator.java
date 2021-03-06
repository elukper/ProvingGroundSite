package hr.altima.calculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import hr.altima.calculation.exceptions.DuplicateEntryException;
import hr.altima.calculation.exceptions.LoopedRelationException;
import hr.altima.dao.DbEntry;
import hr.altima.utils.EntityServiceUtils;
import hr.altima.xmlparser.parseddata.Person;

public class RelationshipCalculator {


	Logger logger = Logger.getLogger(RelationshipCalculator.class);



	public Map<String,String> validateInput(final List<Person> parsedData) throws DuplicateEntryException{


		final List<String> errors = new ArrayList<>();

		final Map<String,String> result = new HashMap<>();

		for(final Person person : parsedData) {
			if(result.containsKey(person.getEntry()) && result.get(person.getEntry())!=null){
				if(!result.get(person.getEntry()).equals(person.getParentName())){
					//Identical entry at two places is skipped, not an error
					errors.add("Duplicate entry detected: "+person.getEntry());
					logger.error("Duplicate entry detected: "+person.getEntry());
				}
				continue;
			}
			result.put(person.getEntry(), person.getParentName());
			if(person.getParentName()!=null && !result.containsKey(person.getParentName())) {
				result.put(person.getParentName(), null);
			}

		}

		if(!errors.isEmpty()) {
			throw new DuplicateEntryException(errors);
		}

		return result;

	}


	public Set<DbEntry> createDatabaseInput(final Map<String, DbEntry> currentEntries, final Map<String,String> entries) throws LoopedRelationException {


		checkLoopedRelationships(currentEntries, entries);

		final Set<DbEntry> result = new HashSet<>();
		final Set<String> inDatabase = new HashSet<>();
		inDatabase.addAll(currentEntries.keySet());

		for(final Entry<String,String> entry : entries.entrySet()) {
			final String parent = entry.getValue();
			final String child = entry.getKey();

			final DbEntry childEntry = getNewOrExisting(currentEntries, child);
			final DbEntry parentEntry = getNewOrExisting(currentEntries, parent);

			if(parentEntry!=null) {
				childEntry.setParent(parentEntry);
			}

			if(inDatabase.contains(child)){
				logger.info("Overwriting existing entry: "+child);
			}

			result.add(childEntry);

			if(!currentEntries.containsKey(child) && childEntry != null){
				currentEntries.put(child, childEntry);
			}

			if(parent != null){
				result.add(parentEntry);
			}

			if(!currentEntries.containsKey(parent) && parentEntry!=null) {
				currentEntries.put(parent, parentEntry);
			}

		}

		return result;
	}

	private void checkLoopedRelationships(final Map<String, DbEntry> currentEntries, final Map<String,String> newDbEntries) throws LoopedRelationException {
		final Map<String, List<String>> parentsToChildren = EntityServiceUtils.resolveParentChildRelations(currentEntries, newDbEntries);

		logger.info("Checking for looped relations");

		final List<String> errors = new ArrayList<>();
		final Set<String> loopedParentsFound = new HashSet<>();

		for(final Entry<String, List<String>> parentToChildren : parentsToChildren.entrySet()) {

			for(final String child : parentToChildren.getValue()) {

				if(parentsToChildren.containsKey(child) && parentsToChildren.get(child).contains(parentToChildren.getKey())) {
					errors.add(child + " is parent to "+parentToChildren.getKey());
					logger.error(child + " is parent to "+parentToChildren.getKey());
					loopedParentsFound.add(parentToChildren.getKey());
				}
			}
		}

		if(!errors.isEmpty()) {
			throw new LoopedRelationException(errors);
		}
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
