package com.metlife.investments.cohesion.core.registry.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;

@XmlRootElement(name="Endpoint")
public class Endpoint
{
        @XmlElement(name="URI")
        private String uri;
        
        @XmlElement(name="Format")
        private String format;
    
        public Endpoint()
	{
	   this(null,"UNKNOWN");
	}
        
        public Endpoint(String uri)
	{
	   this(uri,"UNKNOWN");
	}
        public Endpoint(String uri, String format)
	{
	   this.uri = uri;
	   this.format = format;
	}
	@Override
        public String toString()
        {
    	String result = "";
    	result = "{URI: " + uri + ", " +
    		"Format: " + format + "}";
    	return result;
        }

	public String getURI()
	{
	    return uri;
	}
	
	public String getFormat()
	{
	    return format;
	}
	public String getJmsEndpoint()
	{
	    // assumes that first 4 chars of the URI are "jms:", otherwise return the whole URI
	    if (uri.substring(0, 4).compareToIgnoreCase("jms:") == 0)
		return uri.substring(4);
	    else
		return uri;
	}
}
