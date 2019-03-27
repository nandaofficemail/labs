package com.metlife.investments.cohesion.core.resource;

public class JmsResourceTarget extends ResourceTarget
{
    public JmsResourceTarget(String queueOrTopicName)
    {
	super("jms://" + queueOrTopicName);
    }

}
