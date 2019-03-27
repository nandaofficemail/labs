package com.metlife.investments.cohesion.providers;

public class CohesionProviders
{
    private String[] jmsProviderNamesArray;

    public CohesionProviders()
    {
    }
    /*
    public CohesionProviders(String jmsProviderNames)
    {
	this.jmsProviderNamesArray = jmsProviderNames.split(",");
    }
    */
    public void setJmsProviders(String jmsProviderNames)
    {
	this.jmsProviderNamesArray = jmsProviderNames.split(",");
    }
    
    public String[] getJMSProviderNames()
    {
	return this.jmsProviderNamesArray; 
    }
}
