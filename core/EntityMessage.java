package com.metlife.investments.cohesion.core;

import java.util.List;

import javax.ws.rs.core.GenericType;

/** EntityMessage is an interface to an object that contains a generic entity,
 * where the entity is just a POJO representing the payload of a cohesion message.
 *
 */
public interface EntityMessage
{
    /** extracts the entity object out from the message
     * 
     * @param c the class instance
     * @param <S> the type of the entity
     * @return the entity
     */
    <S> S getEntity(Class<S> c);
    
    /** extracts the collection of entity objects out from the message
     * 
     * @param gt the GenericType
     * @param <S> the type of the entity
     * @return the List of entities
     */
    <S> List<S> getEntityCollection(GenericType<List<S>> gt);
    
    /** extract the class of the entity
     * 
     * @return the class object
     */
    Class<?> getEntityClass();
}
