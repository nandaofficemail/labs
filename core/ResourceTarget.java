package com.metlife.investments.cohesion.core.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/* ResourceTarget represents a resource which is the target of some Cohesion Framework action.
 * 
 */
public class ResourceTarget
{
    public static final String HTTP_SCHEME = "http";
    public static final String JMS_SCHEME = "jms";
    public static final String COHESION_SCHEME = "cohesion";

    public enum TargetType
    {
	COHESION,
	HTTP,
	JMS,
	UKNOWN
    }

    private String uri;
    private TargetType resourceType;
    private URI theURI;
    
    public ResourceTarget()
    {
	this.resourceType = TargetType.COHESION;
    }
    
    public ResourceTarget(String u)
    {
	this();
	this.uri = u;
	initialize();
    }
    
    private void initialize()
    {
	try
	{
	    this.theURI = new URI(this.uri);
	    if (this.theURI.getScheme().toLowerCase().contains(COHESION_SCHEME))
	    {
		this.resourceType = TargetType.COHESION;
	    }
	    else if (this.theURI.getScheme().toLowerCase().contains(JMS_SCHEME))
	    {
		this.resourceType = TargetType.JMS;
	    }
	    else if (this.theURI.getScheme().toLowerCase().contains(HTTP_SCHEME))
	    {
		this.resourceType = TargetType.HTTP;
	    }
	}
	catch (URISyntaxException e)
	{
	    e.printStackTrace();
	}
    }
    
    public ResourceTarget(ResourceTarget resourceTarget)
    {
	this(resourceTarget.uri);
    }

    public String getDomain()
    {
	return this.theURI.getAuthority();
    }

    public String getPath()
    {
	return this.theURI.getPath();
    }
    
    public String getURI()
    {
	return uri;
    };
    
    @Override
    public String toString()
    {
	return "(" + uri +  ")";
    }

    
    @Override
    public boolean equals(Object obj) 
    {
	if (!(obj instanceof ResourceTarget))
	{
	      return false;
	}
	if (obj == this)
	{
	    return true;
	}
	
	return  this.uri.equals(((ResourceTarget) obj).uri);
    }
    
    @Override
    public int hashCode() 
    {
	final int prime = 31;
	int result = 1;
	result = prime * result	+ ((uri == null) ? 0 : uri.hashCode());
	return result;
    }
    public boolean isCohesionTarget()
    {
	return resourceType == TargetType.COHESION;
    }
    public boolean isJMSTarget()
    {
	return resourceType == TargetType.JMS;
    }
    public boolean isHTTPTarget()
    {
	return resourceType == TargetType.HTTP;
    }

    public String getJMSDestination()
    {
	// for activeMQ the wildcard should be '>'
	// for WebSphere MQ the wildcard should be "#"
	// TODO: the driver should provide this detail
	
	return theURI.getSchemeSpecificPart().replace("*", "#");
    }
    
  }
