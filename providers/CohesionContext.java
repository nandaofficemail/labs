package com.metlife.investments.cohesion.providers;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;

import com.metlife.investments.cohesion.core.AsyncRequestor;
import com.metlife.investments.cohesion.core.Consumer;
import com.metlife.investments.cohesion.core.Context;
import com.metlife.investments.cohesion.core.Producer;
import com.metlife.investments.cohesion.core.Publisher;
import com.metlife.investments.cohesion.core.Requestor;
import com.metlife.investments.cohesion.core.Subscriber;
import com.metlife.investments.cohesion.core.registry.Registry;
import com.metlife.investments.cohesion.core.registry.RegistryService;
import com.metlife.investments.cohesion.exceptions.CohesionException;
import com.metlife.investments.cohesion.providers.messaging.CohesionConsumer;
import com.metlife.investments.cohesion.providers.messaging.CohesionProducer;
import com.metlife.investments.cohesion.providers.messaging.CohesionPublisher;
import com.metlife.investments.cohesion.providers.messaging.CohesionSubscriber;
import com.metlife.investments.cohesion.providers.messaging.JMSProvider;
import com.metlife.investments.cohesion.providers.registry.CohesionRegistryRequestor;
import com.metlife.investments.cohesion.providers.rest.CohesionAsyncRequestor;
import com.metlife.investments.cohesion.providers.rest.CohesionRequestor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.stereotype.Component;

@Component
public class CohesionContext implements Context
{
    private static final String COHESION_CONFIGURATION_XML = "/cohesion.xml";

    // constants that are used within the cohesion.xml configuration file
    private static final String COHESION_PROVIDERS = "cohesionProviders";
    private static final String DEFAULT_QUEUE_PROVIDER = "defaultQueueProvider";
    private static final String DEFAULT_TOPIC_PROVIDER = "defaultTopicProvider";
    private static final String COHESION_SERVICE_REGISTRY = "serviceRegistry";

    // context is a singleton
    private static CohesionContext context;
    private static ApplicationContext appContext;
    private static Map<Class<?>, Logger> loggerMap;
    private static Logger logger;
    
    private CohesionProviders cohesionProviders;
    private RegistryService serviceRegistry;
    private Map<String, JMSProvider> jmsProviders;
    private String defaultQueueProvider;
    private String defaultTopicProvider;

    static
    {
	loggerMap = new HashMap<Class<?>, Logger>();
	logger = CohesionContext.getLogger(CohesionContext.class);
	appContext = new ClassPathXmlApplicationContext(COHESION_CONFIGURATION_XML);
    }

    public static CohesionContext getContextInstance() throws CohesionException
    {
	if (context == null)
	{
	    context = new CohesionContext();
	    if (context == null)
	    {
		logger.error("CohesionContext is NULL");
		throw new CohesionException("CohesionContext could not be created");
	    }
	}
	return context;
    }

    protected CohesionContext()
    {
	logger.debug("Creating CohesionContext()");
	this.defaultQueueProvider = DEFAULT_QUEUE_PROVIDER;
	this.defaultTopicProvider = DEFAULT_TOPIC_PROVIDER;
	this.jmsProviders = new HashMap<String, JMSProvider>();
    }

    public void initialize()
    {
	logger.debug("CohesionContext.initialize()");

	this.cohesionProviders = (CohesionProviders) appContext.getBean(COHESION_PROVIDERS);
	this.serviceRegistry = (RegistryService) appContext.getBean(COHESION_SERVICE_REGISTRY);
	logger.debug("refreshing the Service Registry");
	this.serviceRegistry.refresh();
	for (String jmsProviderName : cohesionProviders.getJMSProviderNames())
	{
	    jmsProviderName = jmsProviderName.trim();
	    ConnectionFactory queueManager = (ConnectionFactory) appContext.getBean(jmsProviderName);
	    JMSProvider jmsProvider = new JMSProvider(queueManager);
	    jmsProvider.initializeProvider();

	    if (jmsProviderName.toLowerCase().contains("topic"))
	    {
		// comes here if the provider is for topics
		jmsProvider.setAsTopicProvider();
		// use this set the first topic provider as the "Default" topic
		// provider
		if (this.defaultTopicProvider != null)
		{
		    jmsProviders.put(this.defaultTopicProvider, jmsProvider);
		    this.defaultTopicProvider = null;
		}
	    }
	    else
	    {
		// comes here if the provider is for queues
		// use this set the first queue provider as the "Default" queue
		// provider
		if (this.defaultQueueProvider != null)
		{
		    jmsProviders.put(this.defaultQueueProvider, jmsProvider);
		    this.defaultQueueProvider = null;
		}
	    }
	    jmsProviders.put(jmsProviderName, jmsProvider);
	}
    }

    public static Logger getLogger(Class<?> cls)
    {
	if (CohesionContext.loggerMap.containsKey(cls))
	{
	    return CohesionContext.loggerMap.get(cls);
	}
	else
	{
	    Logger lgr = LoggerFactory.getLogger(cls);
	    CohesionContext.loggerMap.put(cls, lgr);
	    return lgr;
	}
    }

    public JMSProvider getJMSProvider(String providerName)
    {
	return this.jmsProviders.get(providerName);
    }

    public void close()
    {
	logger.debug("Calling: CohesionContext.close()");
	for (JMSProvider p : this.jmsProviders.values())
	{
	    p.close();
	}
    }

    @Override
    public Subscriber createSubscriber()
    {
	logger.debug("createSubscriber(DEFAULT_TOPIC_PROVIDER)");
	return new CohesionSubscriber(this, this.getJMSProvider(DEFAULT_TOPIC_PROVIDER));
    }

    @Override
    public Subscriber createSubscriber(String jmsProviderName)
    {
	logger.debug("createSubscriber()");
	return new CohesionSubscriber(this, this.getJMSProvider(jmsProviderName));
    }

    @Override
    public Publisher createPublisher()
    {
	logger.debug("createPublisher(DEFAULT_TOPIC_PROVIDER)");
	return new CohesionPublisher(this, this.getJMSProvider(DEFAULT_TOPIC_PROVIDER));
    }

    @Override
    public Publisher createPublisher(String jmsProviderName)
    {
	logger.debug("createPublisher()");
	return new CohesionPublisher(this, this.getJMSProvider(jmsProviderName));
    }

    @Override
    public Requestor createRequestor()
    {
	logger.debug("createRequestor()");
	return new CohesionRequestor(this);
    }

    public RegistryService getServiceRegistry()
    {
	return serviceRegistry;
    }

    @Override
    public Producer createProducer() throws CohesionException
    {
	logger.debug("createProducer(DEFAULT_QUEUE_PROVIDER)");
	return new CohesionProducer(this, this.getJMSProvider(DEFAULT_QUEUE_PROVIDER));
    }

    @Override
    public Producer createProducer(String jmsProviderName) throws CohesionException
    {
	logger.debug("createProducer()");
	return new CohesionProducer(this, this.getJMSProvider(jmsProviderName));
    }

    @Override
    public Consumer createConsumer() throws CohesionException
    {
	logger.debug("createConsumer(DEFAULT_QUEUE_PROVIDER)");
	return new CohesionConsumer(this, this.getJMSProvider(DEFAULT_QUEUE_PROVIDER));
    }

    @Override
    public Consumer createConsumer(String jmsProviderName) throws CohesionException
    {
	logger.debug("createConsumer()");
	return new CohesionConsumer(this, this.getJMSProvider(jmsProviderName));
    }

    @Override
    public AsyncRequestor createAsyncRequestor() throws CohesionException
    {
	logger.debug("createAsyncRequestor()");
	return new CohesionAsyncRequestor(this);
    }

}
