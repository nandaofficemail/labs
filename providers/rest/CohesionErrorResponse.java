package com.metlife.investments.cohesion.providers.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class CohesionErrorResponse<T> extends CohesionResponse<T>
{
    private int errorStatus;
    
    // TODO: need to expose the error string
    private String error;
    
    public CohesionErrorResponse(Class<T> entityClass, int status, String error)
    {
	super(entityClass, null);
	errorStatus = status;
	this.error = error;
    }
    @Override
    public String toString()
    {
	return this.getEntity(String.class);
    }
    @Override
    public <S> S getEntity(Class<S> scls)
    {
	return null;
    }
    @Override
    public <S> List<S> getEntityCollection(GenericType<List<S>> gt)
    {
	return null;
    }

    @Override
    public int getStatus()
    {
	return errorStatus;
    }

    @Override
    public javax.ws.rs.core.Response.StatusType getStatusInfo()
    {
	return null;
    }

    @Override
    public boolean hasEntity()
    {
	return false;
    }

    @Override
    public String getMediaType()
    {
	return null;
    }

    @Override
    public int getLength()
    {
	return 0;
    }

    @Override
    public Date getDate()
    {
	return null;
    }

    @Override
    public Date getLastModified()
    {
	return null;
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders()
    {
	return null;
    }

    @Override
    public String getHeaderString(String name)
    {
	return null;
    }
}
