package com.practice.CacheDesign;

public class DoublyLinkedListNode<K> {
    K key;
    DoublyLinkedListNode<K> prev;
    DoublyLinkedListNode<K> next;

    public DoublyLinkedListNode(K key) {
        this.key = key;
    }

    public K getKey() { return key; }
}
