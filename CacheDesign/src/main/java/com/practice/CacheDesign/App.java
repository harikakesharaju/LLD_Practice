package com.practice.CacheDesign;

public class App {
    public static void main(String[] args) throws Exception {
        EvictionAlgorithm<Integer> lru = new LRUEvictionAlgorithm<>();
        CacheStorage<Integer, String> cacheStorage = new InMemoryCacheStorage<>(2, lru);
        DBStorage<Integer, String> dbStorage = new SimpleDBStorage<>();
        WritePolicy<Integer, String> writePolicy = new WriteThroughPolicy<>();

        Cache<Integer, String> cache = new Cache<>(cacheStorage, dbStorage, writePolicy, lru);

        cache.updateData(1, "one");
        cache.updateData(2, "two");
        System.out.println("Access 1: " + cache.accessData(1));
        cache.updateData(3, "three");
        System.out.println("Contains 2 after LRU eviction: " + cache.containsKey(2));
        System.out.println("Access 3: " + cache.accessData(3));
        System.out.println("DB contains 3: " + dbStorage.get(3));
    }
}
