package leetcode.top100;

/**
 * No65_122_MaxProfitII
 *
 * 122. 买卖股票的最佳时机 II
 *
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润 。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 28, 2025</pre>
 */
public class No65_122_MaxProfitII {


    // 方法一：贪心
    public int maxProfit0(int[] prices) {
        int maxProfit = 0;
        int minCost = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minCost) {
                maxProfit += prices[i] - minCost;
                minCost = prices[i];
            } else {
                minCost = Math.min(minCost, prices[i]);
            }
        }
        return maxProfit;
    }


    // 方法二：动态规划
    // 定义二维数组，dp[i][0]表示不持有股票的收益，dp[i][1]表示持有股票的收益
    // 可知：
    // dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] + prices[i]));
    // dp[i][1] = Math.max(dp[i - 1][1], Math.max(dp[i - 1][0] - prices[i]));
    // 所以，只要针对每一天都求解它持有股票和不持有股票的收益，就能推导出n天的最大收益。
    //
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

}
