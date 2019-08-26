package lru;


import java.util.HashMap;

public class LRUCache<K,V> {
    private HashMap<K,Node<V>> keyNodeMap;
    private HashMap<Node<V>,K> nodeKeyMap;
    private NodeDoubleLinkedList<V> nodeList;
    private int capacity;

    public LRUCache(int capacity){
        if(capacity<1){
            throw new RuntimeException("should ");
        }
    }
}
