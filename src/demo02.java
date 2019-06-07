import sun.text.normalizer.NormalizerBase;

import java.util.*;

public class demo02 {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        HashMap<Integer,Node> map = new HashMap<>();
        Node newNode = new Node(node.val,new ArrayList<>());
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node.val,newNode);

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(Node tmp : cur.neighbors){
                if(!map.containsKey(tmp.val)){
                    map.put(tmp.val,new Node(tmp.val,new ArrayList<>()));
                    queue.offer(tmp);
                }
                map.get(cur.val).neighbors.add(map.get(tmp.val));
            }
        }
        return newNode;
    }

    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++){
            dp[i] = dp[i-1]<=0?nums[i]:dp[i-1]+nums[i];
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        int res = Math.min(dp[0],dp[1]);
        for(int i = 0;i<cost.length;i++){
            dp[i] = Math.min(dp[i-1],dp[i-2])+cost[i];
        }
        return dp[cost.length-1];
    }

    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if(nums.length == 1){
            return dp[0];
        }
        dp[1] = Math.max(nums[0],nums[1]);
        if(nums.length == 2){
            return Math.max(dp[0],dp[1]);
        }
        dp[2] = Math.max(dp[1],dp[0]+nums[2]);
        int res = Math.max(Math.max(dp[1],dp[2]),dp[0]);
        for(int i = 3;i<nums.length;i++){
            dp[i] = Math.max(dp[i-2],dp[i-3])+nums[i];
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
