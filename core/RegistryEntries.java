package com.metlife.investments.cohesion.core.registry.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CohesionRegistry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistryEntries
{
    @XmlElement(name = "RegistryEntries")
    private List<RegistryEntry> entries;

    public List<RegistryEntry> getEntries()
    {
	return entries;
    }
    
    @Override
    public String toString()
    {
	String result = "RegistryEntries: [";
	for (RegistryEntry r : entries)
	{
	    result += r.toString() + ",";
	}
	result += "]";
	return result;
    }
}
