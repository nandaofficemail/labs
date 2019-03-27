package com.metlife.investments.cohesion.core.registry.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import com.metlife.investments.cohesion.core.registry.PublisherRegistryPropertiesDTO;

@XmlRootElement(name = "Registry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistryEntry
{
    @XmlElement(name="Id")
    private int id;

    @XmlElement(name="Type")
    private String type;
    
    @XmlElement(name="Domain")
    private String domain;
    
    @XmlElement(name="Path")
    private String path;
    
    @XmlElement(name = "Properties")
    private List<PropertyValue> propertyValues;
    
    @XmlElement(name = "Endpoint")
    private Endpoint endPoint;

    @Override
    public String toString()
    {
	String result = "";
	result = "Id: " + Integer.toString(id) + ", " +
		"Type: " + type + ", " +
		"Domain: " + domain + ", " +
		"Path: " + path + ", " +
		"Properties: " + propertyValues + ", " +
		"Endpoint: " + endPoint;
	return result;
    }

    public int getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    public String getPath()
    {
        return path;
    }

    public String getDomain()
    {
        return domain;
    }
    
    public List<PropertyValue> getPropertyValues()
    {
        return propertyValues;
    }

    public Endpoint getEndPoint()
    {
        return endPoint;
    }
    
    
}
