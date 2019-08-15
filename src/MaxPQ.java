import java.util.Comparator;

public class MaxPQ<Key extends Comparable<Key>>  {
    //存储元素的数组
    private Key[] pq;
    //当前Priority Queue中的元素个数
    private int N = 0;

    public MaxPQ(int cap){
        //索引0不用，所以多分配一个空间
        pq = (Key[])new Comparator[cap+1];
    }

    /**
     * 返回当前队列中最大元素
     */
    public Key max(){
        return pq[1];
    }

    /**
     * 插入元素
     */
    public void insert(Key e){
        N++;
        pq[N] = e;
        swim(N);
    }

    /**
     * 删除并返回当前队列中最大元素
     */
    public Key delMax(){
        Key max = pq[1];
        exch(1,N);
        pq[N] = null;
        N--;
        sink(1);
        return max;
    }

    /**
     * 上浮第k个元素，以维护最大堆性质
     */
    private void swim(int k){
        //如果浮到堆顶，不能再上浮
        while(k>1 && less(parent(k),k)){
            //如果第k个元素比上层大，交换
            exch(parent(k),k);
            k = parent(k);
        }
    }
    /**
     * 下沉第k个元素，以维护最大堆性质
     */
    private void sink(int k){
        //如果沉到堆底，就沉不下去了
        while(left(k)<=N){
            //先假设左边节点较大
            int older = left(k);
            //如果右边节点存在，比一下大小
            if(right(k)<=N && less(older,right(k))){
                older = right(k);
            }
            //节点K比两个孩子都大，就不必下沉了
            if(less(older,k)) break;
            //否则不符合最大堆的结构，下沉k节点
            exch(k,older);
            k = older;
        }
    }
    /**
     * 交换数组的两个元素
     */
    private void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    /**
     * pq[i]是否比pq[j]小？
     */
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j]) < 0;
    }
    // 父节点的索引
    int parent(int root) {
        return root / 2;
    }
    // 左孩子的索引
    int left(int root) {
        return root * 2;
    }
    // 右孩子的索引
    int right(int root) {
        return root * 2 + 1;
    }
}
