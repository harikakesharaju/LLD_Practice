package com.practice.CacheDesign;

import java.util.HashMap;
import java.util.Map;

public class SimpleDBStorage<K, V> implements DBStorage<K, V> {
    private final Map<K, V> store = new HashMap<>();

    @Override
    public synchronized V get(K key) {
        return store.get(key);
    }

    @Override
    public synchronized void write(K key, V value) {
        store.put(key, value);
    }

    @Override
    public synchronized void delete(K key) {
        store.remove(key);
    }
}
