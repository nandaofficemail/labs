package com.metlife.investments.cohesion.exceptions;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

public class CohesionException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CohesionException(String msg)
    {
	super(msg);
    }

    public CohesionException(String msg, Exception e)
    {
	super(msg, e);
    }

}
