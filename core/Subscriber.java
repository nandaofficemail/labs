package com.metlife.investments.cohesion.core;

import java.util.Map;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;

/** Subscriber is an interface to the instance that allows Cohesion clients to subscribe to and asynchonously 
 * receive events on resource targets which have been published by other Cohesion clients.
 */
public interface Subscriber
{
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
