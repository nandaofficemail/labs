package com.metlife.investments.cohesion.providers.messaging;

import javax.jms.ConnectionFactory;

import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

public class JMSProvider
{
    private ConnectionFactory queueManager;
    private DynamicDestinationResolver destinationResolver;
    private UserCredentialsConnectionFactoryAdapter connectionFactory;
    private long defaultReceiveTimeout;
    private JmsTemplate jmsTemplate;
    
    public JMSProvider(ConnectionFactory queueManager)
    {
	this.queueManager = queueManager;
	this.defaultReceiveTimeout = 0;
    }
    
    public void initializeProvider()
    {
	this.destinationResolver = new DynamicDestinationResolver();
	
	// this.connectionFactory = new SingleConnectionFactory();
	this.connectionFactory = new UserCredentialsConnectionFactoryAdapter();
	this.connectionFactory.setUsername("mqtest");
	this.connectionFactory.setPassword("");
	this.connectionFactory.setTargetConnectionFactory(this.queueManager);
	this.jmsTemplate = new JmsTemplate();
	this.jmsTemplate.setDestinationResolver(this.destinationResolver);
	this.jmsTemplate.setConnectionFactory(this.connectionFactory);
	this.jmsTemplate.setPubSubDomain(false);
	this.jmsTemplate.setReceiveTimeout(this.defaultReceiveTimeout);
	this.jmsTemplate.afterPropertiesSet();
	this.jmsTemplate.setPubSubDomain(false);  // default to being a queue provider
    }
    
    public void setAsQueueProvider()
    {
	this.jmsTemplate.setPubSubDomain(false);
    }
    public void setAsTopicProvider()
    {
	this.jmsTemplate.setPubSubDomain(true);
    }
    public JmsTemplate getJmsTemplate()
    {
	return this.jmsTemplate;
    }
    public ConnectionFactory getConnectionFactory()
    {
	return this.connectionFactory;
    }
    public void close()
    {
	//this.connectionFactory.destroy();
    }
}
