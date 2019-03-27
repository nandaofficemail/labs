package com.metlife.investments.cohesion.providers.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;

import com.metlife.investments.cohesion.core.Context;
import com.metlife.investments.cohesion.core.Format;
import com.metlife.investments.cohesion.core.Requestor;
import com.metlife.investments.cohesion.core.Response;
import com.metlife.investments.cohesion.core.registry.dto.Endpoint;
import com.metlife.investments.cohesion.core.resource.ClassTarget;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;
import com.metlife.investments.cohesion.exceptions.CohesionException;
import com.metlife.investments.cohesion.providers.CohesionContext;
import com.metlife.investments.cohesion.providers.messaging.CohesionMessagingHelper;

public class CohesionRequestor implements Requestor
{
    private ResourceTarget resource;
    private Format formatType;
    private CohesionContext cohesionContext;

    public CohesionRequestor(Context cohesion)
    {
	this.cohesionContext = (CohesionContext) cohesion;
	this.formatType = Format.UNSPECIFIED;
    }

    @Override
    public Requestor setResource(ResourceTarget rt)
    {
	this.resource = rt;
	return this;
    }

    @Override
    public Requestor setFormat(Format type)
    {
	this.formatType = type;
	return this;
    }
    @Override
    public ResourceTarget getResource()
    {
	return this.resource;
    }

    @Override
    public Response get(ResourceTarget rt)
    {
	return get(rt, String.class);
    }

    @Override
    public <T> Response get(ResourceTarget rtarget, Class<T> cls, Format type)
    {
	ResourceTarget rt;
	try
	{
	    rt = CohesionHttpHelper.resolveResourceToEndpoint(rtarget, cohesionContext);
	    this.setResource(rt);

	    final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig().setIncludeRoot(false);

	    final ContextResolver<MoxyJsonConfig> jsonConfigResolver = moxyJsonConfig.resolver();

	    Client client = ClientBuilder.newClient();
	    client.register(jsonConfigResolver);
	    client.register(MoxyJsonFeature.class);
	    client.register(MoxyXmlFeature.class);

	    WebTarget target = client.target(this.resource.getURI());
	    String mediaType;
	    if (type == Format.XML)
	    {
		mediaType = "application/xml";
	    }
	    else if (type == Format.JSON)
	    {
		mediaType = "application/json";
	    }
	    else
	    {
		mediaType = "application/plain";
	    }

	    javax.ws.rs.core.Response res = target.request(mediaType).get();

	    // buffer the entity data so that we are depending on the input
	    // stream
	    // which
	    // could be closed before we get to consume the entity.
	    res.bufferEntity();
	    return new CohesionResponse<T>(cls, res);
	}
	catch (CohesionException ce)
	{
	    return new CohesionErrorResponse<T>(cls, 400, ce.getMessage());
	}
    }

    @Override
    public <T> Response get(ResourceTarget rt, Class<T> cls)
    {
	return get(rt, cls, this.formatType == Format.UNSPECIFIED ? Format.XML : this.formatType);
    }

    @Override
    public <T> Response getByType(Class<T> cls)
    {
	return this.get(new ClassTarget(cls), cls);
    }

    @Override
    public Response delete(ResourceTarget rt)
    {
	this.setResource(rt);

	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Response head(ResourceTarget rt)
    {
	this.setResource(rt);

	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T> Response post(ResourceTarget rt, T entity)
    {
	this.setResource(rt);

	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T, S> Response post(ResourceTarget rt, T entity, Class<S> cls)
    {
	this.setResource(rt);

	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T> Response put(ResourceTarget rt, T entity)
    {
	this.setResource(rt);

	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <T, S> Response put(ResourceTarget rt, T entity, Class<S> cls)
    {
	this.setResource(rt);

	// TODO Auto-generated method stub
	return null;
    }


}
