package com.practice.CacheDesign;

public class DoublyLinkedList<K> {
    private DoublyLinkedListNode<K> head;
    private DoublyLinkedListNode<K> tail;
    private int size = 0;

    public void addFirst(DoublyLinkedListNode<K> node) {
        if (node == null) return;
        node.prev = null;
        node.next = head;
        if (head != null) head.prev = node;
        head = node;
        if (tail == null) tail = node;
        size++;
    }

    public void moveToFirst(DoublyLinkedListNode<K> node) {
        if (node == null || head == node) return;
        remove(node);
        addFirst(node);
    }

    public void remove(DoublyLinkedListNode<K> node) {
        if (node == null) return;
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;
        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
        node.prev = null;
        node.next = null;
        size--;
    }

    public DoublyLinkedListNode<K> removeLast() {
        if (tail == null) return null;
        DoublyLinkedListNode<K> node = tail;
        remove(node);
        return node;
    }

    public DoublyLinkedListNode<K> getTail() { return tail; }

    public int size() { return size; }
}
