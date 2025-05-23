package leetcode.top100;

import study.util.PrintArray;

/**
 * No58_LCR003_CountBits
 *
 * <p/>
 * LCR 003. 比特位计数
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 23, 2025</pre>
 */
public class No58_LCR003_CountBits {


    /**
     * 思路：动态规划
     *
     * 定义：dp[i]为整数i的比特位1的个数
     *
     * 当i为奇数时，dp[i] = dp[i >> 1] + 1
     * 当i为偶数时，dp[i] = dp[i >> 1]
     *
     * 两者可以合并，dp[i] = dp[i >> 1] + (1 & i);
     *
     * 因此dp[i]可以由它的子结果求得，从0-n遍历即可。
     *
     * 注意：位运算符&, |, ~, 它们的优先级很低，因此每次都需要加上括号，否则都会出问题，优先级不如加减乘除。
     *
     * # 复杂度
     *
     * - 时间复杂度: $O(n)$，遍历n个整数。
     * - 空间复杂度: $O(1)$，结果数组不算额外空间。这是对的，就是O(1)
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int i1 = dp[i >> 1];
            dp[i] = i1 + (i & 1);
        }

        return dp;
    }


    public static void main(String[] args) {
        No58_LCR003_CountBits obj = new No58_LCR003_CountBits();
        int[] ints = obj.countBits(5);
        PrintArray.printArray(ints);
    }


}
