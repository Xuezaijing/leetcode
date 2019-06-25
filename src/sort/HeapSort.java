package sort;

import java.util.Arrays;

public class HeapSort implements IArraySort<int[]>{

    @Override
    public int[] sort(int[] data) throws Exception {
        //对arr进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(data,data.length);

        return new int[0];
    }
}
