package com.example.Task_7_Caching.configuration;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("student") {
            @Override
            public Cache getCache(String name) {
                Cache cache = super.getCache(name);
                if (cache != null) {
                    log.info("Cache with name '{}' found", name);
                }
                return cache;
            }

        };
    }
}
