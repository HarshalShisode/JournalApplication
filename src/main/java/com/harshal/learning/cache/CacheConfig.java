package com.harshal.learning.cache;

import com.harshal.learning.entity.CacheConfigEntity;
import com.harshal.learning.repository.CacheConfigDetails;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CacheConfig {

    public enum keys{
        api
    }

    @Autowired
    private CacheConfigDetails cacheConfigDetails;

    public Map<String, String> cacheMap;

    @PostConstruct
    public void init(){
        cacheMap=new HashMap<>();
        List<CacheConfigEntity> allConfigs = cacheConfigDetails.findAll();
        for(CacheConfigEntity cacheConfigEntity:allConfigs) {
            cacheMap.put(cacheConfigEntity.getKey(), cacheConfigEntity.getValue());
        }
    }
}
