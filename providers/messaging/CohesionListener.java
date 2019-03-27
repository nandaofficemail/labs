package com.metlife.investments.cohesion.providers.messaging;

import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.metlife.investments.cohesion.core.EntityMessage;
import com.metlife.investments.cohesion.core.Format;
import com.metlife.investments.cohesion.core.MessageHandler;
import com.metlife.investments.cohesion.core.Listener;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.core.serializer.SerializerDeserializer;
import com.metlife.investments.cohesion.providers.CohesionContext;
import com.metlife.investments.cohesion.providers.CohesionEntityMessage;
import com.metlife.investments.cohesion.providers.CohesionMessage;

public class CohesionListener<T> implements Listener, MessageListener
{
    private static Logger logger = LoggerFactory.getLogger(CohesionListener.class);
    private JMSProvider jmsProvider;
    private Class<T> aClass;
    private MessageHandler handler;
    private Format format;
    private String subscriptionName;
    private DefaultMessageListenerContainer listenerContainer;

    public CohesionListener(JMSProvider provider, boolean pubSub, String topicQueue, Map<String, String> props, Class<T> cls, 
	    MessageHandler hndlr, Format format,
	    CohesionContext context)
    {
	this(provider, pubSub, topicQueue, CohesionMessagingHelper.formatSQL92(props), cls, hndlr, format, null, context);
    }

    public CohesionListener(JMSProvider provider, boolean pubSub, String topicQueue, Map<String, String> props, Class<T> cls, 
	    MessageHandler hndlr, Format format,
	    String subscriptionName, CohesionContext context)
    {
	this(provider, pubSub, topicQueue, CohesionMessagingHelper.formatSQL92(props), cls, hndlr, format, subscriptionName, context);
    }

    public CohesionListener(JMSProvider provider, boolean pubSub, String topicQueue, String messageSelector, Class<T> cls, 
	    MessageHandler hndlr, Format format,
	    String subscriptionName, CohesionContext context)
    {
	logger.debug("setting up a subscription for: {}", topicQueue);
	this.handler = hndlr;
	this.aClass = cls;
	this.format = format;
	this.subscriptionName = subscriptionName;
	this.jmsProvider = provider;
	
	this.listenerContainer = new DefaultMessageListenerContainer();
	this.listenerContainer.setConnectionFactory(jmsProvider.getConnectionFactory());
	this.listenerContainer.setDestinationName(topicQueue);
	this.listenerContainer.setMessageListener(this);

	// this is only if the subscription is on a topic
	if (pubSub)
	{
        	this.listenerContainer.setPubSubDomain(true);
        
        	if (subscriptionName != null)
        	{
        	    // make this a durable subscription
        	    this.listenerContainer.setDurableSubscriptionName(subscriptionName);
        	    this.listenerContainer.setSubscriptionDurable(subscriptionName != null);
        	}
	}
	
    	if (messageSelector != null)
    	{
    	    this.listenerContainer.setMessageSelector(messageSelector);
    	}
	this.listenerContainer.afterPropertiesSet();
	this.listenerContainer.start();
    }

    public void stopListening()
    {
	this.listenerContainer.stop();
    }

    @SuppressWarnings("unchecked")
    public void onMessage(Message message)
    {
	logger.debug("subscription got message via onMessage: {}",
		message.toString());
	try
	{
	    TextMessage tm = (TextMessage) message;
	    String msgText = tm.getText();

	    T entity;
	    if (this.aClass == String.class)
	    {
		entity = (T) msgText;
	    } else
	    {
		entity = SerializerDeserializer.deserialize(msgText, this.aClass, this.format);
	    }

	    EntityMessage em = new CohesionEntityMessage<T>(entity);
	    this.handler.onMessage(em);
	} catch (Exception e)
	{
	    logger.error("error when handling Message", e);
	    // TODO: should throw exception
	}
    }


}
