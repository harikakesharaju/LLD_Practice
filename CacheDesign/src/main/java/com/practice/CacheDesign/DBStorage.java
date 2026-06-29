package com.practice.CacheDesign;

public interface DBStorage<K, V> {
    V get(K key);
    void write(K key, V value);
    void delete(K key);
}
