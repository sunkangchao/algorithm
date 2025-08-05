package leetcode.top100;

import java.util.Arrays;

/**
 * No91_62_UniquePaths
 *
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>08月 05, 2025</pre>
 */
public class No91_62_UniquePaths {


    /**
     * 动态规划解法：dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
     * 原问题转化成子问题来求解。
     * 很显然，(i,j)位置的到达路径种数可以由(i-1, j)位置和(i, j-1)位置合并求得，它只能从这两个位置而来
     * 因为每次只能向右或者向下走，这意味着它只能从左边或者上边走过来。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {

        // 定义动态规划数组
        int[][] dp = new int[m][n];

        // 转移方程
        // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

        // 初始化
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 遍历方向
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 返回值
        return dp[m - 1][n - 1];

    }



}
