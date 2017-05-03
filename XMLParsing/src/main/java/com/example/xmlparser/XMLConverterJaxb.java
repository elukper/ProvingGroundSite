package com.example.xmlparser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.example.parsedData.AbstractParsedXmlData;

public class XMLConverterJaxb {

	private final Marshaller marshaller;

	private final Unmarshaller unmarshaller;

	public XMLConverterJaxb(final Class<? extends AbstractParsedXmlData> classname) throws JAXBException {
		this.marshaller = JAXBContext.newInstance(classname).createMarshaller();
		this.unmarshaller = JAXBContext.newInstance(classname).createUnmarshaller();
	}

	public void convertFromObjectToXML(final Object object, final String filepath) throws IOException, JAXBException {

		FileOutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(filepath);
			marshaller.marshal(object, outputStream);
		} finally {
			if(outputStream != null) {
				outputStream.close();
			}
		}

	}

	public Object convertFromXMLToObject(final String xmlfile) throws IOException {

		FileInputStream inputStream = null;

		try {
			inputStream = new FileInputStream(xmlfile);
			return unmarshaller.unmarshal(inputStream);
		} catch (final JAXBException e) {

			e.printStackTrace();
			return null;
		} finally {
			if(inputStream != null) {
				inputStream.close();
			}
		}

	}

}
