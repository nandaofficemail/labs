package com.metlife.investments.cohesion.core;

import java.util.Map;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;

/** Publisher is an interface to the instance that allows Cohesion clients to publish messages
 * on the Cohesion platform, for subsequent consumption by Cohesion subscribers.
 */

public interface Publisher 
{
    /** publishes events about a resource 
     * 
     * @param r defines the resource
     * @param message the string message to be published
     * @param properties set by the publisher that can be used by the subscriber for filtering
     */
    void publish(ResourceTarget r, String message, Map<String, String> properties);
    
    /** publishes events about a resource 
     * 
     * @param r defines the resource
     * @param entity the bean that is to be published
     * @param cls the class object representing the entity
     * @param <T> the type of the entity
     * @param properties set by the publisher that can be used by the subscriber for filtering
     */
    <T> void publishEntity(ResourceTarget r, T entity, Class<T> cls, Map<String, String> properties);
}
