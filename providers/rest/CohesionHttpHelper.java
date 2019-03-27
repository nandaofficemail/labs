package com.metlife.investments.cohesion.providers.rest;

import java.net.URI;
import java.net.URISyntaxException;

import com.metlife.investments.cohesion.core.registry.dto.Endpoint;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.exceptions.CohesionException;
import com.metlife.investments.cohesion.providers.CohesionContext;
import com.metlife.investments.cohesion.providers.messaging.CohesionMessagingHelper;

public class CohesionHttpHelper
{
    public static ResourceTarget resolveResourceToEndpoint(ResourceTarget rt, CohesionContext cohesionContext) throws CohesionException
    {
	if (rt.isHTTPTarget())
	{
	    return rt;
	}
	else if (rt.isCohesionTarget())
	{
	    Endpoint entry = CohesionMessagingHelper.resolveResourceToEndpoint(rt, ResourceTarget.HTTP_SCHEME, cohesionContext);

	    URI origURI;
	    try
	    {
		origURI = new URI(rt.getURI());
	    }
	    catch (URISyntaxException e)
	    {
		throw new CohesionException("Error parsing URI: " + rt.getURI());
	    }
	    String q = origURI.getQuery();
	    String f = origURI.getFragment();
	    
	    URI resolvedURI;
	    try
	    {
		resolvedURI = new URI(entry.getURI());
	    }
	    catch (URISyntaxException e)
	    {
		throw new CohesionException("Error parsing URI: " + entry.getURI());
	    }
	    
	    URI derivedURI;
	    try
	    {
		derivedURI = new URI(resolvedURI.getScheme(),
		    		resolvedURI.getAuthority(),
		    		resolvedURI.getPath(),
		    		origURI.getQuery(),
		    		origURI.getFragment());
	    }
	    catch (URISyntaxException e)
	    {
		throw new CohesionException("Error constructting derived URI");
	    }
	    
	    return new ResourceTarget(derivedURI.toString());
	}
	throw new CohesionException("invalid target type for HTTP");
    }

}
