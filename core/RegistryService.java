package com.metlife.investments.cohesion.core.registry;

import java.util.List;

import com.metlife.investments.cohesion.core.registry.dto.RegistryEntry;
import com.metlife.investments.cohesion.core.resource.ResourceTarget;

public interface RegistryService
{
    List<RegistryEntry> searchRegistry(String domain, String path, String expectedType);
    void refresh();
}
