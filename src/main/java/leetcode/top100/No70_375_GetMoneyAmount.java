package leetcode.top100;

/**
 * No70_375_GetMoneyAmount
 *
 * 375. 猜数字大小 II
 *
 * 我们正在玩一个猜数游戏，游戏规则如下：
 *
 * 1. 我从 1 到 n 之间选择一个数字。
 * 2. 你来猜我选了哪个数字。
 * 3. 如果你猜到正确的数字，就会 赢得游戏 。
 * 4. 如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
 * 5. 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
 * 给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>06月 05, 2025</pre>
 */
public class No70_375_GetMoneyAmount {


    /**
     * 思路：动态规划
     *
     * 需要遍历所有可能，并不是每次选择中间的所需的金额最小，所以这就需要你进行遍历。
     * 比如求解1-10区间的最小的，你就需要定义一个k，从1到10，计算每个k取值时，1-10的区间的所需最大值（因为要保证能赢），最终求出不同k时的最小值。
     *
     * 使用动态规划写法，这种区间求解你已经写过几次了，先求解小区间，逐步求解至原区间。
     *
     * 注意：
     * 1）想不清楚的时候，动动手画图，画出来就明白为什么要这么做了，画图来分析。
     * 2）画图了，你就能发现这道题存在一个状态转移，则当前状态是可以由前一个状态转移过来，即原问题可以分解成求解子问题。
     * 3）这类题有点不好调试，错把right写成j导致排查了半小时，最后借助ai才找到问题。你最好就是认真看一遍有没有写错，思路没问题，没写错那应该就没问题。
     *
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        if (n < 2) {
            return 0;
        }

        // 1. 定义dp二维数组
        int[][] dp = new int[n + 1][n + 1];

        // 2. 初始化 都是0 直接免除
        
        // 3. 遍历求解每个区间长度的所需金额 dp[i][j] = max(dp[i, k - 1], dp[k + 1, j]]) + k
        for (int i = 2; i <= n; i++) {
            for (int j = 1; i + j - 1 <= n; j++) {
                int left = j, right = i + j - 1;
                dp[left][right] = Integer.MAX_VALUE;
                for (int k = left; k <= right; k++) {
                    if (k == left) {
                        // 错把right写成j 卡了半小时 牛逼
                        dp[left][right] = Math.min(dp[left][right], k + dp[k + 1][right]);
                    } else if (k == right) {
                        dp[left][right] = Math.min(dp[left][right], dp[left][k - 1] + k);
                    } else {
                        int max = Math.max(dp[left][k - 1], dp[k + 1][right]);
                        dp[left][right] = Math.min(dp[left][right], max + k);
                    }
                }
            }
        }

        // 4. 返回dp[0][n]
        return dp[1][n];
    }


    public static void main(String[] args) {
        No70_375_GetMoneyAmount obj = new No70_375_GetMoneyAmount();
        int ans = obj.getMoneyAmount(10);
        System.out.println(ans);
    }



}
