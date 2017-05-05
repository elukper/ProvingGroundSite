package hr.altima.xmlparser;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.io.Files;

import hr.altima.xmlparser.parseddata.Entries;

public class XmlParsingUnitTest {

	@Test
	public void testXmlParser() {

		XMLParsingUnit<Entries> xmlParsingUnit;
		final File file = new File("src/main/resources/inputFolder/entries.xml");
		try {
			xmlParsingUnit = new XMLParsingUnit<>(Entries.class);
			final Entries entries = xmlParsingUnit.parseXmlTData(Files.asByteSource(file).openStream());
			Assert.assertEquals(4, entries.getEntries().size());

		} catch (final JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}
