package sort;

import java.util.Arrays;

public class InsertSort implements IArraySort<int[]> {

    @Override
    public int[] sort(int[] data) throws Exception {
        int[] arr = Arrays.copyOf(data,data.length);

        for(int i = 1;i<arr.length;i++){
            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
            print(arr);
            System.out.println();
        }
        return arr;

    }
}
