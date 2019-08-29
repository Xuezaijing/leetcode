public class xieduijiaoshuchu {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
        };
        xieduijiaoprint(matrix);
    }

    private static void xieduijiaoprint(int[][] matrix) {
        for(int k = matrix[0].length-1;k>=0;k--){
            int j = k;
            int i = 0;
            while (i<matrix.length && j<matrix[0].length){
                System.out.print(matrix[i][j]+" ");
                i++;
                j++;
            }
        }
        for(int k = 1;k<matrix.length;k++){
            int i = k;
            int j = 0;
            while(i<matrix.length && j<matrix[0].length){
                System.out.print(matrix[i][j]+" ");
                i++;
                j++;
            }
        }
    }
}
