package com.metlife.investments.cohesion.exceptions;

import javax.ws.rs.core.Response.Status;

public class CohesionRESTException extends CohesionException
{
    private int statusCode;

    public CohesionRESTException(String msg)
    {
	super(msg);
    }

    public CohesionRESTException(int statusCode, String msg)
    {
	super(msg);
	this.statusCode = statusCode;
    }

    public CohesionRESTException(int statusCode, String msg, Exception e)
    {
	super(msg, e);
	this.statusCode = statusCode;
    }

    public int getStatusCode()
    {
	return statusCode;
    }

}
