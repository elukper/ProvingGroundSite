package hr.altima.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import hr.altima.calculation.RelationshipCalculator;
import hr.altima.calculation.exceptions.DuplicateEntryException;
import hr.altima.model.DbEntry;
import hr.altima.model.service.EntityServiceImplementation;
import hr.altima.xmlparser.XMLParsingUnit;
import hr.altima.xmlparser.parseddata.Entries;
import hr.altima.xmlparser.parseddata.Person;

public class ExecuteTest {

	@Autowired
	@Qualifier("entityService")
	private EntityServiceImplementation<DbEntry> entityService; //Shit aint working

	public void executeTest() {


		final Entries resolvedData = parseData();

		final RelationshipCalculator relCalc = new RelationshipCalculator();

		final Set<DbEntry> dbEntries = new HashSet<>();

		final List<DbEntry> currentDbEntries = entityService.findAllEntities(DbEntry.class);

		try {
			dbEntries.addAll(relCalc.createDatabaseInput(EntityServiceUtils.mapDbEntryToName(currentDbEntries), relCalc.resolveDatabaseInput(resolvedData.getEntries())));

		} catch (final DuplicateEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		entityService.saveAllEntities(new ArrayList<>(dbEntries),DbEntry.class);

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
