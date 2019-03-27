package com.metlife.investments.cohesion.core;

import java.util.Map;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;

/** Receiver is an interface to the instance that allows Cohesion clients to receive messages on the Cohesion
 * platform.
 */
public interface Consumer
{
    /** synchronously wait for and consume a message sent by a Cohesion Producer to a resource 
     * 
     * @param target defines the resource
     * @param properties used to filter messages
     * @param cls the class of the entity expected in the message
     * @param <T> the type of the entity consumed
     * @return the entity
     */
    <T> T consume(ResourceTarget target, Map<String, String> properties, Class<T> cls);

    /** register a handler to asynchronously listen for and consume messages that are sent by a Cohesion Producer to a resource 
     * 
     * @param target defines the resource
     * @param properties used to filter messages
     * @param cls the class of the entity expected in the message
     * @param handler callback handler that will receive the message
     * @param <T> the type of the entity consumed
     * @return a Listener
     */
    <T> Listener registerConsumerListener(ResourceTarget target, Map<String, String> properties, Class<T> cls, MessageHandler handler);
}
