package hr.altima.xmlparser.parseddata;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {

	@XmlElement(name="entry")
	private List<Person> entries;

	public List<Person> getEntries() {
		return entries;
	}

	public void setEntries(final List<Person> entries) {
		this.entries = entries;
	}




}
