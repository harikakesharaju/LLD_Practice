package com.practice.CacheDesign;

public interface CacheStorage<K, V> {
    V get(K key);
    void put(K key, V value);
    void remove(K key);
    boolean containsKey(K key);
    int size();
    int getCapacity();
}
