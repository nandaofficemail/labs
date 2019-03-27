package com.metlife.investments.cohesion.providers.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.StatusType;

import com.metlife.investments.cohesion.core.Response;

public class CohesionResponse<T> implements Response
{
    private Class<T> entityClass;
    private javax.ws.rs.core.Response rsResponse;
    
    public CohesionResponse(Class<T> entityClass, javax.ws.rs.core.Response res)
    {
	this.entityClass = entityClass;
	this.rsResponse = res;
    }

    @Override
    public String toString()
    {
	return this.getEntity(String.class);
    }
    @Override
    public <S> S getEntity(Class<S> scls)
    {
	return this.rsResponse.readEntity(scls);
    }
    @Override
    public <S> List<S> getEntityCollection(GenericType<List<S>> gt)
    {
	return this.rsResponse.readEntity(gt);
    }
    @Override
    public Class<?> getEntityClass()
    {
	return this.entityClass;
    }

    @Override
    public int getStatus()
    {
	return this.rsResponse.getStatus();
    }

    @Override
    public javax.ws.rs.core.Response.StatusType getStatusInfo()
    {
	return this.rsResponse.getStatusInfo();
    }

    @Override
    public boolean hasEntity()
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public String getMediaType()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int getLength()
    {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Date getDate()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Date getLastModified()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getHeaderString(String name)
    {
	// TODO Auto-generated method stub
	return null;
    }
}
