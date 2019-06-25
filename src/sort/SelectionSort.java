package sort;

import java.util.Arrays;

public class SelectionSort implements IArraySort<int[]>{

    @Override
    public int[] sort(int[] data) throws Exception {
        int[] arr = Arrays.copyOf(data,data.length);

        //总共需要进行N-1轮
        for(int i = 0;i<arr.length-1;i++){
            int min = i;

            for(int j = i+1;j<arr.length;j++){
                if(arr[min]>arr[j]){
                    min = j;
                }
            }
            int tmp = arr[min];
            arr[min] = arr[i];
            arr[i] = tmp;

        }
        return arr;
    }
}
