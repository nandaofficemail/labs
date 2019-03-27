package com.metlife.investments.cohesion.providers.messaging;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import com.metlife.investments.cohesion.core.Context;
import com.metlife.investments.cohesion.core.registry.PublisherRegistryDTO;
import com.metlife.investments.cohesion.core.registry.dto.Endpoint;
import com.metlife.investments.cohesion.core.registry.dto.RegistryEntry;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.providers.CohesionContext;

public class CohesionMessagingHelper
{
    public static Endpoint resolveResourceToEndpoint(ResourceTarget target, String expectedType, CohesionContext context)
    {
	List<RegistryEntry> entry;
	if (target.isCohesionTarget())
	{
	    CohesionContext.getLogger(CohesionMessagingHelper.class).info("using Cohesion to find destination of: {}", target);

	    entry = context.getServiceRegistry().searchRegistry(target.getDomain(), target.getPath(), expectedType);

	    if (entry == null || entry.size() == 0)
	    {
		CohesionContext.getLogger(CohesionMessagingHelper.class).error("no registry entries found for resource: {}", target);
		// TODO: should throw an exception here
		return null;
	    }
	    else if (entry.size() > 1)
	    {
		CohesionContext.getLogger(CohesionMessagingHelper.class).error("found more than one match in regstry for: {}", target);
		// TODO: should throw an exception here
		return null;
	    }
	    else
	    {
		// recursively try to resolve the URI using the URI returned from the registry service lookup 
		return resolveResourceToEndpoint(new ResourceTarget(entry.get(0).getEndPoint().getURI()), expectedType, context);
	    }
	}
	else if (target.isJMSTarget())
	{
	    CohesionContext.getLogger(CohesionMessagingHelper.class).info("using direct destination: {}", target);
	    Endpoint ep = new Endpoint(target.getJMSDestination(), "UNKNOWN");
	    return ep;
	}
	else if (target.isHTTPTarget())
	{
	    Endpoint ep = new Endpoint(target.getURI());
	    return ep;
	}
	else
	{
	    return null;
	}

    }

    public static String formatSQL92(Map<String, String> props)
    {
	if (props == null || props.size() == 0)
	{
	    return null;
	}

	String messageSelector = "";
	boolean first = true;
	for (Entry<String, String> pv : props.entrySet())
	{
	    if (!first)
	    {
		messageSelector += ") AND ";
	    }
	    messageSelector += "(" + pv.getKey() + " = '" + pv.getValue() + "'";
	    first = false;
	}
	messageSelector += ")";

	return messageSelector;
    }

}
