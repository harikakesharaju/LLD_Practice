package com.practice.CacheDesign;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCacheStorage<K, V> implements CacheStorage<K, V> {
    private final int capacity;
    private final Map<K, V> cache = new HashMap<>();
    private final EvictionAlgorithm<K> evictionAlgorithm;

    public InMemoryCacheStorage(int capacity, EvictionAlgorithm<K> evictionAlgorithm) {
        this.capacity = capacity;
        this.evictionAlgorithm = evictionAlgorithm;
    }

    @Override
    public synchronized V get(K key) {
        V value = cache.get(key);
        if (value != null) {
            evictionAlgorithm.keyAccessed(key);
        }
        return value;
    }

    @Override
    public synchronized void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            evictionAlgorithm.keyAccessed(key);
            return;
        }

        if (cache.size() >= capacity) {
            K evictedKey = evictionAlgorithm.evictKey();
            if (evictedKey != null) {
                cache.remove(evictedKey);
            }
        }

        cache.put(key, value);
        evictionAlgorithm.keyAccessed(key);
    }

    @Override
    public synchronized void remove(K key) {
        cache.remove(key);
        evictionAlgorithm.remove(key);
    }

    @Override
    public synchronized boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public synchronized int size() {
        return cache.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
