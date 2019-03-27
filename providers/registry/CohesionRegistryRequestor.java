package com.metlife.investments.cohesion.providers.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metlife.investments.cohesion.core.Requestor;
import com.metlife.investments.cohesion.core.Response;
import com.metlife.investments.cohesion.core.registry.PubSubRegistryList;
import com.metlife.investments.cohesion.core.registry.PublisherRegistryDTO;
import com.metlife.investments.cohesion.core.registry.Registry;
import com.metlife.investments.cohesion.core.registry.ReqRespEntity;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.providers.CohesionContext;


public class CohesionRegistryRequestor implements Registry
{
    private static Logger logger = LoggerFactory.getLogger(CohesionRegistryRequestor.class);

    private CohesionContext cohesionContext;
    
    public CohesionRegistryRequestor(CohesionContext cohesionContext)
    {

    }
    
    public void refresh()
    {

    }
    
    public List<ReqRespEntity> getReqRespEntity(ResourceTarget pat)
    {
	return null;
    }

    public PublisherRegistryDTO getPubSubEntity(ResourceTarget key)
    {
	return null;
    }
}
