package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.CastorException;
import org.exolab.castor.xml.Unmarshaller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.castor.CastorMarshaller;

import com.example.xmlparser.XMLConverter;
import com.example.xmlparser.XMLConverterImplementation;


@SpringBootApplication
public class XmlParsingApplication {
	
	private static final String XML_FILE_NAME = "entries.xml";

	public static void main(String[] args) throws Exception{
		SpringApplication.run(XmlParsingApplication.class, args);
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/xmlparser/xmlParserBeanConfig.xml");
		
		XMLConverter xmlConverter = appContext.getBean(XMLConverterImplementation.class);
		
//		Person personA = new Person();
//		personA.setName("Barry");
//		personA.setParentName("Joe");
//		
//		Person personB = new Person();
//		personB.setName("Joe");
//		
//		List<Person> data = new ArrayList<>();
//		data.add(personA);
//		data.add(personB);
//		
//		ParsedData parsedData = new ParsedData();
//		parsedData.setEntries(data);
//		
//			xmlConverter.convertFromObjectToXML(parsedData, XML_FILE_NAME);
		
//		Mapping mapping = new Mapping();
//	     mapping.loadMapping("C:/elukper/mapping.xml");
//
//	     Unmarshaller un = new Unmarshaller(Addressbook.class);
//	     un.setMapping( mapping );
//
//	     // -- Read in the Addressbook using the mapping
//	     FileReader in = new FileReader("input.xml");
//	     Addressbook book = (Addressbook) un.unmarshal(in);
//	     in.close();
	     
	     
		
		
		
		//ParsedData input = (ParsedData)xmlConverter.convertFromXMLToObject(XML_FILE_NAME);
		
		Addressbook input2 = (Addressbook)xmlConverter.convertFromXMLToObject("src/main/resources/input.xml");
		
		System.out.println("Done");
		
		
	}
}
