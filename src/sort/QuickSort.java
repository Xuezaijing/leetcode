package sort;

import java.util.Arrays;

public class QuickSort implements IArraySort<int[]>{

    @Override
    public int[] sort(int[] data) throws Exception {
        int[] arr = Arrays.copyOf(data,data.length);
        quickSort(arr,0,arr.length-1);
        return arr;
    }
    private void quickSort(int[] arr,int left,int right){
        if(left<right){
            int partitionIntdex = partition(arr,left,right);
            quickSort(arr,left,partitionIntdex-1);
            quickSort(arr,partitionIntdex+1,right);
        }
    }
    private int partition(int[] arr,int left,int right){
        //设定基准值
        int random = left+(int)Math.random()*(right-left);
        swap(arr,random,left);
        int pivot = left;
        int index = left+1;
        for(int i = index;i<=right;i++){
            if(arr[i]<arr[pivot]){
                swap(arr,i,index);
                index++;
            }
        }
        swap(arr,pivot,index-1);
        return index-1;
    }
    private void swap(int[] arr,int x,int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void main(String[] args) {
        int left = 1,right = 10;
        int random = left+(int)(Math.random()*(right-left));
        System.out.println(random);
    }
}
