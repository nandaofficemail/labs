package com.metlife.investments.cohesion.providers.messaging;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.metlife.investments.cohesion.core.Context;
import com.metlife.investments.cohesion.core.Format;
import com.metlife.investments.cohesion.core.MessageHandler;
import com.metlife.investments.cohesion.core.Subscriber;
import com.metlife.investments.cohesion.core.Listener;
import com.metlife.investments.cohesion.core.registry.PublisherRegistryDTO;
import com.metlife.investments.cohesion.core.registry.Registry;
import com.metlife.investments.cohesion.core.registry.dto.Endpoint;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.core.serializer.SerializerDeserializer;
import com.metlife.investments.cohesion.providers.CohesionContext;
import com.metlife.investments.cohesion.providers.CohesionMessage;

public class CohesionSubscriber implements Subscriber
{
    private CohesionContext context;
    private JMSProvider jmsProvider;

    final static Logger logger = LoggerFactory.getLogger(CohesionSubscriber.class);

    public CohesionSubscriber(Context cohesion, JMSProvider jmsProvider)
    {
	this.context = (CohesionContext) cohesion;
	this.jmsProvider = jmsProvider;
    }

    @Override
    public <T> Listener subscribe(ResourceTarget target, Map<String, String> properties, Class<T> cls,
	    final MessageHandler handler)
    {
	return subscribe(target, properties, cls, handler, null);
    }

    @Override
    public <T> Listener subscribe(ResourceTarget target, Map<String, String> properties, Class<T> cls,
	    MessageHandler handler, String durableSubscriptionName)
    {
	Endpoint entry = CohesionMessagingHelper.resolveResourceToEndpoint(target, ResourceTarget.JMS_SCHEME, context);
	if (entry != null)
	{
	    return new CohesionListener<T>(jmsProvider, true, entry.getJmsEndpoint(), properties, cls, handler,
			SerializerDeserializer.formatType(entry.getFormat()), durableSubscriptionName,
			this.context);
	}
	else
	{
	    logger.error("Cannot create listener for Resource: {}", target);
	    return null;
	}
    }

    @Override
    public void unsubscribe(Listener s)
    {
	s.stopListening();
    }

}
