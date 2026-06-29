package com.practice.CacheDesign;

public class Cache<K, V> {
    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K, V> dbStorage;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionAlgorithm<K> evictionAlgorithm;

    public Cache(CacheStorage<K, V> cacheStorage,
                 DBStorage<K, V> dbStorage,
                 WritePolicy<K, V> writePolicy,
                 EvictionAlgorithm<K> evictionAlgorithm) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionAlgorithm = evictionAlgorithm;
    }

    public synchronized V accessData(K key) throws Exception {
        if (!cacheStorage.containsKey(key)) {
            throw new Exception("Key not found in cache: " + key);
        }
        evictionAlgorithm.keyAccessed(key);
        return cacheStorage.get(key);
    }

    public synchronized void updateData(K key, V value) throws Exception {
        writePolicy.write(key, value, cacheStorage, dbStorage);
        evictionAlgorithm.keyAccessed(key);
    }

    public synchronized boolean containsKey(K key) {
        return cacheStorage.containsKey(key);
    }

    public synchronized int size() {
        return cacheStorage.size();
    }

    public synchronized void remove(K key) {
        cacheStorage.remove(key);
        evictionAlgorithm.remove(key);
    }

    public void shutdown() {
        // No-op for this simple in-memory implementation.
    }
}
