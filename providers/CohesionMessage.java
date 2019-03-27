package com.metlife.investments.cohesion.providers;

import com.metlife.investments.cohesion.core.Message;

public class CohesionMessage implements Message
{
    private String msgBody;
    
    public CohesionMessage(String body)
    {
	this.msgBody = body;
    }

    public CohesionMessage()
    {
    }

    public void setText(String str)
    {
	this.msgBody = str;
    }

    public String getText()
    {
	return this.msgBody;
    }
}
