package hr.altima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hr.altima.configuration.AppConfig;
import hr.altima.utils.ExecuteTest;

@SpringBootApplication
public class XmlParserApplication {

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(XmlParserApplication.class, args);

		final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.getEnvironment().setActiveProfiles("dev");
		applicationContext.register(AppConfig.class);
		applicationContext.refresh();

		//		XMLParsingUnit<Entries> xmlParsingUnit;
		//		final File file = new File("src/main/resources/inputFolder/entries.xml");
		//		try {
		//			xmlParsingUnit = new XMLParsingUnit<>(Entries.class);
		//			final Entries entries = xmlParsingUnit.parseXmlToData(file);
		//			for(final Person person : entries.getEntries()) {
		//				System.out.println("Name: "+person.getEntry()+" parent name; "+ person.getParentName());
		//			}
		//		} catch (final JAXBException e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		}

		final ExecuteTest executeTest = new ExecuteTest();
		executeTest.executeTest();




		System.out.println("Done");
	}
}
