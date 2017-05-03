package hr.altima.xmlparser.xmlconverterbeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class XMLConverterImplementation implements XMLConverter{

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	@Override
	public Marshaller getMarshaller() {
		return marshaller;
	}

	@Override
	public void setMarshaller(final Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	@Override
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	@Override
	public void setUnmarshaller(final Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	@Override
	public void convertFromObjectToXML(final Object object, final String filepath)
			throws IOException {

		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filepath);
			getMarshaller().marshal(object, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	@Override
	public Object convertFromXMLToObject(final String xmlfile) throws IOException {

		FileInputStream is = null;
		try {
			is = new FileInputStream(xmlfile);
			return getUnmarshaller().unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public Object convertFromXMLToObject(final File xmlfile) throws IOException {

		FileInputStream is = null;
		try {
			is = new FileInputStream(xmlfile);
			return getUnmarshaller().unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

}
