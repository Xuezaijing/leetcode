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

    /**
     * 课程表
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     *
     * 示例 1:
     *
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     *
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     * 说明:
     *
     * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 提示:
     *
     * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
     * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
     * 拓扑排序也可以通过 BFS 完成。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for(int i = 0;i<prerequisites.length;i++){
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if(matrix[pre][ready]==0)
                indegree[ready]++;
            matrix[pre][ready] = 1;

        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i<numCourses;i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int pre = queue.poll();
            count++;
            for(int i = 0;i<numCourses;i++){
                if(matrix[pre][i]==1){
                    if(--indegree[i]==0)
                        queue.offer(i);
                }
            }
        }
        return count==numCourses;
    }
}
