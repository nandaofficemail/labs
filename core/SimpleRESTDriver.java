package com.metlife.investments.cohesion.core.rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.metlife.investments.cohesion.exceptions.CohesionRESTExceptionMapper;

public class SimpleRESTDriver extends ResourceConfig
{
	private static Logger logger = LoggerFactory.getLogger(SimpleRESTDriver.class);
	private String uri;
	private int port;
	private boolean done;
	
	public SimpleRESTDriver() 
	{
		logger.info("Instantiating SimpleRESTDriver");
		register(CohesionRESTExceptionMapper.class);
		done=false;
	}
	public SimpleRESTDriver resources(String pkg)
	{
		packages(pkg);
		return this;
	}
	public SimpleRESTDriver port(int p)
	{
		this.port = p;
		return this;
	}
	public SimpleRESTDriver uri(String u)
	{
		this.uri = u;
		return this;
	}
	private URI getBaseURI() 
	{
		return UriBuilder.fromUri(getURI()).port(getPort()).build();
	}
	private  String getURI()
	{
		return this.uri!=null?this.uri:"http://0.0.0.0/api";
	}
	private  int getPort() 
	{
		return this.port!=0?this.port:8989;
	}
	public void stop()
	{
		done=true;
	}
	public void run()
	{
		final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(getBaseURI(), this);
		server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("public"), "/");
		logger.info("SimpleRESTDriver running");
		while (!done) {}
		logger.info("SimpleRESTDriver stopping");
	}
}
