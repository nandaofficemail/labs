package com.metlife.investments.cohesion.core;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;


public interface Requestor 
{
    /** Sets the specific resource target that this requestor will act on.
     * 
     * @param target the resource target
     * @return the Requester object itself, which allows the method to be used fluently
     */
    Requestor setResource(ResourceTarget target);
    
    /** Gets the resource target that has been set on this requestor
     * 
     * @return the resource target
     */
    ResourceTarget getResource();
    
    /** Sets the format of the data to be returned by the requestor
     * 
     * @param type Format type
     * @return the Requestor object itself, which allows the method to be used fluently
     */
    Requestor setFormat(Format type);
    
    /** Invoke HTTP GET method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @return the response
     */
    Response get(ResourceTarget target);
    
    /** Invoke HTTP GET method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @param cls the class type of the returned response
     * @param <T> the type of the entity
     * @return the response
     */
    <T> Response get(ResourceTarget target, Class<T> cls);
    
    /** Invoke HTTP GET method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @param cls the class type of the returned response
     * @param <T> the type of the entity
     * @param type the format of data returned.  Most useful when cls is a String
     * @return the response
     */
    <T> Response get(ResourceTarget target, Class<T> cls, Format type);
    
    /** Invoke HTTP GET method for the current requestor using the type of the class
     * to identify the endpoint that would serve that type.  This method does not require
     * a resource target, and so assumes that there is a cohesion mapping that can be used to resolve the endpoint
     * based on the type.
     * 
     * @param cls the class type of the returned response
     * @param <T> the type of the entity
     * @return the response
     */
    <T> Response getByType(Class<T> cls);
       
    /** Invoke HTTP DELETE method for the current requestor synchronously.
     * 
     * @param target the resource target
     * @return the response
     */
    Response delete(ResourceTarget target);
    
    /** Invoke HTTP HEAD for the current requestor synchronously
     * 
     * @param target the resource target
     * @return the response
     */
    Response head(ResourceTarget target);

    /** Invoke HTTP POST for the current requestor synchronously
     * 
     * @param target the resource target
     * @param <T> the type of the entity
     * @param entity is the object to be passed in the body of the request
     * @return the response
     */
    <T> Response post(ResourceTarget target, T entity);

    /**  Invoke HTTP POST for the current requestor synchronously
     * 
     * @param target the resource target
     * @param <T> the type of the input entity
     * @param <S> the type of the Response entity
     * @param entity  is the object to be passed in the body of the request
     * @param cls is the type of the response
     * @return the response
     */
    <T,S> Response post(ResourceTarget target, T entity, Class<S> cls);
    
    /** Invoke HTTP PUT for the current requestor synchronously
     * 
     * @param target the resource target
     * @param <T> the type of the entity
     * @param entity is the object to be passed in the body of the request
     * @return the response
     */
    <T> Response put(ResourceTarget target, T entity);

    /**  Invoke HTTP PUT for the current requestor synchronously
     * 
     * @param target the resource target
     * @param <T> the type of the input entity
     * @param <S> the type of the response entity
     * @param entity  is the object to be passed in the body of the request
     * @param cls is the type of the response
     * @return the response
     */
    <T,S> Response put(ResourceTarget target, T entity, Class<S> cls);
}
