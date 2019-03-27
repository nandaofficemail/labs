package com.metlife.investments.cohesion.core.resource;


public class ClassTarget<T> extends ResourceTarget
{
    public ClassTarget(Class<T> cls)
    {
	super("cohesion://class/" + cls.getSimpleName());
    }
}
