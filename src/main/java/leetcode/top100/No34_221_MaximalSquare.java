package leetcode.top100;

/**
 * No34_221_MaximalSquare
 *<p>221. 最大正方形</p>
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 23, 2025</pre>
 */
public class No34_221_MaximalSquare {


    /**
     * 定义dp[i][j]为以第i行j列的元素为右下角的正方形的边长
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(dp[i][0], max);
        }

        for (int j = 0; j < matrix[0].length; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            max = Math.max(dp[0][j], max);
        }


        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

//        return max ^ 2;
        return (int) Math.pow(max, 2);
    }

}
