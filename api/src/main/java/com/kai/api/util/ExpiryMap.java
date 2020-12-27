package com.kai.api.util;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExpiryMap<K, V> {
    /**
     * default expiry time 5分钟
     */
    private long EXPIRY = 5*1000*60;

    private Map<K, ExpiryEntry> expiryMap = new ConcurrentHashMap();

    public V put(K key, V value) {
        final ExpiryEntry<V> put = expiryMap.put(key, new ExpiryEntry().setValue(value).setExpireTime(System.currentTimeMillis() + EXPIRY));
        return put == null ? null : put.getValue();
    }

    public V get(Object key) {
        final ExpiryEntry<V> expiryEntry = expiryMap.get(key);
        if (expiryEntry != null) {
            final long expireTime = expiryEntry.getExpireTime();
            if (expireTime > System.currentTimeMillis()) {
                return expiryEntry.getValue();
            }
            expiryMap.remove(key);
            return null;
        }
        return null;
    }


    @Data
    @Accessors(chain = true)
    class ExpiryEntry<V> {
        private long expireTime;

        private V value;
    }

}
