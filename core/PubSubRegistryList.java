package com.metlife.investments.cohesion.core.registry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ArrayOfPublisherRegistry")
public class PubSubRegistryList
{
    private List<PublisherRegistryDTO> pubEntries;

    @XmlElement(name = "PublisherRegistry")
    public List<PublisherRegistryDTO> getPubEntries()
    {
        return pubEntries;
    }

    public void setPubEntries(List<PublisherRegistryDTO> pubEntries)
    {
        this.pubEntries = pubEntries;
    }
    
    public String toString()
    {
	String result = "List of PubEntries: ";
	if (pubEntries != null)
	{
	    for (PublisherRegistryDTO p : pubEntries)
	    {
		result = result + ", " + p.toString();
	    }
	}
	return result;
    }
}
