package hr.altima.xmlparser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:xmlparsing.properties")
public class XMLParsingUnit<T> {

	@Autowired
	private Environment env;

	Marshaller marshaller;
	Unmarshaller unmarshaller;

	public XMLParsingUnit(final Class<T> classT) throws JAXBException {
		final JAXBContext jc = JAXBContext.newInstance(classT);
		marshaller = jc.createMarshaller();
		unmarshaller = jc.createUnmarshaller();

	}

	public T parseXmlToData() throws JAXBException {
		return (T) unmarshaller.unmarshal(new File(env.getProperty("input.folderandfile")));
	}

	public T parseXmlToData(final File xml) throws JAXBException {
		return (T) unmarshaller.unmarshal(xml);
	}

}
