package com.metlife.investments.cohesion.core.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metlife.investments.cohesion.core.Context;
import com.metlife.investments.cohesion.exceptions.CohesionException;
import com.metlife.investments.cohesion.providers.CohesionContext;

public class BaseCohesionService
{
    private CohesionContext context;
    private Logger logger;

    static
    {
    }

    public BaseCohesionService(Class<?> classInstance) throws CohesionException
    {
	logger = LoggerFactory.getLogger(classInstance);
	context = CohesionContext.getContextInstance();

	initialize();
    }

    /**
     * Initializes the Cohesion REST service base class
     * 
     */
    protected void initialize()
    {
    }

    protected Logger getLogger()
    {
	return this.logger;
    }

    public Context getContext()
    {
	return this.context;
    }
}
