package hr.altima.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import hr.altima.calculation.RelationshipCalculator;
import hr.altima.calculation.exceptions.DuplicateEntryException;
import hr.altima.enumeration.DaoAction;
import hr.altima.model.DbEntry;
import hr.altima.model.service.EntityService;
import hr.altima.xmlparser.XMLParsingUnit;
import hr.altima.xmlparser.parseddata.Entries;
import hr.altima.xmlparser.parseddata.Person;

public class ExecuteTest {

	public void executeTest() {

		final EntityService<DbEntry> entityService = EntityServiceUtils.createEntityService();

		final Entries resolvedData = parseData();

		final RelationshipCalculator relCalc = new RelationshipCalculator();

		final Map<DaoAction,Set<DbEntry>> dbEntries = new HashMap<>();

		final List<DbEntry> currentDbEntries = entityService.findAllEntities(DbEntry.class);

		try {
			dbEntries.putAll(relCalc.createDatabaseInput(EntityServiceUtils.mapDbEntryToName(currentDbEntries), relCalc.resolveDatabaseInput(resolvedData.getEntries())));

		} catch (final DuplicateEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(dbEntries.get(DaoAction.PERSIST) != null) {
			entityService.saveAllEntities(new ArrayList<>(dbEntries.get(DaoAction.PERSIST)));
		}

		if(dbEntries.get(DaoAction.UPDATE) != null) {
			entityService.saveAllEntities(new ArrayList<>(dbEntries.get(DaoAction.UPDATE)));
		}
	}

	private Entries parseData() {

		XMLParsingUnit<Entries> xmlParsingUnit;
		Entries entries = null;
		final File file = new File("src/main/resources/inputFolder/entries.xml");
		try {
			xmlParsingUnit = new XMLParsingUnit<>(Entries.class);
			entries = xmlParsingUnit.parseXmlToData(file);
			for(final Person person : entries.getEntries()) {
				System.out.println("Name: "+person.getEntry()+" parent name; "+ person.getParentName());
			}
		} catch (final JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return entries;

	}

}
