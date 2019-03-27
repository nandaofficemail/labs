package com.metlife.investments.cohesion.core.serializer;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metlife.investments.cohesion.core.Format;

public class SerializerDeserializer
{
    private static Logger logger = LoggerFactory.getLogger(SerializerDeserializer.class);

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String str, Class<T> cls, Format type)
    {
	Reader strReader = new StringReader(str);
	return deserialize(strReader, cls, type);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(Reader strReader, Class<T> cls, Format type)
    {
        Map<String, Object> properties = new HashMap<String, Object>(1);
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        JAXBContext jc;
        T t = null;
	try
	{
	    jc = JAXBContext.newInstance(new Class[] {cls}, properties);
	    StreamSource ss = new StreamSource(strReader);
	    Unmarshaller unmarshaller = jc.createUnmarshaller();
	    switch(type)
	    {
	    case XML:
		break;
	    case JSON:
		unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
		break;
	    default:
		break;
	    }
	    t = (T) unmarshaller.unmarshal(ss, cls).getValue();

	} 
	catch (JAXBException e)
	{
	    logger.error("error during unmarshal", e);
	}
        return (t);
        

    }

    public static <T> String serialize(T obj, Class<T> cls, Format type) throws Exception
    {
        Map<String, Object> properties = new HashMap<String, Object>(1);

        JAXBContext jc = JAXBContext.newInstance(new Class[] {cls}, properties);
 
        Marshaller marshaller = jc.createMarshaller();
        switch(type)
        {
        case XML:
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            break;
        case JSON:
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            break;
        default:
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            break;
        }
        StringWriter strWriter = new StringWriter();
        marshaller.marshal(obj, strWriter);
        return strWriter.toString();
    }
    public static Format formatType(String dataContract)
    {
	Format format;
	// get the topic for the entry and then subscribe to the topic
	if (dataContract.equalsIgnoreCase("XSD") || dataContract.equalsIgnoreCase("XML"))
	{
	    format = Format.XML;
	}
	else if (dataContract.equalsIgnoreCase("JSON"))
	{
	    format = Format.JSON;
	}
	else
	{
	    format = Format.TEXT;
	}
	return format;
    }
}
