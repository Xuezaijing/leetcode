import java.util.ArrayList;
import java.util.List;

public class quanpailie {

    public static void backtracking(int[] arr, List<String> res,StringBuilder sb,int start){
        if(sb.length()==3){
            res.add(sb.toString());
            return;
        }
        for(int i = start;i<arr.length;i++){
            sb.append(arr[i]);
            swap(arr,i,start);
            backtracking(arr,res,sb,start+1);
            swap(arr,i,start);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        List<String> res= new ArrayList<>();
        backtracking(arr,res,new StringBuilder(),0);
        for(String str:res){
            System.out.println(str);
        }
        System.out.println(res.size());
    }
}
