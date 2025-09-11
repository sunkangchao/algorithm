package leetcode.top100;

/**
 * No98_72_MinDistance
 *
 * @author sunkangchao
 * @version 1.0
 * @since <pre>09月 11, 2025</pre>
 */
public class No98_72_MinDistance {


    // 左上角为修改，上方为增加，左方为删除
    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }

        // 关键：定义dp[i][j]，表示以i - 1为结尾的字符串word1编辑成以j-1为结尾的字符串的最短编辑距离
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 遍历计算 主要取决于左边、上边、左上角三个位置的dp值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }


}
