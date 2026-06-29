package com.practice.CacheDesign;

public interface EvictionAlgorithm<K> {
    void keyAccessed(K key);
    K evictKey();
    void remove(K key);
    int size();
}
