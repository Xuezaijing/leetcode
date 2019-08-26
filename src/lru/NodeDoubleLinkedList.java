package lru;

/**
 * 双向链表结构：优先级最低的节点是head，优先级最高的节点是tail。
 * @param <V>
 */
public class NodeDoubleLinkedList<V> {
    private Node<V> head;
    private Node<V> tail;

    public NodeDoubleLinkedList(){
        this.head = null;
        this.tail = null;
    }

    /**
     * 加入一个节点时，
     * 将新加入的节点放在链表的尾部，并将这个节点设置为新的尾节点
     * @param newNode
     */
    public void addNode(Node<V> newNode){
        if(newNode == null){
            return;
        }
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }else{
            this.tail.next = newNode;
            newNode.last = this.tail;
            this.tail = newNode;
        }
    }

    /**
     * 对与双向链表的每个节点，都可以分离出来并放到整个链表的尾部
     * @param node
     */
    public void moveToTati(Node<V> node){
        if(this.tail == node){
            return;
        }
        if(this.head == node){
            this.head = node.next;
            this.head.last = null;
        }else{
            node.last.next = node.next;
            node.next.last = node.last;
        }
        node.last = this.tail;
        node.next = null;
        this.tail.next = node;
        this.tail = node;
    }

    /**
     * 移除head节点并返回这个节点，然后将head设置成老head节点的下一个
     * @return
     */
    public Node<V> removeHead(){
        if(this.head == null){
            return null;
        }
        Node<V> res = this.head;
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        }else{
            this.head = res.next;
            res.next = null;
            this.head.last = null;
        }
        return res;
    }
}
