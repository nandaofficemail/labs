package com.metlife.investments.cohesion.providers.messaging;

/**
 * implements the Publisher interface for Cohesion
 */
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metlife.investments.cohesion.core.Publisher;
import com.metlife.investments.cohesion.core.registry.PublisherRegistryDTO;
import com.metlife.investments.cohesion.core.registry.dto.Endpoint;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;


public class CohesionPublisher extends JMSProviderContainer implements Publisher
{
    private com.metlife.investments.cohesion.providers.CohesionContext context;

    private static Logger logger = LoggerFactory.getLogger(CohesionPublisher.class);
    
    public CohesionPublisher(com.metlife.investments.cohesion.providers.CohesionContext cohesion, JMSProvider jmsProvider)
    {
	super(jmsProvider);
	this.context = cohesion;
    }

    public void publish(ResourceTarget target, final String message, final Map<String, String> properties)
    {
	publishEntity(target, message, String.class, properties);
    }

    public <T> void publishEntity(ResourceTarget target, T entity, Class<T> cls, Map<String, String> properties)
    {
	Endpoint entry = CohesionMessagingHelper.resolveResourceToEndpoint(target, ResourceTarget.JMS_SCHEME, context);
	if (entry != null)
	{
	    sendMsg(entity, cls, properties, entry.getFormat(), entry.getJmsEndpoint(), logger);
	}
	else
	{
	    logger.error("Cannot publish entity for Resource: {}", target);
	}
    }
 }
