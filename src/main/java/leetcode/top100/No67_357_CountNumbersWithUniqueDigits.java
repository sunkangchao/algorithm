package leetcode.top100;

/**
 * No67_357_CountNumbersWithUniqueDigits
 *
 * 357. 统计各位数字都不同的数字个数
 *
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>05月 29, 2025</pre>
 */
public class No67_357_CountNumbersWithUniqueDigits {



    // 由于 0<=x<10^n，比如n=2，即0<=x<100，可以分别求出x是两位数，1位数的值，再把它们累加
    // 1）当然，你每计算一位时，都可以遍历它之前的全部位，再相加，但这样的时间复杂度高。
    // 2）可以把相乘的值存起来，跟着结果去迭代。
    // 10 + (9 * 9) + (9 * 9 * 8) + (9 * 9 * 8 * 7)...
    // 括号部分可以预存起来，下一次再乘以 10 - i + 1即可。
    // 所以这道题分成两部分理解，前一部分是计算当前的相加值，另一部分是计算当前的结果值。
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur = cur * (9 - i);
            ans += cur;
        }

        return ans;
    }



}
