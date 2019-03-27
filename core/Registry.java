package com.metlife.investments.cohesion.core.registry;

import java.util.List;

import com.metlife.investments.cohesion.core.resource.ResourceTarget;

public interface Registry
{
    List<ReqRespEntity> getReqRespEntity(ResourceTarget pat);
    PublisherRegistryDTO getPubSubEntity(ResourceTarget pat);
    void refresh();
}

