package com.metlife.investments.cohesion.core;

import java.util.Map;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.exceptions.CohesionException;

public interface CohesionClient
{
    /** initializes the cohesion client abstraction API.
     * 
     * @exception CohesionException if any exception occurred
     */
    void initialize() throws CohesionException;

    /** closes the cohesion client
     * 
     * @exception CohesionException if any exception occurred
     */
    void close() throws CohesionException;

    /** Invoke GET method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @return the response
     */
    Response get(ResourceTarget target);
    
    /** Invoke GET method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @param cls the class type of the returned response
     * @param <T> the type of the entity
     * @return the response
     */
    <T> Response get(ResourceTarget target, Class<T> cls);
    
    /** Invoke GET method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @param cls the class type of the returned response
     * @param <T> the type of the entity
     * @param type the format of data returned.  Most useful when cls is a String
     * @return the response
     */
    <T> Response get(ResourceTarget target, Class<T> cls, Format type);
    
    /** Invoke GET method for the current requestor using the type of the class
     * to identify the endpoint that would serve that type.  This method does not require
     * a resource target, and so assumes that there is a cohesion mapping that can be used to resolve the endpoint
     * based on the type.
     * 
     * @param cls the class type of the returned response
     * @param <T> the type of the entity
     * @return the response
     */
    <T> Response getByType(Class<T> cls);
       
    /** Invoke POST for the current requestor synchronously
     * 
     * @param target the resource target
     * @param <T> the type of the entity
     * @param entity is the object to be passed in the body of the request
     * @return the response
     */
    <T> Response post(ResourceTarget target, T entity);

    /**  Invoke POST for the current requestor synchronously
     * 
     * @param target the resource target
     * @param <T> the type of the input entity
     * @param <S> the type of the Response entity
     * @param entity  is the object to be passed in the body of the request
     * @param cls is the type of the response
     * @return the response
     */
    <T,S> Response post(ResourceTarget target, T entity, Class<S> cls);
    
    /** Invoke DELETE method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @return the response
     */
    Response delete(ResourceTarget target);
    
    /** publishes events about a resource 
     * 
     * @param r defines the resource
     * @param message the string message to be published
     * @param properties set by the publisher that can be used by the subscriber for filtering
     */
    void publish(ResourceTarget r, String message, PropVals properties);
    
    /** publishes events about a resource 
     * 
     * @param r defines the resource
     * @param entity the bean that is to be published
     * @param cls the class object representing the entity
     * @param <T> the type of the entity
     * @param properties set by the publisher that can be used by the subscriber for filtering
     */
    <T> void publishEntity(ResourceTarget r, T entity, Class<T> cls, Map<String, String> properties);

    /** subscribes to events that are published about a resource - will create a non-durable subscription
     * 
     * @param target defines the resource
     * @param properties used to filter events
     * @param cls the class of the entity
     * @param <T> the type of the entity
     * @param handler callback handler that will receive the message
     * @return a subscription
     */
    <T> Listener subscribe(ResourceTarget target, Map<String, String> properties, Class<T> cls, MessageHandler handler);

    /** durably subscribes to events that are published about a resource - this creates a durable subscription
     * 
     * @param target defines the resource
     * @param properties used to filter events
     * @param cls the class of the entity
     * @param <T> the type of the entity
     * @param handler callback handler that will receive the message
     * @param durableSubscriptionName name of the durable subscription. 
     * @return a subscription
     */
    <T> Listener subscribe(ResourceTarget target, Map<String, String> properties, Class<T> cls, MessageHandler handler, String durableSubscriptionName);
    
    /** unsubscribe to events published on a resource
     * 
     * @param s the subscription returned from a previous subscribe() method
     */
    void unsubscribe(Listener s);

}
