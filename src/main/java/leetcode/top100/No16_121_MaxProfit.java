package leetcode.top100;

/**
 * No16_121_MaxProfit
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 28, 2025</pre>
 */
public class No16_121_MaxProfit {


    public int maxProfit(int[] prices) {

        // 定义dp[i]记录当前
        int maxProfit = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 1; i < prices.length; i++) {
            // 只有第2天开始才能卖出
            if (prices[i] > minValue) {
                maxProfit = Math.max(maxProfit, prices[i] - minValue);
            } else {
                minValue = prices[i];
            }
        }
        return maxProfit;
    }



}
