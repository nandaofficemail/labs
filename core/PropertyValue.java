package com.metlife.investments.cohesion.core.registry.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PropertyValue")
public class PropertyValue
{
    @XmlElement(name="Property")
    private String property;
    
    @XmlElement(name="Value")
    private String value;
    
    @Override
    public String toString()
    {
	String result = "";
	result = "<Property: " + property + ", " +
		"Value: " + value + ">";
	return result;
    }
}
