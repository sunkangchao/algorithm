package examination;

/**
 * 买卖股票的最好时机
 *
 * @author SunKangChao
 * @date 2021/7/16 16:42
 */
public class MaxProfit {
    //【1，4，2，6，2，4，8，9】
    // 想要稳定早起，唯有早睡

    public int maxProfit(int[] prices) {
        // write code here
        int start = 0;
        int end = 0;
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < i; j++) {
                int profit = prices[i] - prices[j];
                if (profit > maxProfit) {
                    start = j;
                    end = i;
                    maxProfit = profit;
                }
            }
        }

        System.out.println("start: " + start + ",end: " + end);
        return maxProfit;

    }

    public static void main(String[] args) {
        MaxProfit instance = new MaxProfit();
        int[] prices = {1,4,2,6,2,4,8,9};
        System.out.println(instance.maxProfit(prices));

    }
}
