package com.metlife.investments.cohesion.providers.registry;

public class PubSubRegEntryKey 
{
    private String resource;
    private String operation;
    
    public PubSubRegEntryKey(String resource, String operation)
    {
	this.resource = resource;
	this.operation = operation;
    }
    public String getResource()
    {
        return this.resource;
    }
    public void setResource(String resource)
    {
        this.resource = resource;
    }
    public String getOpeartion()
    {
        return this.operation;
    }
    public void setOpeartion(String operation)
    {
        this.operation = operation;
    }
}

