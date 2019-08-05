public class DJbishi {
    public static void main(String[] args) {
        drinkCoffee();
    }

    /**
     * 喝咖啡
     */
    public static void drinkCoffee(){
        int N = 4;
        int A = 3;
        int X = 3;
        int max_time = X *60*A;
        int[] bugs = new int[]{333,77,100,13};
        int totalOriginalTime = 0;
        int workLimit = 640;
        for(int i = 0;i<bugs.length;i++){
            totalOriginalTime += bugs[i];
        }
        if(max_time >= totalOriginalTime){
            int res = (int)Math.ceil(1.0*totalOriginalTime/A);
            System.out.println(res);

        }else{
            int remainTime = totalOriginalTime - max_time;
            int res = (int)Math.ceil(1.0*max_time/A);
            System.out.println(res+remainTime);
        }
    }

    /**
     * 多重背包问题
     */
    public static void muilti01pack() {
        int N = 2;
        int T = 10;
        int[] prices = new int[]{0,1,1};
        int[] sat = new int[]{0,1,1};
        int[] counts = new int[]{0,1,1};
        int[][] dp = new int[N+1][T+1];
        for(int i = 0;i<=N;i++){
            dp[i][0] = 0;
        }
        for(int i = 0;i<=T;i++){
            dp[0][i] = 0;
        }
        for(int i = 1;i<=N;i++){
            for(int j = 1;j<=T;j++){
                for(int k = 0;k<=counts[i];k++){
                    if(j-k*prices[i]>=0){
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-k*prices[i]]+sat[i]*k);
                    }
                }
            }
        }
        System.out.println(dp[N][T]);
        print(dp);
    }
    private static void print(int[][] dp){
        System.out.println("dp数组：");
        for(int i = 0;i<dp.length;i++){

            for(int j = 0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 机器人大战
     * 简单的最短路径 Floyd算法
     */
    private void robotBattle(){
        int N = 0;
        int P = 0;
        int C = 0;

        int[][] map = new int[N][N];

        for(int k = 0;k<N;k++){
            for(int i = 0;i<N;i++){
                for(int j = 0;j<N;j++){
                    map[i][j] = Math.min(map[i][j],map[i][k]+map[k][j]);
                }
            }
        }

    }
}
