package hr.altima.calculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import hr.altima.calculation.exceptions.DuplicateEntryException;
import hr.altima.model.DbEntry;
import hr.altima.xmlparser.parseddata.Person;

public class RelationshipCalculatorTest {

	private static final List<Person> startingPersonList = new ArrayList<>();

	private static final RelationshipCalculator relationshipCalculator = new RelationshipCalculator();

	@BeforeClass
	public static void executeAtStart(){

		final Person personA = new Person();
		personA.setEntry("Luka");
		personA.setParentName("Mirko");

		final Person personB = new Person();
		personB.setEntry("Tesa");
		personB.setParentName("Zdenka");

		final Person personC = new Person();
		personC.setEntry("Tina");
		personC.setParentName("Zdenka");

		final Person personD = new Person();
		personD.setEntry("Mirko");
		personD.setParentName("Marko");

		startingPersonList.add(personA);
		startingPersonList.add(personB);
		startingPersonList.add(personC);
		startingPersonList.add(personD);

	}

	@Test
	public void resolveAccurateDatabaseInputTest() {
		final List<Person> input = new ArrayList<>();
		input.addAll(startingPersonList);
		Map<String,String> result = null;
		try {
			result = relationshipCalculator.resolveDatabaseInput(input);
		} catch (final DuplicateEntryException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(result);

		for(final Person person : input) {
			Assert.assertTrue(result.containsKey(person.getEntry()));
			Assert.assertTrue(result.get(person.getEntry()).equals(person.getParentName()));
		}

		Assert.assertTrue(result.containsKey("Marko"));
		Assert.assertTrue(result.get("Marko")==null);

		Assert.assertTrue(result.containsKey("Zdenka"));
		Assert.assertTrue(result.get("Zdenka")==null);
	}

	@Test
	public void resolveInaccurateDatabaseInputTest() {
		final List<Person> input = new ArrayList<>();
		input.addAll(startingPersonList);

		final Person invalidDuplicatePerson = new Person();
		invalidDuplicatePerson.setEntry("Luka");
		invalidDuplicatePerson.setParentName("Franjo");

		final Person duplicateEntry = new Person();
		duplicateEntry.setEntry("Tesa");
		duplicateEntry.setParentName("Zdenka");

		input.add(invalidDuplicatePerson);
		input.add(duplicateEntry);

		Map<String,String> result = null;
		List<String> errors = null;

		try {
			result = relationshipCalculator.resolveDatabaseInput(input);
		} catch (final DuplicateEntryException e) {
			errors = e.getErrors();
		}

		Assert.assertTrue(result==null);
		Assert.assertNotNull(errors);
		Assert.assertTrue(errors.size()==1);
		Assert.assertTrue(errors.get(0).contains("Luka"));
	}

	@Test
	public void createDatabaseInputEmptyDb() {

		Map<String,String> entries = null;

		try {
			entries = relationshipCalculator.resolveDatabaseInput(startingPersonList);
		} catch (final DuplicateEntryException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(entries);

		//		final Set<DbEntry> result = relationshipCalculator.createDatabaseInput(new HashMap<>(), entries);
		//
		//		final int expectedNumOfEntries = entries.keySet().size();
		//
		//		Assert.assertEquals(expectedNumOfEntries, result.size());
		//
		//		Assert.assertEquals(0, relationshipCalculator.getInfo().size());

	}

	@Test
	public void createDatabaseInputOverwrite() {

		final DbEntry existingEntry = new DbEntry();
		existingEntry.setName("Luka");

		final DbEntry existingParent = new DbEntry();
		existingParent.setName("Gaj Julije Cezar");

		existingEntry.setParent(existingParent);

		final Map<String,DbEntry> currentEntries = new HashMap<>();
		currentEntries.put(existingEntry.getName(), existingEntry);
		currentEntries.put(existingParent.getName(), existingParent);

		Map<String,String> entries = null;

		try {
			entries = relationshipCalculator.resolveDatabaseInput(startingPersonList);
		} catch (final DuplicateEntryException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(entries);

		//		final Set<DbEntry> result = relationshipCalculator.createDatabaseInput(currentEntries, entries);
		//
		//		final int expectedNumOfEntries = entries.keySet().size();
		//
		//		Assert.assertEquals(expectedNumOfEntries, result.size());
		//		Assert.assertEquals(expectedNumOfEntries+1, currentEntries.size());
		//		Assert.assertEquals(1, relationshipCalculator.getInfo().size());
		//
		//		for(final DbEntry dbEntry : result) {
		//			if(dbEntry.getName().equals("Luka")) {
		//				Assert.assertTrue(dbEntry.getParent().getName().equals("Mirko"));
		//			}
		//		}

	}

}
