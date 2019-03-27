package com.metlife.investments.cohesion.providers.registry;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metlife.investments.cohesion.core.Format;
import com.metlife.investments.cohesion.core.registry.RegistryService;
import com.metlife.investments.cohesion.core.registry.dto.RegistryEntries;
import com.metlife.investments.cohesion.core.registry.dto.RegistryEntry;
import com.metlife.investments.cohesion.core.serializer.SerializerDeserializer;
import com.metlife.investments.cohesion.core.util.ResourceHelper;

public class FileRegistryService implements RegistryService
{
    private static final String REGISTRY_JSON = "registry.json";

    private static Logger logger = LoggerFactory.getLogger(FileRegistryService.class);

    private Map<Integer,RegistryEntry> entries;
    //    private Map<String, Set<Integer>> typeCache;
    //    private Map<String, Set<Integer>> pathCache;
    // 	  private Map<PropertyValue, Set<Integer>> propertiesCache;

    private String registryFile;
    public FileRegistryService()
    {
	entries = new HashMap<Integer,RegistryEntry>();
    }
    public void setRegistryFile(String rfile)
    {
	this.registryFile = rfile;
    }
    
    @Override
    public List<RegistryEntry> searchRegistry(String domain, String path, String expectedType)
    {
	// right now we search the registry linearly on each request
	// TODO: need to optimize by using the hash maps
	List<RegistryEntry> results = new ArrayList<RegistryEntry>();
	    
	for (RegistryEntry r : entries.values())
	{
	    if (r.getDomain().trim().equalsIgnoreCase(domain.trim()))
	    {
        	    if (r.getPath().trim().equalsIgnoreCase(path.trim()))
        	    {
        		if (r.getType().trim().equalsIgnoreCase(expectedType.trim()))
        		{
        		    results.add(r);
        		}
        	    }
        	}
	}
	return results;
    }

    @Override
    public void refresh()
    {
	Reader reader = ResourceHelper.getResourceReader(this.registryFile);
	if (reader != null)
	{
        	logger.debug("loading registry entries");
        
        	RegistryEntries re = SerializerDeserializer.deserialize(reader, RegistryEntries.class, Format.JSON);
        	for (RegistryEntry r : re.getEntries())
        	{
        	    entries.put(r.getId(), r);
        	}
	}
	else
	{
	    logger.error("registry file " + this.registryFile + " not found");
	}
    }

}
