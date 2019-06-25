package sort;

import java.util.Arrays;

public class CountingSort implements IArraySort<int[]>{

    @Override
    public int[] sort(int[] data) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(data, data.length);
        //获取数组中最大的值
        int[] maxminValue = getMaxMinValue(arr);
        //开辟新空间，存储min到max间所有数出现的频率
        //遍历数组，即可完成排序
        //可能会造成空间的浪费
        return new int[0];
    }

    private int[] getMaxMinValue(int[] arr) {
        int maxvalue = arr[0];
        int minvalue = arr[0];
        for(int i = 1;i<arr.length;i++){
            if(arr[i]>maxvalue){
                maxvalue = arr[i];
            }
            if(arr[i]<minvalue){
                minvalue = arr[i];
            }
        }
        return new int[]{minvalue,maxvalue};
    }
}
