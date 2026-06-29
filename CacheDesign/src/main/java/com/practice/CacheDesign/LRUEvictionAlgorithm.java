package com.practice.CacheDesign;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {
    private final DoublyLinkedList<K> dll = new DoublyLinkedList<>();
    private final Map<K, DoublyLinkedListNode<K>> nodes = new HashMap<>();

    @Override
    public synchronized void keyAccessed(K key) {
        DoublyLinkedListNode<K> node = nodes.get(key);
        if (node != null) {
            dll.moveToFirst(node);
            return;
        }
        node = new DoublyLinkedListNode<>(key);
        dll.addFirst(node);
        nodes.put(key, node);
    }

    @Override
    public synchronized K evictKey() {
        DoublyLinkedListNode<K> node = dll.removeLast();
        if (node == null) {
            return null;
        }
        nodes.remove(node.getKey());
        return node.getKey();
    }

    @Override
    public synchronized void remove(K key) {
        DoublyLinkedListNode<K> node = nodes.remove(key);
        if (node != null) {
            dll.remove(node);
        }
    }

    @Override
    public synchronized int size() {
        return nodes.size();
    }
}
