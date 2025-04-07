package leetcode.top100;

/**
 * 1143. 最长公共子序列
 *
 * No21_1143_LongestCommonSubsequence
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>04月 07, 2025</pre>
 */
public class No21_1143_LongestCommonSubsequence {


    /**
     * 分析：求解两个字符串的最长公共子序列，这种问题可以非常简单的转化成若干个小规模的子问题，
     * 情况一：比如，text1的最后一个字符和text2的最后一个字符是相等的，那么它的最长公共子序列显然等于text1和text2都减去最后
     * 一个字符后的最长公共子序列+1
     * 情况二：如果text1的最后一个字符和text2的最后一个字符是不相等的，此时最长的子序列应该是等于text1减去最后一个字符得到的字符串
     * 与text2的最长公共子序列，或者text2减去最后一个字符后与text1的最长公共子序列，它们的最大值就是text1和text2的最长公共子序列
     *
     * 1）状态定义，dp[i][j]表示字符串1的第i个字符与字符串2的第j个字符的最长公共子序列
     * 2) 状态转移方程，经上述分析,
     *      if(s[i - 1] == s[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1
     *      if (s[i - 1] != s[j - 1]) dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
     * 3）初始值分析，空串和任何字符串的最长公共子序列都是0
     * 4）遍历方向，dp[i][j]取决于左边、左上、以及上边，也就是先左到右然后从上到下，或者先从上到下然后从左到右，这两种方式来填表都可以
     * 5）返回值，dp[i][j]就是最终的结果
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // text1 = "abcde", text2 = "ace"
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        No21_1143_LongestCommonSubsequence obj = new No21_1143_LongestCommonSubsequence();
        int rs = obj.longestCommonSubsequence("abcde", "ace");
        System.out.println(rs);
    }

}
