package lru;

public class Node<V> {
    public V value;
    public Node<V> last;
    public Node<V> next;

    public Node(V value){
        this.value = value;
    }
}
