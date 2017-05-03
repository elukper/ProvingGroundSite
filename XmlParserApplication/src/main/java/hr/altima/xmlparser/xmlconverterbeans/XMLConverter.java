package hr.altima.xmlparser.xmlconverterbeans;

import java.io.File;
import java.io.IOException;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public interface XMLConverter {

	public static String XMLCOVERTER_CASTOR_BEAN_XML = "/beanconfigurations/xmlconverter/xmlParserBeanConfig.xml";

	public Unmarshaller getUnmarshaller();

	public void setUnmarshaller(Unmarshaller unmarshaller);

	public Marshaller getMarshaller();

	public void setMarshaller(Marshaller marshaller);

	public void convertFromObjectToXML(Object object, String filepath) throws IOException;

	public Object convertFromXMLToObject(String xmlfile) throws IOException;

	public Object convertFromXMLToObject(File xmlfile) throws IOException;



}
