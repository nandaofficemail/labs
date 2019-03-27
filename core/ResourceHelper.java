package com.metlife.investments.cohesion.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceHelper
{
    public static String getResource(String rsc)
    {
	String val = "";

	try
	{
	    BufferedReader r = getResourceReader(rsc);
	    // reads each line
	    String l;
	    while ((l = r.readLine()) != null)
	    {
		val = val + l;
	    }
	}
	catch (Exception e)
	{
	    System.out.println(e);
	}
	return val;
    }

    public static BufferedReader getResourceReader(String rsc)
    {
	Class<?> cls;
	try
	{
	    cls = Class.forName("com.metlife.investments.cohesion.core.util.ResourceHelper");
	    // returns the ClassLoader object associated with this Class
	    ClassLoader cLoader = cls.getClassLoader();
	    // input stream
	    InputStream is = cLoader.getResourceAsStream(rsc);
	    if (is == null)
		return null;
	    BufferedReader r = new BufferedReader(new InputStreamReader(is));
	    return r;
	}
	catch (ClassNotFoundException e)
	{
	    e.printStackTrace();
	}
	return null;
    }

}
