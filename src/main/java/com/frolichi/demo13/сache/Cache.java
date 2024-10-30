package com.frolichi.demo13.сache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {

    private final Map<Integer, Object> cache = new ConcurrentHashMap<>();
    private final int maxCacheSize = 100;


    public void put(int key, Object value) {
        if (cache.size() > maxCacheSize) cache.clear();
        cache.put(key, value);
    }

    public Object get(int key) {
        return cache.get(key);

    }

    public void clearForKey(int key) {
        cache.remove(key);
    }


    public void clearAll() {
        cache.clear();
    }
}




