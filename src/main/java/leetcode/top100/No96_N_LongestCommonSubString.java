package leetcode.top100;

/**
 * No96_N_LongestCommonSubString
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>09月 10, 2025</pre>
 */
public class No96_N_LongestCommonSubString {


    /**
     * 给定字符串str1, str2，求解其最长公共子串
     * @param str1
     * @param str2
     * @return 具体的公共子串
     */
    public String longestCommonSubString(String str1, String str2) {
        // 定义dp[i][j]为第i-1,j-1索引位字符为结尾的最长公共子串长度
        if (str1 == null || str2 == null || str1.isEmpty() || str2.isEmpty()) {
            return "";
        }

        // 定义dp 最大长度 结束索引位
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        int maxLen = 0;
        int endIndex = 0;

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i - 1; // 分清楚索引和长度的关系 这里卡了
                    }
                }
            }
        }

        if (maxLen == 0) {
            return "";
        }
        return str1.substring(endIndex - maxLen + 1, endIndex + 1);
    }


    public static void main(String[] args) {
        String str1 = "ab12454";
        String str2 = "sfkjab12jji";
        No96_N_LongestCommonSubString obj = new No96_N_LongestCommonSubString();
        String ans = obj.longestCommonSubString(str1, str2);
        System.out.println(ans);
    }


}
