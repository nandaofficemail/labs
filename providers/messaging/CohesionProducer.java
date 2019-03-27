package com.metlife.investments.cohesion.providers.messaging;

/**
 * implements the Publisher interface for Cohesion
 */
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.metlife.investments.cohesion.core.Producer;
import com.metlife.investments.cohesion.core.Publisher;
import com.metlife.investments.cohesion.core.registry.PublisherRegistryDTO;
import com.metlife.investments.cohesion.core.registry.dto.Endpoint;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.core.serializer.SerializerDeserializer;

public class CohesionProducer extends JMSProviderContainer implements Producer
{
    private com.metlife.investments.cohesion.providers.CohesionContext context;
    private JMSProvider jmsProvider;
    private static Logger logger = LoggerFactory.getLogger(CohesionPublisher.class);
    
    public CohesionProducer(com.metlife.investments.cohesion.providers.CohesionContext cohesion, JMSProvider jmsProvider)
    {
	super(jmsProvider);
	this.context = cohesion;
    }

    @Override
    public void send(ResourceTarget target, String message, Map<String, String> properties)
    {
	send(target, message, String.class, properties);
    }

    @Override
    public <T> void send(ResourceTarget target, T entity, Class<T> cls, Map<String, String> properties)
    {
	Endpoint entry = CohesionMessagingHelper.resolveResourceToEndpoint(target, ResourceTarget.JMS_SCHEME, context);
	if (entry != null)
	{
	    sendMsg(entity, cls, properties, entry.getFormat(), entry.getJmsEndpoint(), logger);
	}
	else
	{
	    logger.error("Cannot send message for Resource: {}", target);
	}
    }
 }
