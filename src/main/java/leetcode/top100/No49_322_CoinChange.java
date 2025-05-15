package leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * No49_322_CoinChange
 *<p/>
 * 322. 零钱兑换
 *<p/>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * <p/>
 * 完全背包问题
 *
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 12, 2025</pre>
 */
public class No49_322_CoinChange {


    /**
     * 暴力递归，把大问题拆分成子问题，然后求解子问题，进而解决大问题
     * 如coins = [1, 2, 5], amount = 11
     *
     * 要求出组成11最少需要几枚硬币，最后组成11的硬币都可以是1，2，5；那么可以分别求最后一枚是1，2，5每种情况时，
     * 则组成10，9，6时哪个所需的硬币最小，那么最终的结果就是这三个中的最小值再加1。
     *
     * 即可以看出来，原问题的结构和子问题结构完全一致。直接使用递归就可以。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange0(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }
        int ans = dfs0(coins, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public int dfs0(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }

        int nums = Integer.MAX_VALUE;
        for(int coin : coins) {
            int i = dfs0(coins, amount - coin);
            if (i >= 0) {
                nums = Math.min(i, nums);
            }
        }

        return nums == Integer.MAX_VALUE ? Integer.MAX_VALUE : nums + 1;
    }


    /**
     * 解法二：记忆化递归
     *
     * 在解法一的计算过程中存在很多的重复计算，我们可以优化一下，把每次算过的结果存储起来，
     * 避免下次重复计算。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int ans = dfs1(coins, amount, new HashMap<Integer, Integer>());
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public int dfs1(int[] coins, int amount, Map<Integer, Integer> set) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }

        if (set.containsKey(amount)) {
            return set.get(amount);
        }

        int nums = Integer.MAX_VALUE;
        for(int coin : coins) {
            int i = dfs1(coins, amount - coin, set);
            if (i >= 0) {
                nums = Math.min(i, nums);
            }
        }

        int ans = nums == Integer.MAX_VALUE ? Integer.MAX_VALUE : nums + 1;
        set.put(amount, ans);
        return ans;
    }


    /**
     * 解法三：动态规划
     * 可以把amount之前的每种值都求出来，通过记忆化且递推的方式计算结果
     */
    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            int ans = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] >= 0) {
                    ans = Math.min(ans, dp[i - coin]);
                }
            }
            dp[i] = ans == Integer.MAX_VALUE ? -1 : ans + 1;
        }
        return dp[amount];
    }






    public static void main(String[] args) {
        No49_322_CoinChange obj = new No49_322_CoinChange();
        int[] nums = {1,2,5};
        int ans = obj.coinChange(nums, 11);
        System.out.println(ans);

    }

}
