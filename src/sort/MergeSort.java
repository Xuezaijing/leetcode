package sort;

import java.util.Arrays;

public class MergeSort implements IArraySort<int[]>{

    @Override
    public int[] sort(int[] data) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(data,data.length);

        if(arr.length<2){
            return arr;
        }
        int middle = (int)Math.floor(arr.length/2);

        int[] left = Arrays.copyOfRange(arr,0,middle);
        int[] right = Arrays.copyOfRange(arr,middle,arr.length);
        return merge(sort(left),sort(right));
    }

    protected int[] merge(int[] left,int[] right){
        int[] result = new int[left.length+right.length];
        int i = 0,j = 0,k = 0;
        while(i<left.length&&j<right.length){
            if(left[i]<right[j]){
                result[k++] = left[i++];
            }else{
                result[k++] = right[j++];
            }
        }
        while(i<left.length){
            result[k++] = left[i++];
        }
        while(j<right.length){
            result[k++] = right[j++];
        }
        print(result);
        System.out.println();
        return result;
    }
}
