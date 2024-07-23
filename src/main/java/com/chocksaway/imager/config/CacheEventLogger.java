package com.chocksaway.imager.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?,?> cacheEvent) {
        System.out.println("Cache event: " + cacheEvent.getType() + " key: " + cacheEvent.getKey() + " old value: " + cacheEvent.getOldValue() + " new value: " + cacheEvent.getNewValue());
    }
}