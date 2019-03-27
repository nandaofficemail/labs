package com.metlife.investments.cohesion.core;

import org.slf4j.Logger;

import com.metlife.investments.cohesion.exceptions.CohesionException;


/** Context is an application context interface that represents a connection 
 * to the Cohesion framework.   This interface has methods to create components that
 * the application can use to communicate with and utilize the cohesion framework.
 */
public interface Context
{
    /** initializes the application context.
     * 
     * @exception CohesionException if any exception occurred
     */
    void initialize() throws CohesionException;

    /** closes the cohesion application context
     * 
     * @exception CohesionException if any exception occurred
     */
    void close() throws CohesionException;

    /** factory method used to create a subscriber to be used to subscribe to cohesion events - uses the default JMS provider
     * 
     * @return an instance of a Subscriber that can be used to subscribe to events about resources
     * @exception CohesionException if any exception occurred
     */
    Subscriber createSubscriber() throws CohesionException;
    
    /** factory method used to create a subscriber to be used to subscribe to cohesion events
     * 
     * @param jmsProviderName name of the JMS provider component to use for this subscription
     * @return an instance of a Subscriber that can be used to subscribe to events about resources
     * @exception CohesionException if any exception occurred
     */ 
    Subscriber createSubscriber(String jmsProviderName) throws CohesionException;
    
    /** factory method used to create a publisher to be used to publish cohesion events to 
     * interested subscribers - uses the default JMS provider
     * 
     * @return an instance of a Publisher that can be used to publish events about resources
     * @exception CohesionException if any exception occurred
     */
    Publisher createPublisher() throws CohesionException;

    /** factory method used to create a publisher to be used to publish cohesion events to 
     * interested subscribers
     * 
     * @param jmsProviderName name of the JMS provider component to use for this publisher
     * @return an instance of a Publisher that can be used to publish events about resources
     * @exception CohesionException if any exception occurred
     */
    Publisher createPublisher(String jmsProviderName) throws CohesionException;
    
    /** factory method used to create a requestor to be used to invoke synchronous request/response requests.
     * 
     * @return an instance of a Requestor that can be used to make RESTful requests of resources
     * @exception CohesionException if any exception occurred
     */
    Requestor createRequestor() throws CohesionException;

    /** factory method used to create a Producer which is used to send messages to a messaging queue.
     *  The messages can then be received by Consumers.  This method should create a Producer using
     *  the default jms provider.
     * 
     * @return an instance of a Producer that can be used to send messages
     * @exception CohesionException if any exception occurred
     */
    Producer createProducer() throws CohesionException;
    
    /** factory method used to create a Producer which is used to send messages to a messaging queue.
     *  The messages can then be received by Consumers. 
     * 
     * @param jmsProviderName name of the JMS provider component to use for this sender
     * @return an instance of a Producer that can be used to send messages
     * @exception CohesionException if any exception occurred
     */
    Producer createProducer(String jmsProviderName) throws CohesionException;
    
    /** factory method used to create a Consumer which can be used to receive messages from a messaging queue.
     *  This method should create a Consumer using the default jms provider.
     * 
     * @return an instance of a Consumer that can be used to receive messages
     * @exception CohesionException if any exception occurred
     */
    Consumer createConsumer() throws CohesionException;
    
    /** factory method used to create a Consumer which can be used to receive messages from a messaging queue.
     *  This method should create a Consumer using the default jms provider.
     * 
     * @param jmsProviderName name of the JMS provider component to use for this Receiver
     * @return an instance of a Consumer that can be used to receive messages
     * @exception CohesionException if any exception occurred
     */
    Consumer createConsumer(String jmsProviderName) throws CohesionException;
    
    /** factory method used to create an asynchronous requestor to be used to invoke asynchronous request/response requests.
     * 
     * @return an instance of a AsyncRequestor that can be used to make RESTful requests of resources
     * @exception CohesionException if any exception occurred
     */
    AsyncRequestor createAsyncRequestor() throws CohesionException;
    
}
