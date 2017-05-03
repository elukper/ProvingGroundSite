package hr.altima.xmlparser.parseddata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlValue
	private String entry;

	@XmlAttribute
	private String parentName;



	public String getEntry() {
		return entry;
	}



	public String getParentName() {
		return parentName;
	}



	public void setEntry(final String entry) {
		this.entry = entry;
	}



	public void setParentName(final String parentName) {
		this.parentName = parentName;
	}




}
