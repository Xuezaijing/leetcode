package sort;

import com.sun.scenario.effect.Merge;

public class SortTest {
    public static void main(String[] args) throws Exception {
        int[] sourceArray = {1,3,2,4,9,6,8,7,0};
        //BubbleSort sort = new BubbleSort();
        //SelectionSort sort = new SelectionSort();
        //InsertSort sort = new InsertSort();
        //ShellSort sort = new ShellSort();
        //MergeSort sort = new MergeSort();
        QuickSort sort = new QuickSort();
        int[] res = sort.sort(sourceArray);
        sort.print(res);

    }
}
