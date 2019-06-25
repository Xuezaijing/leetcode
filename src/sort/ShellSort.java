package sort;

import java.util.Arrays;

public class ShellSort implements IArraySort<int[]>{

    @Override
    public int[] sort(int[] data) throws Exception {
        //进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(data,data.length);

        int gap = 1;
        while(gap<arr.length){
            gap = gap * 3+1;
        }

        while(gap>0){
            //分段进行插入排序
            for(int i = gap;i<arr.length;i++){
                // 记录要插入的数据
                int tmp = arr[i];

                // 从已经排序的序列最右边的开始比较，找到比其小的数
                int j = i-gap;
                while (j >= 0 && tmp < arr[j]) {
                    arr[j+gap] = arr[j];
                    j-=gap;
                }


                arr[j+gap] = tmp;

            }
            print(arr);
            System.out.println();
            gap = (int)Math.floor(gap/3);
        }
        return arr;
    }
}
