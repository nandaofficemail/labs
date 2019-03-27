package com.metlife.investments.cohesion.exceptions;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Produces({MediaType.TEXT_HTML})
public class CohesionRESTExceptionMapper implements ExceptionMapper<CohesionRESTException> {  
  
    private static final Logger log = LoggerFactory.getLogger(CohesionRESTExceptionMapper.class.getName());  
      
    @Override
    public Response toResponse(CohesionRESTException exception) 
    {
        log.error("Service problem while executing a method", exception);

    	return Response.status(exception.getStatusCode()).entity("<CohesionExceptionMsg>Cohesion Exception encountered.  HTTP Status:"+exception.getStatusCode()+"-"+exception.getMessage()+"</CohesionExceptionMsg>").build();
    }



}  
