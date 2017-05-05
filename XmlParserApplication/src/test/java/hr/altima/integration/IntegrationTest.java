package hr.altima.integration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.Files;

import hr.altima.calculation.RelationshipCalculator;
import hr.altima.calculation.exceptions.DuplicateEntryException;
import hr.altima.calculation.exceptions.LoopedRelationException;
import hr.altima.dao.DbEntry;
import hr.altima.dao.repository.DbEntryRepository;
import hr.altima.utils.EntityServiceUtils;
import hr.altima.xmlparser.XMLParsingUnit;
import hr.altima.xmlparser.parseddata.Entries;

@ActiveProfiles("tst")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
public class IntegrationTest {

	@Autowired
	DbEntryRepository dbEntryService;

	@Test
	public void testEnd2EndEmptyDb(){
		dbEntryService.deleteAll();
		testEnd2End(new HashMap<>());
	}

	@Test
	public void testEnd2EndFilledDb() {

		dbEntryService.deleteAll();

		final DbEntry dbEntry1 = new DbEntry();
		dbEntry1.setName("Dean Winchester");

		final DbEntry dbEntry2 = new DbEntry();
		dbEntry2.setName("Sam Winchester");

		dbEntry1.setParent(dbEntry2);

		dbEntryService.save(Arrays.asList(dbEntry1,dbEntry2));

		final Map<String, DbEntry> currentDbEntries = EntityServiceUtils.mapDbEntryToName(Lists.newArrayList(dbEntryService.findAll()));

		testEnd2End(currentDbEntries);
	}


	private void testEnd2End(final Map<String,DbEntry> currentDbEntries) {

		//Step 1: Parse data
		XMLParsingUnit<Entries> xmlParsingUnit;
		Entries entries = null;
		final File file = new File("src/main/resources/inputFolder/entries.xml");

		try {
			xmlParsingUnit = new XMLParsingUnit<>(Entries.class);
			entries = xmlParsingUnit.parseXmlTData(Files.asByteSource(file).openStream());
		} catch (final JAXBException e1) {
			e1.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(entries);
		//Expected 4 entries parsed
		Assert.assertEquals(4, entries.getEntries().size());

		//Step 2: Perform calculation
		final RelationshipCalculator relationshipCalculator = new RelationshipCalculator();

		//Step 2.1: Check if there are any duplicate entries in the parsed xml data
		Map<String, String> resolvedDatabaseCalcInput = null;
		try {
			resolvedDatabaseCalcInput = relationshipCalculator.validateInput(entries.getEntries());
		} catch (final DuplicateEntryException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(resolvedDatabaseCalcInput);

		//Step 2.2: Calculate dependencies, expected number of new inputs is 4

		Set<DbEntry> databaseInput = null;
		try {
			databaseInput = relationshipCalculator.createDatabaseInput(currentDbEntries, resolvedDatabaseCalcInput);
		} catch (final LoopedRelationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(databaseInput);
		Assert.assertEquals(4, databaseInput.size());

		//Step 3: Save into database
		dbEntryService.save(databaseInput);

		final List<DbEntry> output = Lists.newArrayList(dbEntryService.findAll());
		Assert.assertEquals(4, output.size());

		int validEntries = 0;

		for(final DbEntry dbEntry : output) {
			switch(dbEntry.getName()) {
			case "John Winchester": validEntries++; break;
			case "Castiel": validEntries++; break;
			case "Dean Winchester": if(dbEntry.getParent().getName().equals("John Winchester")) {validEntries++;} break;
			case "Sam Winchester":  if(dbEntry.getParent().getName().equals("John Winchester")) {validEntries++;} break;
			default:
			}
		}

		Assert.assertEquals(4, validEntries);

	}

}
