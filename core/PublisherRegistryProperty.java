package com.metlife.investments.cohesion.core.registry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="d3p1:PublisherRegistryProperty")
public class PublisherRegistryProperty
{
    @XmlElement(name="d3p1:ID")
    private int ID;

    @XmlElement(name="d3p1:Property")
    private String property;
	
    public int getID() {
	return ID;
    }
    public void setID(int iD) {
	ID = iD;
    }
    public String getProperty() {
	return property;
    }
    public void setProperty(String property) {
	this.property = property;
    }
    
    @Override
    public String toString()
    {
	return "{" + this.ID + "|" + this.property + "}";
    }
}
