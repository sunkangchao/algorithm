package leetcode.top100;

/**
 * No66_123_MaxProfitIII
 *
 * 123. 买卖股票的最佳时机 III
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 28, 2025</pre>
 */
public class No66_123_MaxProfitIII {


    // 动态规划，定义二维数组，用5种状态表示
    // 可以增加维度，也可以在原来维度上增加状态。
    public int maxProfit0(int[] prices) {
        int[][] dp = new int[prices.length][5];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }

        return Math.max(0, Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4]));
    }

    // 方法二：求出每一天，它前面能够获取的最大利润，以及第i天交易后，它后面能再次获得的最大利润
    // 第i天前能够获取的最大利润 + 第i天后能够获取的最大利润，两者相加，遍历每一天取最大值就是限制购买两次能够获得的最大利润
    public int maxProfit(int[] prices) {

        // 遍历数组，求出第i天能够获得的最大利润
        int len = prices.length;
        int[] preProfit = new int[len];
        preProfit[0] = 0;
        int minCost = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] > minCost) {
                maxProfit = Math.max(maxProfit, prices[i] - minCost);
            } else {
                minCost = Math.min(minCost, prices[i]);
            }
            preProfit[i] = maxProfit;
        }

        // 遍历数据，求出第i天能够获得的最大利润 反过来求 这样只需要一次遍历
        int[] postProfit = new int[len];
        postProfit[len - 1] = 0;
        int maxCost = prices[len - 1];
        int maxProfit2 = 0;
        for (int i = len - 2; i >= 0; i--) {
            if (prices[i] < maxCost) {
                maxProfit2 = Math.max(maxProfit2, maxCost - prices[i]);
            } else {
                maxCost = Math.max(maxCost, prices[i]);
            }
            postProfit[i] = maxProfit2;
        }

        // 遍历两个数组，相加每个索引值，取和最大，即为结果
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, preProfit[i] + postProfit[i]);
        }
        return ans;
    }

}
