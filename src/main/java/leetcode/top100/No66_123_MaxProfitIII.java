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
    
    // 方法三
    // 三维数组解法
    // 注意：
    // 1）当进行买入时即进入下一次的交易轮次 关键和核心 买入即认为进入下一轮次 卖出不算
    // 2）三维数组最多就三层循环来求解，难度不在这里，知道怎么解就行。至于这道题，第三个维度固定只有两个，因此两层循环就可以，何况第三个维度的每个值求解方式不同
    // 3）注意最后的返回值，不一定是购买了两次的，也可能是交易了一次的，或者是交易0次的。0次这种次数已经被初始值使用Math.max取到的，因此最后只需要比较交易一次和交易两次的。
    // 4）这种解法对于k天也能够解，所以这种解法你需要掌握，以防变种题
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;

        int[][][] dp = new int[n][3][2];

        dp[0][0][0] = 0;
        dp[0][0][1] = Integer.MIN_VALUE; // 这种状态是不存在的，第i天只会依赖于第i-1天的[k-1][0],[k][0],[k][1]这三个状态，不依赖于状态[k-1][1]

        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];

        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0]; // 这里gpt给出的答案是不存在 我认为是存在的 买入卖出再买入 此时就处于这种状态 提交了是正确的
                                  // 按照gpt的做法就提交需要搭配dp[0][2][1] = Integer.MIN_VALUE + 返回Math.max(dp[n - 1][1][0], dp[n - 1][2][0]);

        // 剩余其它的天数dp[i][0][0]都为0 dp[i][0][1]可认为不存在 也不会依赖这一天
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 2; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        // 比较交易一次和交易两次的 不持有股票时收益是最大的 这种解法对于k天也能够解
        return Math.max(dp[n - 1][1][0], dp[n - 1][2][0]);
    }

}
