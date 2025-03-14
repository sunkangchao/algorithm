package leetcode.top100;

/**
 * No11_5_LongestPalindrome
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>03月 14, 2025</pre>
 */
public class No11_5_LongestPalindrome {


    /**
     * 方法一，中心扩展法
     * 思路：遍历每一个字符，以该字符为中心移动同时往两边延伸，相等就继续延伸，不相等则记录最终该回文字符串的长度。
     * 注意需要分别考虑结果的回文字符串为奇数和偶数的情况，奇数情况由相同字符同时往两边扩展，偶数情况由当前字符和前一个字符再同时往两边扩展
     * 结果取奇数情况和偶数情况的最大值才是最终的结果。对的，和官方题解一致。
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */

    public String longestPalindrome1(String s) {

        char[] chars = s.toCharArray();
        int maxLength = 0;
        String result = "";

        for (int i = 0; i < chars.length; i++) {

            int j = i, k = i;
            while (j >=0 && k < chars.length && chars[j] == chars[k]) {
                j--;
                k++;
            }
            // 存在两种情况 1）越界了 2）chars[j] != chars[k] 无论哪一种 都是当前不满足 取上一层遍历的结果
            if (maxLength < k - j - 1) {
                maxLength = k - j - 1;
                result = s.substring(j + 1, k);
            }

            // 上述只是处理结果回文字符串为奇数的情况 并没有处理偶数的情况 接下来补充处理
            j = i - 1;
            k = i;
            while (j >=0 && k < chars.length && chars[j] == chars[k]) {
                j--;
                k++;
            }
            if (maxLength < k - j - 1) {
                maxLength = k - j - 1;
                result = s.substring(j + 1, k);
            }

        }

        return result;

    }


    /**
     * 方法二：动态规划
     * 思路：假设chars为字符串s的字符数组，i,j分别为字符串s的第i个和第j和字符，且P[i,j]为字符串s以i为左端点，j为右端点的子串是否为回文字符串。
     * 我们知道，假如chars[i] = chars[j]，则P[i,j]是否为回文字符串，取决于P[i+1,j-1]是否为回文字符串，且j - 1 < 3时，是否为回文字符串取决于chars[i]是否等于chars[j]
     * 所以，我们可以得到以下的转移公式
     * P[i,j] = j - 1 < 3 ? chars[i] == chars[j] : P[i + 1, j - 1] && chars[i] == chars[j]
     * 接着初始化，如果i = j, 该子串只有一个字符，则必然为回文字符串；
     * 接着就可以根据依赖关系来确定该二维数组的遍历方向，可以看到每个二维点的取值跟右下角的点有关系，所以我们可以先从左往右，再从上往下遍历。
     * 最终遍历P二维数组，取P[i,j]为true且j - 1 + 1最大的即为该字符串s的最长回文字符串。
     * 注意：i > j时，取值无效，所以不需要填充i > j时的值。
     *
     * 时间复杂度：O(n^2)。因为需要填充整个二维数组中的一半。
     * 空间复杂度：O(n^2)。需要额外的一个二维数组来存储之前计算的值。
     */
    public String longestPalindrome(String s) {

        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (j - i < 3) {
                    dp[i][j] = chars[i] == chars[j];
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && chars[i] == chars[j];
                }

                if (j - i + 1 > max && dp[i][j]) {
                    max = j - i + 1;
                    result = s.substring(i, j + 1);
                }
            }
        }

        // 上面只是遍历了i < j的最大值，如果结果在i=j上，需要补充这一条判断
        if (result.isEmpty()) {
            result = String.valueOf(chars[0]);
        }
        return result;

    }


    public static void main(String[] args) {

        No11_5_LongestPalindrome instance = new No11_5_LongestPalindrome();
        String abs = instance.longestPalindrome("a");
        System.out.println(abs);

    }


}
