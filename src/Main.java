import java.util.*;

public class Main {
    static int count = 0;
    public static void main(String[] args) {
        /*//Scanner in = new Scanner(System.in);
        int n = 5;
        int d = 19;
        int[] locs = {1,10,20,30,50};


        for(int i=0;i<locs.length-2;i++){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(locs[i]);
            dfs(locs,i,i,i+1,1,d,list);
        }
        System.out.println(count);*/
        Main main = new Main();
        main.removeDuplicates(new int[]{1,1,2});
    }
    private static void dfs(int[] locs,int left, int last, int start, int num, int d, ArrayList<Integer> list){

        if(num==3){
            System.out.println(list);
            count++;
            return;
        }
        for(int i = start;i<locs.length;i++){
            if(locs[i]-locs[left]<=d){
                list.add(locs[i]);
                dfs(locs,left,i,i+1,num+1,d,list);
            }else{
                break;
            }
        }
    }

    /**
     * 349 两个数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        String s = "";
        char[] arr = s.toCharArray();
        return new int[]{};
    }
    public int[] twoSum(int[] numbers, int target) {
        int i = 0,j = numbers.length-1;
        while(i<j){
            int tmp = numbers[i]+numbers[j];
            if(tmp==target){
                return new int[]{i,j};
            }else if(tmp<target){
                i++;
            }else{
                j++;
            }
        }
        return new int[]{};
    }

    public boolean isPlaindrome(String s){
        String actual = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        System.out.println(actual);
        String rev = new StringBuffer(actual).reverse().toString();
        System.out.println(rev);
        return true;
    }

    public int findPairs(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int count = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(k==0){
                if(entry.getValue()>=2){
                    count++;
                }
            }else{
                if(map.containsKey(entry.getKey()+k)){
                    count++;
                }
            }
        }
        return count;
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int maxDepthdfs(Node root) {
        if(root == null) return 0;
        int max = 0;
        for(Node node : root.children){
            max = Math.max(max,maxDepthdfs(node));
        }
        return max+1;
    }

    public int maxDepthbfs(Node root){
        if(root == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while(!queue.isEmpty()){
            int num = queue.size();
            for(int i = 0;i<num;i++){
                Node cur = queue.poll();
                for(Node node : cur.children){
                    queue.offer(node);
                }
            }
            depth++;
        }
        return depth;
    }


    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i<size;i++){
                Node cur = queue.poll();
                list.add(cur.val);
                for(Node node : cur.children){
                    queue.offer(node);
                }
            }
            res.add(list);
        }
        return res;
    }
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i<size;i++){
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            res.addFirst(list);
        }
        return res;
    }

    HashMap<Integer,Integer> depth;
    HashMap<Integer,TreeNode> parent;

    public boolean isCousins(TreeNode root, int x, int y) {
        depth = new HashMap<>();
        parent = new HashMap<>();
        dfs(root,null);
        return depth.get(x)==depth.get(y) && parent.get(x)!=parent.get(y);
    }
    private void dfs(TreeNode node,TreeNode par){
        if(node != null){
            depth.put(node.val,par == null?0:1+depth.get(par.val));
            parent.put(node.val,par);
            dfs(node.left,node);
            dfs(node.right,node);
        }
    }


    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    public int orangesRotting(int[][] grid) {
        int R = grid.length, C = grid[0].length;

        // queue : all starting cells with rotten oranges
        Queue<Integer> queue = new ArrayDeque();
        Map<Integer, Integer> depth = new HashMap();
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c)
                if (grid[r][c] == 2) {
                    int code = r * C + c;
                    queue.add(code);
                    depth.put(code, 0);
                }

        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / C, c = code % C;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }

        for (int[] row: grid)
            for (int v: row)
                if (v == 1)
                    return -1;
        return ans;

    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean turn = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for(int i = 0;i<size;i++){
                TreeNode node = queue.poll();
                if(turn){
                    list.addLast(node.val);
                }else{
                    list.addFirst(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(list);
            turn = !turn;
        }
        return res;
    }

    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        int depth = -1;
        queue.addAll(Arrays.asList("0000"));
        while (!queue.isEmpty()){
            depth++;
            int size = queue.size();
            for(int i = 0;i<size;i++){
                String node = queue.poll();
                if(node.equals(target)){
                    return depth;
                }
                if(visited.contains(node)){
                    continue;
                }
                visited.add(node);
                queue.addAll(getSuccessors(node));
            }
        }
        return -1;
    }
    private static List<String> getSuccessors(String str) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            res.add(str.substring(0, i) + (str.charAt(i) == '0' ? 9 :  str.charAt(i) - '0' - 1) + str.substring(i+1));
            res.add(str.substring(0, i) + (str.charAt(i) == '9' ? 0 :  str.charAt(i) - '0' + 1) + str.substring(i+1));
        }
        return res;
    }


    private int n;
    private int m;

    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }

    Map<Integer,Integer> dist;
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer,List<int[]>> graph = new HashMap<>();
        for(int[] edge : times){
            if(!graph.containsKey(edge[0])){
                graph.put(edge[0],new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[2],edge[1]});
        }
        for(int node : graph.keySet()){
            Collections.sort(graph.get(node),(a,b)->a[0]-b[0]);
        }
        dist = new HashMap<>();
        for(int i = 1;i<=N;i++){
            dist.put(i,Integer.MAX_VALUE);
        }
        dfs(graph,K,0);
        int ans = 0;
        for (int cand : dist.values()){
            if(cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans,cand);
        }
        return ans;
    }
    private void dfs(Map<Integer,List<int[]>> graph,int node,int elapsed){
        if(elapsed>=dist.get(node)) return;
        dist.put(node,elapsed);
        if(graph.containsKey(node)){
            for(int[] info : graph.get(node)){
                dfs(graph,info[1],elapsed+info[0]);
            }
        }
    }
    class Node2{
        int r,c,depth;
        public Node2(int r,int c,int d){
            this.r = r;
            this.c = c;
            this.depth = d;
        }
    }

    public int[][] getComponents(int[][] A){
        int R = A.length,C = A[0].length;
        int[][] colors = new int[R][C];
        int t = 0;

        for(int r0 = 0;r0<R;r0++){
            for(int c0 = 0;c0<C;c0++){
                if(colors[r0][c0]==0 && A[r0][c0] == 1){
                    Stack<Integer> stack = new Stack<>();
                    stack.push(r0*C+c0);
                    colors[r0][c0] = ++t;

                    while (!stack.isEmpty()){
                        int node = stack.pop();
                        int r = node/C,c = node%C;
                        for(int nei : neighbors(A,r,c)){
                            int nr = nei/C,nc = nei%C;
                            if(A[nr][nc] == 1 && colors[nr][nc] == 0){
                                colors[nr][nc] = t;
                                stack.push(nei);
                            }
                        }
                    }
                }
            }
        }
        return colors;
    }

    public List<Integer> neighbors(int[][] A,int r,int c){
        int R = A.length,C = A[0].length;
        List<Integer> list = new ArrayList<>();
        if(r-1>=0) list.add((r-1)*C+c);
        if(c-1>=0) list.add(r*C+c-1);
        if(r+1<R) list.add((r+1)*C+c);
        if(c+1<C) list.add(r*C+c+1);
        return list;
    }
    public int shortestBridge(int[][] A) {
        int R = A.length,C = A[0].length;
        int[][] colors = getComponents(A);

        Queue<Node2> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        for(int r = 0;r<R;r++){
            for(int c = 0;c<C;C++){
                if(colors[r][c]==1){
                    queue.add(new Node2(r,c,0));
                }else if(colors[r][c]==2){
                    set.add(r*C+c);
                }
            }
        }

        while (!queue.isEmpty()){
            Node2 node2 = queue.poll();
            if(set.contains(node2.r * C +node2.c))
                return node2.depth - 1;
            for(int nei : neighbors(A,node2.r,node2.c)){
                int nr = nei/C,nc = nei%C;
                if(colors[nr][nc]!=1){
                    queue.add(new Node2(nr,nc,node2.depth+1));
                    colors[nr][nc] = 1;
                }
            }
        }
        return -1;
    }
    private List<Integer> res;
    public void getKFromRoot(TreeNode root,int k){
        if(root == null){
            return;
        }
        if( k == 0){
            res.add(root.val);
            return;
        }
        getKFromRoot(root.left,k-1);
        getKFromRoot(root.right,k-1);
    }

    /**
     * 三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res= new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        for(int i = 0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int j = i+1,k = nums.length-1;
            while (j<k){
                if(nums[j]+nums[k]+nums[i]==0){
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    while(j<k&&nums[j]==nums[j+1]) j++;
                    while(j<k&&nums[k]==nums[k-1]) k--;
                    j++;
                    k--;
                }else if(nums[j]+nums[k]+nums[i]<0){
                    j++;
                }else{
                    k--;
                }
            }
        }
        return res;
    }

    /**
     * 删除排序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length==0){
            return 0;
        }
        int j = 0;
        int cur = nums[0];
        for(int i = 0;i<nums.length;i++){
            if(nums[i]==cur){

            }else{
                cur = nums[i];
                int tmp = nums[i];
                nums[i] = nums[++j];
                nums[j] = tmp;
            }
        }
        for(int i = 0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println(j+1);
        return j+1;
    }

    /**
     * 搜索旋转排序数组
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]) {
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

    /**
     * 组合总和
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(res,new ArrayList<>(),candidates,target,0);
        return res;
    }

    private void backtracking(List<List<Integer>> res, ArrayList<Integer> temp, int[] candidates, int remain, int start) {
        if(remain < 0 ){
            return;
        }else if(remain==0){
            res.add(new ArrayList<>(temp));
        }else{
            for(int i = start;i<candidates.length;i++){
                temp.add(candidates[i]);
                backtracking(res,temp,candidates,remain-candidates[i],i);
                temp.remove(temp.size()-1);
            }
        }
    }

    /**
     * 组合总和II
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        backtracking(candidates,0,target,new ArrayList<Integer>(),res);
        return res;
    }
    private void backtracking(int[] cand,int start,int remain,List<Integer>                             temp,List<List<Integer>> res){
        if(remain<0) return;
        else if(remain==0) res.add(new ArrayList<Integer>(temp));
        for(int i = start;i<cand.length;i++){
            if(i>start && cand[i]==cand[i-1]) continue;
            temp.add(cand[i]);
            backtracking(cand,i+1,remain-cand[i],temp,res);
            temp.remove(temp.size()-1);
        }
    }
}