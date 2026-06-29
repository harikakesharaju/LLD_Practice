package com.practice.CacheDesign;

public class WriteThroughPolicy<K, V> implements WritePolicy<K, V> {
    @Override
    public void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception {
        if (cacheStorage == null || dbStorage == null) {
            throw new IllegalArgumentException("Cache and DB storage must not be null");
        }
        cacheStorage.put(key, value);
        dbStorage.write(key, value);
    }
}
