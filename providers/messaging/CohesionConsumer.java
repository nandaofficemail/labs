package com.metlife.investments.cohesion.providers.messaging;

import java.util.Map;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.metlife.investments.cohesion.core.Consumer;
import com.metlife.investments.cohesion.core.Context;
import com.metlife.investments.cohesion.core.EntityMessage;
import com.metlife.investments.cohesion.core.Listener;
import com.metlife.investments.cohesion.core.MessageHandler;
import com.metlife.investments.cohesion.core.registry.PublisherRegistryDTO;
import com.metlife.investments.cohesion.core.registry.dto.Endpoint;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.core.serializer.SerializerDeserializer;
import com.metlife.investments.cohesion.providers.CohesionContext;
import com.metlife.investments.cohesion.providers.CohesionEntityMessage;

public class CohesionConsumer implements Consumer
{
    private CohesionContext context;
    private JMSProvider jmsProvider;
    final static Logger logger = LoggerFactory.getLogger(CohesionConsumer.class);

    public CohesionConsumer(Context context, JMSProvider jmsProvider)
    {
	this.context = (CohesionContext) context;
	this.jmsProvider = jmsProvider;
    }

    @Override
    public <T> T consume(ResourceTarget target, Map<String, String> properties, Class<T> cls)
    {
	JmsTemplate jmsTemplate = jmsProvider.getJmsTemplate();

	logger.debug("synchronously reading Message from target: {}", target.toString());

	Endpoint entry = CohesionMessagingHelper.resolveResourceToEndpoint(target, ResourceTarget.JMS_SCHEME, context);
	Message message;

	if (properties != null && properties.size() > 0)
	{
	    logger.debug("Using Selector: {}", CohesionMessagingHelper.formatSQL92(properties));

	    message = jmsTemplate.receiveSelected(entry.getJmsEndpoint(), CohesionMessagingHelper.formatSQL92(properties));
	}
	else
	{
	    logger.debug("NO selector");
	    message = jmsTemplate.receive(entry.getJmsEndpoint());
	}
	logger.debug("received message: {}", message.toString());
	try
	{
	    TextMessage tm = (TextMessage) message;
	    String msgText = tm.getText();

	    T entity;
	    if (cls == String.class)
	    {
		entity = (T) msgText;
	    }
	    else
	    {
		entity = SerializerDeserializer.deserialize(msgText, cls,
			SerializerDeserializer.formatType(entry.getFormat()));
	    }

	    //EntityMessage em = new CohesionEntityMessage<T>(entity);
	    return entity;
	}
	catch (Exception e)
	{
	    logger.error("error when handling Message", e);
	    // TODO: should throw exception
	}

	return null;
    }

    @Override
    public <T> Listener registerConsumerListener(ResourceTarget target, Map<String, String> properties, Class<T> cls,
	    MessageHandler handler)
    {
	Endpoint entry = CohesionMessagingHelper.resolveResourceToEndpoint(target, ResourceTarget.JMS_SCHEME, context);
	if (entry != null)
	{
	    return new CohesionListener<T>(jmsProvider, false, entry.getJmsEndpoint(), properties, cls, handler,
		    SerializerDeserializer.formatType(entry.getFormat()), null, this.context);
	}
	else
	{
	    return null;
	}
    }

}
