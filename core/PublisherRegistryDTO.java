package com.metlife.investments.cohesion.core.registry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PublisherRegistry")
public class PublisherRegistryDTO
{
    @XmlElement(name = "ID")
    private int ID;

    @XmlElement(name = "Domain")
    private String Domain;

    @XmlElement(name = "Resource")
    private String Resource;

    @XmlElement(name = "Operation")
    private String Operation;

    @XmlElement(name = "Topic")
    private String Topic;

    @XmlElement(name = "DataContract")
    private String Data_Contract;

    @XmlElement(name = "DataContractType")
    private String DataContractType;

    @XmlElement(name = "PublisherRegistryProperties")
    private List<PublisherRegistryPropertiesDTO> pubEntryProperties;

    public PublisherRegistryDTO()
    {
    }

    @Override
    public String toString()
    {
	String result = "(" + this.ID + "|" + this.Domain + "|" + this.Resource + "|"
		+ this.Operation + "|" + this.Topic + "|" + this.Data_Contract
		+ "|" + this.DataContractType + "|";
	if (pubEntryProperties != null)
	{
	    for (PublisherRegistryPropertiesDTO p : pubEntryProperties)
	    {
		result = result + p.toString() + ", ";
	    }
	}
	result = result + ")";
	
	return result;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int iD)
    {
        ID = iD;
    }

    public String getDomain()
    {
        return Domain;
    }

    public void setDomain(String domain)
    {
        Domain = domain;
    }

    public String getResource()
    {
        return Resource;
    }

    public void setResource(String resource)
    {
        Resource = resource;
    }

    public String getOperation()
    {
        return Operation;
    }

    public void setOperation(String operation)
    {
        Operation = operation;
    }

    public String getTopic()
    {
        return Topic;
    }

    public void setTopic(String topic)
    {
        Topic = topic;
    }

    public String getData_Contract()
    {
        return Data_Contract;
    }

    public void setData_Contract(String data_Contract)
    {
        Data_Contract = data_Contract;
    }

    public String getDataContractType()
    {
        return DataContractType;
    }

    public void setDataContractType(String dataContractType)
    {
        DataContractType = dataContractType;
    }

    public List<PublisherRegistryPropertiesDTO> getPubEntryProperties()
    {
        return pubEntryProperties;
    }

    public void setPubEntryProperties(List<PublisherRegistryPropertiesDTO> pubEntryProperties)
    {
        this.pubEntryProperties = pubEntryProperties;
    }

}
