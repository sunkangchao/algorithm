package leetcode.top100;

/**
 * No78_64_MinPathSum
 *
 * 64. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 23, 2025</pre>
 */
public class No78_64_MinPathSum {


    // 动态规划
    // 假设dp[i][j]表示到达第i行第j列位置的路径上数据总和的最小值，可知
    // dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j]) + grid[i][j]
    public int minPathSum(int[][] grid) {

        // 定义
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // 初始化
        dp[0][0] = grid[0][0];
        // 初始化第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];
    }


}
