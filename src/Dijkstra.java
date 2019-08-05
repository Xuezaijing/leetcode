public class Dijkstra {
    public static final int M = 10000;//代表正无穷

    public static void main(String[] args) {
        //二维数组每一行分别是A,B,C,D,E到其余点的距离
        // A -> A 距离为0,常量M为正无穷
        int[][] weights = {
                {0,4,M,2,M},
                {4,0,4,1,M},
                {M,4,0,1,3},
                {2,1,1,0,7},
                {M,M,3,7,0}
        };
        int start = 0;
        int[] shortPath = dijkstra(weights,start);
        for (int i = 0; i < shortPath.length; i++)
            System.out.println("从" + start + "出发到" + i + "的最短距离为：" + shortPath[i]);
    }

    private static int[] dijkstra(int[][] weights, int start) {
        //接受一个有向图的权重矩阵weights,和一个起点编号start
        //返回一个int[]数组,表示从start到它的最短路径长度
        int n = weights.length;
        int[] shortPath = new int[n];//保存start到其他各点的最短路径
        String[] paths = new String[n];//保存start到其他各点最短路径的字符串表示
        for(int i = 0;i<n;i++){
            paths[i] = new String(start+"-->"+i);
        }
        int[] visited = new int[n];//标记当前该顶点的最短路径是否已经求出,1表示已经求出
        //初始化
        shortPath[start] = 0;
        visited[start] = 1;

        for(int count = 1;count<n;count++){
            int k = -1;
            int dmin = Integer.MAX_VALUE;
            for(int i = 0;i<n;i++){
                if(visited[i]==0 && weights[start][i] <dmin){
                    dmin = weights[start][i];
                    k = i;
                }
            }
            //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;
            visited[k] = 1;

            //以k为中间点,修正从start到未访问各点的距离
            for(int i = 0;i < n;i++){
                //如果'起始点到当前点距离' + '当前点到某点距离' < '起始点到某点距离', 则更新
                if(visited[i] == 0 && weights[start][k] + weights[k][i]<weights[start][i]){
                    weights[start][i] = weights[start][k] + weights[k][i];
                    paths[i] = paths[k] + "-->" + i;
                }
            }
        }
        for (int i = 0; i < n; i++) {

            System.out.println("从" + start + "出发到" + i + "的最短路径为：" + paths[i]);
        }
        System.out.println("=====================================");
        return shortPath;
    }

}
