package com.metlife.investments.cohesion.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PropVals 
{
    private Map<String,String> propValMap;
    
    public PropVals()
    {
	this.propValMap = new HashMap<String,String>();
    }
    
    public PropVals add(String prop, String val)
    {
	this.propValMap.put(prop,  val);
	return this;
    }

    public void clear()
    {
	propValMap.clear();
    }

    public boolean containsKey(String key)
    {
	return propValMap.containsKey(key);
    }

    public boolean containsValue(String value)
    {
	return propValMap.containsValue(value);
    }

    public Set<java.util.Map.Entry<String, String>> entrySet()
    {
	return propValMap.entrySet();
    }

    public String get(String key)
    {
	return propValMap.get(key);
    }

    public boolean isEmpty()
    {
	return propValMap.isEmpty();
    }

    public Set<String> keySet()
    {
	return propValMap.keySet();
    }

    public String put(String key, String value)
    {
	return propValMap.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends String> m)
    {
	propValMap.putAll(m);
    }

    public String remove(String key)
    {
	return propValMap.remove(key);
    }

    public int size()
    {
	return propValMap.size();
    }

    public Collection<String> values()
    {
	return propValMap.values();
    }
}

