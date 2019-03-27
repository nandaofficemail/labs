package com.metlife.investments.cohesion.providers.messaging;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.slf4j.Logger;

import com.metlife.investments.cohesion.core.serializer.SerializerDeserializer;
public class JMSProviderContainer
{
    private JMSProvider jmsProvider;
    public JMSProviderContainer(JMSProvider jmsProvider)
    {
	this.jmsProvider = jmsProvider;
    }
    
    protected <T> void sendMsg(T entity, Class<T> cls, Map<String, String> properties, String dataContractType, String destination, Logger logger)
    {
	String message;
	try
	{
	    if (cls == String.class)
	    {
		message = (String) entity;
	    } 
	    else
	    {
		message = SerializerDeserializer.serialize(entity, cls,	SerializerDeserializer.formatType(dataContractType));
	    }
	    sendMessage(message, properties, destination, logger);
	} 
	catch (Exception e)
	{
	    logger.error("Exception when serializing", e);
	}
    }

    /** implements the send message functionality for JMS
     * 
     * @param message the string message
     * @param properties used to construct the header properties
     * @param destination destination
     * @param logger logging handler
     */
    protected void sendMessage(final String message, final Map<String, String> properties, String destination, final Logger logger)
    {
	JmsTemplate jmsTemplate = jmsProvider.getJmsTemplate();

	logger.debug("publishing Message {} to destination {}", message, destination);
	
	// send the message to the topic using the jmstemplate for topics
	jmsTemplate.send(destination, new MessageCreator() {
	public javax.jms.Message createMessage(Session session) throws JMSException 
	{
	    javax.jms.Message msg = session.createTextMessage(message);
	    // set the JMS header properties on the message
	    if (properties != null)
	    {
		for (String name : properties.keySet())
		{
		    msg.setStringProperty(name, properties.get(name));
		}
	    }
	    logger.debug("JMS Message {}", msg.toString());

	    return msg;
	}
	});
    }


}
