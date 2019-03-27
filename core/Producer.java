package com.metlife.investments.cohesion.core;

import java.util.Map;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;

/** Producer is an interface to the instance that allows Cohesion clients to send messages
 * on the Cohesion platform, for subsequent consumption by Cohesion consumers.
 */
public interface Producer
{
    /** send a message via the cohesion platform, where the target of the message is the specific resource target
     * 
     * @param target specifies the resource
     * @param message the string message to be published
     * @param properties set by the sender that can be used by the receiver for filtering
     */
    void send(ResourceTarget target, String message, Map<String,String> properties);
    
    /** send a message via the cohesion platform, where the target of the message is the specific resource target
     * 
     * @param target specifies the resource
     * @param entity the bean that is to be serialized and delivered
     * @param cls the class of the entity
     * @param <T> the type of the entity
     * @param properties set by the sender that can be used by the receiver for filtering
     */
    <T> void send(ResourceTarget target, T entity, Class<T> cls, Map<String,String> properties);
}
