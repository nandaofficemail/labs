package com.metlife.investments.cohesion.core.registry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="d3p1:PublisherRegistryProperties")
public class PublisherRegistryPropertiesDTO 
{
    @XmlElement(name="d3p1:PublisherRegistryProperty")
    private List<PublisherRegistryProperty> props;

    public List<PublisherRegistryProperty> getProps()
    {
        return props;
    }

    public void setProps(List<PublisherRegistryProperty> props)
    {
        this.props = props;
    }
    
    @Override
    public String toString()
    {
	String result = "[";
	if (props != null)
	{
	    for (PublisherRegistryProperty p : props)
	    {
		result = result + p.toString() + ",";
	    }
	}
	result = result + "]";
	return result;
    }

}
