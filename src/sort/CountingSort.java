package sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * 说明：
     *
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     *
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     *
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     *
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s,new HashSet<>(wordDict),0,new Boolean[s.length()]);
    }

    private boolean word_Break(String s, HashSet<String> wordDict, int start, Boolean[] memo) {
        if(start==s.length()){
            return true;
        }
        if(memo[start] != null){
            return memo[start];
        }
        for(int end = start+1;end<=s.length();end++){
            if(wordDict.contains(s.substring(start,end)) && word_Break(s,wordDict,end,memo)){
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    public boolean wordBreakDP(String s,List<String> wordDict){
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1;i<dp.length;i++){
            for(int j = 0;j<i;j++){
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
