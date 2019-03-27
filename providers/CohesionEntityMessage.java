package com.metlife.investments.cohesion.providers;

import java.util.List;

import javax.ws.rs.core.GenericType;

import com.metlife.investments.cohesion.core.EntityMessage;

/**
 * a parameterized container of Entity messages.  Implements the EntityMessage interface for
 * cohesion.
 * 
 * @param <T> the type of the response
 */
public class CohesionEntityMessage<T> implements EntityMessage
{
    private T entity;
    
    public CohesionEntityMessage(T e)
    {
	this.entity = e;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <S> S getEntity(Class<S> cls)
    {
	if (this.entity.getClass() == cls)
	{
	    return (S) this.entity;
	}
	else
	{
	    // TODO: should throw exception vs return null
	    return null;
	}
    }

    @Override
    public Class<?> getEntityClass()
    {
	return this.entity.getClass();
    }

    @Override
    public <S> List<S> getEntityCollection(GenericType<List<S>> gt)
    {
	throw new UnsupportedOperationException();
    }
}
