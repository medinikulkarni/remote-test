package com.john.lewis.partnership.service.caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CachingConfig {

    @Bean
    public Map<String, String> rgbColors() {
        Map<String, String> basicColorHexMap = new HashMap<>();
        basicColorHexMap.put("red", "FF0000");
        basicColorHexMap.put("yellow", "FFFF00");
        basicColorHexMap.put("blue", "0000FF");
        basicColorHexMap.put("orange", "FFA500");
        basicColorHexMap.put("green", "008000");
        basicColorHexMap.put("violet", "EE82EE");
        basicColorHexMap.put("white", "FFFFFF");
        basicColorHexMap.put("fuchsia", "FF00FF");
        basicColorHexMap.put("silver", "C0C0C0");
        basicColorHexMap.put("gray", "808080");
        basicColorHexMap.put("olive", "808000");
        basicColorHexMap.put("purple", "800080");
        basicColorHexMap.put("maroon", "800000");
        basicColorHexMap.put("aqua", "00FFFF");
        basicColorHexMap.put("lime", "00FF00");
        basicColorHexMap.put("teal", "008080");
        basicColorHexMap.put("navy", "000080");
        basicColorHexMap.put("black", "000000");
        basicColorHexMap.put("pink", "FFC0CB");
        return basicColorHexMap;
    }

    @Bean
    public Map<String, String> currencySymbol() {
        Map<String, String> currencySymbolMap = new HashMap<>();
        currencySymbolMap.put("GBP", "£");
        return currencySymbolMap;
    }
}
